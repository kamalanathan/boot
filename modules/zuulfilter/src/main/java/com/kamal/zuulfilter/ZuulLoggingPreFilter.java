package com.kamal.zuulfilter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kamal.zuulfilter.utils.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulLoggingPreFilter extends ZuulFilter {
	@Autowired
	FilterUtils filterUtils;

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {}, request uri -> {}", httpServletRequest, httpServletRequest.getRequestURI());

		if (isCorrelationIdPresent()) {
			log.debug("tmx-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId());
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
		}
		log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
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
		return 1;
	}

	@Override
	public String filterType() {
		log.debug("filtertype");
		return "pre";
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