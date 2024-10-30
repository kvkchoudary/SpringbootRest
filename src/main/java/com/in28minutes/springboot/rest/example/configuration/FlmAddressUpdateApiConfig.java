package com.in28minutes.springboot.rest.example.configuration;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlmAddressUpdateApiConfig {

	/*
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	*/
	
	//Rest Template bean with configuration
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 return builder
	  .setConnectTimeout(Duration.ofMillis(3000))
	  .setReadTimeout(Duration.ofMillis(3000))
	  .build();
	}

}
