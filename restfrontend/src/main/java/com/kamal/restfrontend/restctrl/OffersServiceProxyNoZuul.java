package com.kamal.restfrontend.restctrl;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "backend-service")
@RibbonClient(value = "backend-service")
public interface OffersServiceProxyNoZuul {
	@GetMapping("/backend/entrypoint")
	public String getData();
}