package com.kamal.zuulfilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.kamal.zuulfilter.utils.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.util.HTTPRequestUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulRouteFilter extends ZuulFilter {
	@Autowired
	FilterUtils filterUtils;

	ProxyRequestHelper helper = new ProxyRequestHelper();

	private static int count = 0;

	private String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName) {
		return
		// "http://master:6078/demand-supply-fe/cars/demandwithsuggestions";
		oldEndpoint.replaceAll("demandallfezuul", newEndpoint);
	}

	private String getVerb(HttpServletRequest request) {
		String sMethod = request.getMethod();
		return sMethod.toUpperCase();
	}

	private InputStream getRequestBody(HttpServletRequest request) {
		InputStream requestEntity = null;
		try {
			requestEntity = request.getInputStream();
		} catch (IOException ex) {
			// no requestBody is ok.
		}
		return requestEntity;
	}

	private HttpHost getHttpHost(URL host) {
		HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
		return httpHost;
	}

	private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost, HttpRequest httpRequest)
			throws IOException {
		return httpclient.execute(httpHost, httpRequest);
	}

	private MultiValueMap<String, String> revertHeaders(Header[] headers) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		for (Header header : headers) {
			String name = header.getName();
			if (!map.containsKey(name)) {
				map.put(name, new ArrayList<String>());
			}
			map.get(name).add(header.getValue());
		}
		return map;
	}

	private void setResponse(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		// System.out.println(responseString);
		// InputStream stream = response.gete.getResponseDataStream();
		// String body = StreamUtils.copyToString(", Charset.forName("UTF-8"));
		// body = "Modified via setResponseDataStream(): " + body;
		RequestContext context = RequestContext.getCurrentContext();
		context.setResponseStatusCode(response.getStatusLine().getStatusCode());
		if (entity != null) {
			context.setResponseDataStream(new ByteArrayInputStream(responseString.getBytes("UTF-8")));
		}

		boolean isOriginResponseGzipped = false;
		MultiValueMap<String, String> map = revertHeaders(response.getAllHeaders());
		for (Entry<String, List<String>> header : map.entrySet()) {
			String name = header.getKey();
			for (String value : header.getValue()) {
				context.addOriginResponseHeader(name, value);

				if (name.equalsIgnoreCase(HttpHeaders.CONTENT_ENCODING)
						&& HTTPRequestUtils.getInstance().isGzipped(value)) {
					isOriginResponseGzipped = true;
				}
				if (name.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
					context.setOriginContentLength(value);
				}
				if (this.helper.isIncludedHeader(name)) {
					context.addZuulResponseHeader(name, value);
				}
			}
		}
		context.setResponseGZipped(isOriginResponseGzipped);
		// this.helper.setResponse(response.getStatusLine().getStatusCode(),
		// // responseString,
		// response.getEntity() == null ? null : response.getEntity().getContent(),
		// revertHeaders(response.getAllHeaders()));
	}

	private HttpResponse forward(HttpClient httpclient, String verb, String uri, HttpServletRequest request,
			MultiValueMap<String, String> headers, MultiValueMap<String, String> params, InputStream requestEntity)
			throws Exception {
		Map<String, Object> info = helper.debug(verb, uri, headers, params, requestEntity);
		URL host = new URL("http://master:6073" + uri);
		// URL host = new URL(uri);
		HttpHost httpHost = getHttpHost(host);

		HttpRequest httpRequest;
		int contentLength = request.getContentLength();
		InputStreamEntity entity = new InputStreamEntity(requestEntity, contentLength,
				request.getContentType() != null ? ContentType.create(request.getContentType()) : null);
		switch (verb.toUpperCase()) {
		case "POST":
			HttpPost httpPost = new HttpPost(uri);
			httpRequest = httpPost;
			httpPost.setEntity(entity);
			break;
		case "PUT":
			HttpPut httpPut = new HttpPut(uri);
			httpRequest = httpPut;
			httpPut.setEntity(entity);
			break;
		case "PATCH":
			HttpPatch httpPatch = new HttpPatch(uri);
			httpRequest = httpPatch;
			httpPatch.setEntity(entity);
			break;
		default:
			httpRequest = new BasicHttpRequest(verb, uri);

		}
		try {
			httpRequest.setHeaders(convertHeaders(headers));
			HttpResponse zuulResponse = forwardRequest(httpclient, httpHost, httpRequest);

			return zuulResponse;
		} finally {
		}
	}

	private Header[] convertHeaders(MultiValueMap<String, String> headers) {
		List<Header> list = new ArrayList<Header>();
		for (String name : headers.keySet()) {
			for (String value : headers.get(name)) {
				list.add(new BasicHeader(name, value));
			}
		}
		return list.toArray(new BasicHeader[0]);
	}

	private void forwardToSpecialRoute(String route) {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		MultiValueMap<String, String> headers = helper.buildZuulRequestHeaders(request);
		MultiValueMap<String, String> params = helper.buildZuulRequestQueryParams(request);
		String verb = getVerb(request);
		InputStream requestEntity = getRequestBody(request);
		if (request.getContentLength() < 0) {
			context.setChunkedRequestBody();
		}

		helper.addIgnoredHeaders();
		CloseableHttpClient httpClient = null;
		HttpResponse response = null;

		try {
			httpClient = HttpClients.createDefault();
			response = forward(httpClient, verb, route, request, headers, params, requestEntity);
			setResponse(response);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				httpClient.close();
			} catch (IOException ex) {
			}
		}
	}

	// @Override
	// public Object run2() throws ZuulException {
	// OkHttpClient httpClient = new OkHttpClient.Builder()
	// // customize
	// .build();
	//
	// RequestContext context = RequestContext.getCurrentContext();
	// HttpServletRequest request = context.getRequest();
	//
	// String method = request.getMethod();
	//
	// String uri = this.helper.buildZuulRequestURI(request);
	//
	// Headers.Builder headers = new Headers.Builder();
	// Enumeration<String> headerNames = request.getHeaderNames();
	// while (headerNames.hasMoreElements()) {
	// String name = headerNames.nextElement();
	// Enumeration<String> values = request.getHeaders(name);
	//
	// while (values.hasMoreElements()) {
	// String value = values.nextElement();
	// headers.add(name, value);
	// }
	// }
	//
	// InputStream inputStream = request.getInputStream();
	//
	// RequestBody requestBody = null;
	// if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
	// MediaType mediaType = null;
	// if (headers.get("Content-Type") != null) {
	// mediaType = MediaType.parse(headers.get("Content-Type"));
	// }
	// requestBody = RequestBody.create(mediaType,
	// StreamUtils.copyToByteArray(inputStream));
	// }
	//
	// Request.Builder builder = new
	// Request.Builder().headers(headers.build()).url(uri).method(method,
	// requestBody);
	//
	// Response response = httpClient.newCall(builder.build()).execute();
	//
	// LinkedMultiValueMap<String, String> responseHeaders = new
	// LinkedMultiValueMap<>();
	//
	// for (Map.Entry<String, List<String>> entry :
	// response.headers().toMultimap().entrySet()) {
	// responseHeaders.put(entry.getKey(), entry.getValue());
	// }
	//
	// this.helper.setResponse(response.code(), response.body().byteStream(),
	// responseHeaders);
	// context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
	// return null;
	// }

	// @Override
	public Object run() throws ZuulException {
		log.debug("run");
		RequestContext ctx = RequestContext.getCurrentContext();
		if (ctx.getRequest().getRequestURI().contains("demandallfezuul")) {
			String route = buildRouteString(ctx.getRequest().getRequestURI(), "demandwithsuggestions",
					ctx.get("serviceId").toString());
			forwardToSpecialRoute(route);
		}
		count++;
		return null;
	}

	@Override
	public boolean shouldFilter() {
		log.debug("shouldFilter");
		return true;
	}

	@Override
	public int filterOrder() {
		log.debug("filterOrder");
		return 10000;
	}

	@Override
	public String filterType() {
		log.debug("filtertype");
		return "route";
	}

	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

	private boolean isCorrelationIdPresent() {
		if (filterUtils.getCorrelationId() != null) {
			return true;
		}
		return false;
	}
}