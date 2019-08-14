package com.kamal.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.kamal.domain.Offers;

@FeignClient(name = "offers-service")
@RibbonClient(name = "offers-service")
public interface OffersServiceProxyNoZuul {
	@GetMapping("/offers/getallvalidoffers")
	public List<Offers> getallvalidoffers();
}
