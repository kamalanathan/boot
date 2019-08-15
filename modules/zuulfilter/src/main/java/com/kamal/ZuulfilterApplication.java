package com.kamal;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.kamal.zuulfilter.UserContextFilter;

import brave.sampler.Sampler;

@EnableZuulProxy
// @EnableZuulServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
public class ZuulfilterApplication {
	@Bean
	public Filter userContextFilter() {
		UserContextFilter userContextFilter = new UserContextFilter();
		return userContextFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulfilterApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
