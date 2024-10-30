package com.in28minutes.springboot.rest.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class FlmAddressUpdateConfig {

	@Value("${okta.proxySettings:null}")
	private boolean oktaProxySettings;
	
	@Value("${okta.proxyHost:null}")
	private String proxyHost;
	
	@Value("${okta.proxyPort:null}")
	private String proxyPort;
	
	@Value("${okta.oauth2.issuer:null}")
	private String oauthIssuer;
	
	@Value("${flm-address-update.flm-address-api.url}")
	private String flmAddressApiURL;
	
	@Value("${flm-address-update.flm-address-api.clientid:null}")
	private String addressApiClientid;
	
	@Value("${flm-address-update.flm-address-api.clientsecret:null}")
	private String addressApiClientSecret;
	
	@Value("${flm-address-update.flm-address-api.scope:null}")
	private String addressApiScope;
	
	@Value("${flm-address-update.flm-address-api.issuer:null}")
	private String addressApiOktaIssuer;
	
	@Value("${flm-address-update.address-update-api.swagger: }")
	private String swggerURL;
	
	
}
