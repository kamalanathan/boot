package com.kamal.zuulfilter.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/**").permitAll().antMatchers("/**").authenticated();

		// http.authorizeRequests().anyRequest().authenticated();
		// http.authorizeRequests().antMatchers(HttpMethod.DELETE,
		// "/v1/organizations/**").hasRole("ADMIN").anyRequest()
		// .authenticated();
	}
}
