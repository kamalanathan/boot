package com.kamal.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kamal.domain.Profile;

//@FeignClient(name = "profiles-service")
@FeignClient(name = "zuul-api-gatewayserver") // for api
@RibbonClient(name = "profiles-service")
public interface ProfilesServiceProxy {
//	@PostMapping("/profiles/findid/{id}")
	@PostMapping("/profiles-service/profiles/findid/{id}") // for api
	public Profile findprofile(@PathVariable("id") long id);
}
