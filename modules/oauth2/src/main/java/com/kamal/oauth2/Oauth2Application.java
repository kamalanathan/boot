package com.kamal.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableAuthorizationServer
// @RestController
public class Oauth2Application {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	// @RequestMapping(value = { "/kamal/user" }, produces = "application/json")
	// public Map<String, Object> user(OAuth2Authentication user) {
	// Map<String, Object> userInfo = new HashMap<>();
	// userInfo.put("user", user.getUserAuthentication().getPrincipal());
	// userInfo.put("authorities",
	// AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
	// return userInfo;
	// }
}
