package com.in28minutes.springboot.rest.example.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlmAddressApiRequest {

	 private String address1; 
	 private String address2;
	 private String address3;
	 private String city;
	 private String stateProvince;
	 private String postalCode;  
	 private String countryCode;
	 private String vintage;
	 private String firmName;
	 private String contactName;
	 
}
