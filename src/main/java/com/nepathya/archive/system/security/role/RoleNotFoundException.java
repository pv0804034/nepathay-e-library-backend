package com.nepathya.archive.system.security.role;

public class RoleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2043369984622206777L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
