package com.nepathya.archive.system.security.resetPassword;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, UUID> {
	public PasswordReset findByEmail(String email);
}
