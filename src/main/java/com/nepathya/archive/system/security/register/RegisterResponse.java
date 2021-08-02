package com.nepathya.archive.system.security.register;

public class RegisterResponse {
	
	private boolean status;
	
	private String message;
	
	public RegisterResponse() {}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
