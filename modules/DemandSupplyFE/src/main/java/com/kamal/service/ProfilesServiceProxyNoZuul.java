package com.kamal.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kamal.domain.Profile;

@FeignClient(name = "profiles-service")
@RibbonClient(name = "profiles-service")
public interface ProfilesServiceProxyNoZuul {
	@PostMapping("/profiles/findid/{id}")
	public Profile findprofile(@PathVariable("id") long id);
}
