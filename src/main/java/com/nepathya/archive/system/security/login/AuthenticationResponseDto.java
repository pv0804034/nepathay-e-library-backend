package com.nepathya.archive.system.security.login;

import java.io.Serializable;

import com.nepathya.archive.system.security.user.User;


public class AuthenticationResponseDto implements Serializable {
	private static final long serialVersionUID = 7599538604307515156L;
	private String token;
	private String email;
	private String roleName;
	private Boolean isActive;
	private String firstName;
	private String lastName;
	private Boolean isSuspended;

	public AuthenticationResponseDto(User user, String firstName, String lastName, Boolean isSuspended) {
		this.email = user.getEmailId();
		this.roleName = user.getRole().getRole();
		this.isActive = user.getIsActive();
		this.firstName = firstName;
		this.lastName = lastName;
		this.isSuspended = isSuspended;
	}

	public AuthenticationResponseDto() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsSuspended() {
		return isSuspended;
	}

	public void setIsSuspended(Boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
}
