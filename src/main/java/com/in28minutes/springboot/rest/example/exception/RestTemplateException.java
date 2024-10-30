package com.in28minutes.springboot.rest.example.exception;

public class RestTemplateException extends RuntimeException {

	private static final long serialVersionUID = 6608057962828412254L;

	public RestTemplateException(final String message) {
		super(message);
	}
}
