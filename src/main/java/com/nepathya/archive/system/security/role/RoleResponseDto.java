package com.nepathya.archive.system.security.role;

import java.util.UUID;

public class RoleResponseDto {

	private UUID roleId;

	private String role;

	private String description;

	public RoleResponseDto(Role role) {
		this.roleId = role.getRoleId();
		this.role = role.getRole();
		this.description = role.getDescription();
	}

	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
