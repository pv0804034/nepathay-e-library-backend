package com.nepathya.archive.system.security.user;

import java.util.UUID;

public class UserResponseDto {
	
	private UUID id;

	private String firstName;
	
	private String lastName;
	
	private String emailId;

	private String roleName;
	
	private String phone;
	
	private Boolean isActive;

	public UserResponseDto(User user) {
		this.id = user.getUserId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailId = user.getEmailId();
		this.roleName = user.getRole().getRole();
		this.phone = user.getPhone();
		this.isActive = user.getIsActive();
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}