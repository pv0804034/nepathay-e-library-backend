package com.nepathya.archive.system.security.login;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AuthenticationRequestDto implements Serializable {
	private static final long serialVersionUID = -9060673155331048506L;

	@NotBlank(message = "{validation.email.blank}")
	private String email;

	@NotBlank(message = "{validation.password.blank}")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
