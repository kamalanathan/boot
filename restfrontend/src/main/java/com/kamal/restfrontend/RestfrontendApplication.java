package com.kamal.restfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com.kamal")
@EnableFeignClients("com.kamal")
@Slf4j
public class RestfrontendApplication {
	public static void main(String[] args) {
		log.error("I am loaded RestfrontendApplication");
		SpringApplication.run(RestfrontendApplication.class, args);
	}
}
