package com.example.jedi.exception;

import java.util.ResourceBundle;

import org.springframework.http.HttpStatus;

public enum ExceptionMessage {

	PERSON_NOT_FOUND("data.0001"),
	CAR_NOT_FOUND("data.0002");
	
	private final String messageCode;
	private final ResourceBundle resourceBundle;

	private ExceptionMessage(String messageCode) {
		this.messageCode = messageCode;
		this.resourceBundle = ResourceBundle.getBundle("com.example.jedi.bundles.exception_messages");
	}
	
	public String getMessage() {
		return this.resourceBundle.getString(this.messageCode);
	}
	
	public HttpStatus getHttpStatus() {
		Integer code = Integer.valueOf(this.resourceBundle.getString(this.messageCode + ".status"));
		return HttpStatus.valueOf(code);
	}
}
