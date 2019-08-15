package com.kamal.oauth2.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2ServerCtrlr {
	@RequestMapping(value = { "/user" }, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		userInfo.put("authorities1", (user.getUserAuthentication().getDetails()));
		userInfo.put("authorities2", (user.getUserAuthentication().getName()));
		userInfo.put("authorities3", (user.getUserAuthentication().getPrincipal()));
		return userInfo;
	}
}
