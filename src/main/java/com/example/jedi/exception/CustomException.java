package com.example.jedi.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -5995709464885568662L;
	
	private final HttpStatus httpStatus;
	
	public CustomException(ExceptionMessage messageCode) {
		super(messageCode.getMessage());
		this.httpStatus = messageCode.getHttpStatus();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
