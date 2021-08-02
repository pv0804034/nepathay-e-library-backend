package com.nepathya.archive.system.message;

public class MessageDTO {
	
	private String fullName;
	
	private String email;
	
	private String message;
	
	public MessageDTO() {}
	
	public MessageDTO(String fullName, String email, String message) {
		this.fullName = fullName;
		this.email = email;
		this.message = message;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
