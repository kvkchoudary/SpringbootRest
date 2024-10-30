package com.in28minutes.springboot.rest.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FlmAddressUpdateResponse {

	private String stopId;
	
	private String stopIdType;
	
	private String addressLine1 ;

	private String addressLine2; 

	private String addressLine3; 

	private String city;

	private String state;

	private String zipCode ;
}
