package com.in28minutes.springboot.rest.example.exception;

public class InternalServiceException extends Exception {

	private static final long serialVersionUID = 170290541924669069L;

	public InternalServiceException(String message) {
		super(message);
	}

}