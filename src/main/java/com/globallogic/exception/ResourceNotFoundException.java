/*
 * Custom Exception : Resource Not Found Exception
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
