package com.in28minutes.springboot.rest.example.exception;

import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private Map<String, String> message;
}
