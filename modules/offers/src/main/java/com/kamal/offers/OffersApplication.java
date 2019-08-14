package com.kamal.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.kamal")
public class OffersApplication {
	public static void main(String[] args) {
		SpringApplication.run(OffersApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.NEVER_SAMPLE;
	}
}
