package com.example.jedi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1464512082801513106L;

	public DataNotFoundException() {
		super("Data with id not found in the database!");
	}

}
