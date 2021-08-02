package com.nepathya.archive.system.common;

import java.io.Serializable;

public class ApiResponse implements Serializable{

	private static final long serialVersionUID = -6235903226960703552L;
	
	private Boolean success;
	
	private String message;
	
	public ApiResponse() {}

	public ApiResponse(Boolean success, String message) {
		this.setSuccess(success);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
