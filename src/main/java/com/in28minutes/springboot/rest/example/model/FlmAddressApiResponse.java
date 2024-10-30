package com.in28minutes.springboot.rest.example.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class FlmAddressApiResponse {
	private MatchedStop matchedStop;
	private AddressInformation addressInformation;
	private ArrayList<Object> geoCodes = new ArrayList<>();
	private ArrayList<Object> operationalGeoCategory = new ArrayList<>();
}
