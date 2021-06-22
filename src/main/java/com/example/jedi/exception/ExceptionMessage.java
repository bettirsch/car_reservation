package com.example.jedi.exception;

import java.util.ResourceBundle;

public enum ExceptionMessage {

	PERSON_NOT_FOUND("data.0001"),
	CAR_NOT_FOUND("data.0002");
	
	private final String messageCode;
	private final ResourceBundle resourceBundle;

	ExceptionMessage(String messageCode) {
		this.messageCode = messageCode;
		this.resourceBundle = ResourceBundle.getBundle("com.example.jedi.bundles.exception_messages");
	}
	
	public String getMessage() {
		return this.resourceBundle.getString(this.messageCode);
	}

}
