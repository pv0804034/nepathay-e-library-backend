package com.nepathya.archive.system.security.resetPassword;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="password_resets")
public class PasswordReset{
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "reset_id", updatable = false, nullable = false, length = 16)
	private UUID resetId;
	
	@Column(name="email",unique = true)
	private String email;
	
	@Column(name="token")
	private String token;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	public PasswordReset() {
	}
	
	public PasswordReset(String email, String token) {
		this.email = email;
		this.token = token;
		this.createdAt = LocalDateTime.now();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}