package com.example.jedi.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5995709464885568662L;
	
	private final HttpStatus httpStatus;
	
	public DataNotFoundException(ExceptionMessage messageCode) {
		super(messageCode.getMessage());
		this.httpStatus = HttpStatus.NOT_FOUND;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
