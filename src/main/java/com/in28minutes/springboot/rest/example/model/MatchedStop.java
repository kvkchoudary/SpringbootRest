package com.in28minutes.springboot.rest.example.model;

import lombok.Data;

@Data
public class MatchedStop {

	private boolean valid;
	private boolean created;
	private boolean mrl;
	private String stopId;
	private String stopIdType;
	private String vintage;
	private boolean highrise;
	private String shareId;

}
