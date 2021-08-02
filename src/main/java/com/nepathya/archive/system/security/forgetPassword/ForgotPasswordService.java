package com.nepathya.archive.system.security.forgetPassword;

import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {
	
	public ResponseEntity<?> forgotPassword(ForgotPasswordForm forgotPasswordForm);
	
}