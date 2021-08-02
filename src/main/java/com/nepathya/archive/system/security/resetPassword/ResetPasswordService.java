package com.nepathya.archive.system.security.resetPassword;

import org.springframework.http.ResponseEntity;


public interface ResetPasswordService {
	
	public ResponseEntity<?> resetPassword(ResetPasswordForm resetPasswordForm, String email, String token);
	
}