package com.in28minutes.springboot.rest.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenData {

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private String expiresIn;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("token_type")
	private String tokenType;
}