package com.kamal.zuulfilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.kamal.zuulfilter.utils.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulPostLoggingFilter extends ZuulFilter {

	@Autowired
	FilterUtils filterUtils;

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {}, request uri -> {}", httpServletRequest, httpServletRequest.getRequestURI());
		log.debug("Adding the correlation id to the outbound headers. {}", filterUtils.getCorrelationId());
		ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
		log.debug("Completing outgoing request for {}.", ctx.getRequest().getRequestURI());
		try {
			InputStream stream = ctx.getResponseDataStream();
			String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			System.out.println(body);
//			if (ctx.getRequest().getRequestURI().contains("demandwithsuggestions")) {
				// RequestContext context = getCurrentContext();
//				stream = ctx.getResponseDataStream();
//				body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
				body = "Modified via setResponseDataStream(): " + body;
				ctx.setResponseDataStream(new ByteArrayInputStream(body.getBytes("UTF-8")));
				ctx.setRouteHost(null);
//			}
		} catch (IOException e) {
			e.printStackTrace();
			// rethrowRuntimeException(e);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		System.out.println("shouldFilter");
		return true;
	}

	@Override
	public int filterOrder() {
		System.out.println("filterOrder");
		return 1;
	}

	@Override
	public String filterType() {
		System.out.println("filtertype");
		return "post";
	}
}