/*
 * Customized Response for Exceptions
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.exception;

import java.util.Date;

public class ErrorResponse {

	private Date timestamp;
	private String status;
	private String message;
	private String path;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
