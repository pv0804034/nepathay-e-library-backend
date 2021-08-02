package com.nepathya.archive.system.security.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nepathya.archive.system.security.role.Role;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = -6707965161074921481L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "user_id", updatable = false, nullable = false, length = 16)
	private UUID userId;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "email_id", nullable = false, length = 100, unique = true)
	private String emailId;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "phone", nullable = false, length = 20)
	private String phone;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = false;

	@JoinColumn(name = "role_id", nullable = false)
	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Role role;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@JsonIgnore
	@Column(name = "modified_date")
	@UpdateTimestamp
	private LocalDateTime modifiedDate;

	@Column(name = "account_deleted", nullable = false)
	private Boolean accountDeleted = false;

	@JsonIgnore
	@Column(name = "last_login_date", nullable = true)
	private LocalDateTime lastLoginDate;

	@Column(name = "login_count", nullable = true)
	private Long loginCount;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getAccountDeleted() {
		return accountDeleted;
	}

	public void setAccountDeleted(Boolean accountDeleted) {
		this.accountDeleted = accountDeleted;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorites = new ArrayList<SimpleGrantedAuthority>();
		authorites.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));
		return authorites;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", password=" + password + ", phone=" + phone + ", isActive=" + isActive + ", role=" + role
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", accountDeleted="
				+ accountDeleted + ", lastLoginDate=" + lastLoginDate + ", loginCount=" + loginCount + "]";
	}
	
	
}
