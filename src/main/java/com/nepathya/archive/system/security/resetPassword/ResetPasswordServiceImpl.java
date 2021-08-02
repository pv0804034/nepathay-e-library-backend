package com.nepathya.archive.system.security.resetPassword;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ApiResponse;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserRepository;
import com.nepathya.archive.system.security.user.UserService;
import com.nepathya.archive.system.service.common.PasswordEncoderUtil;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService{

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordResetRepository passwordResetRepository;
	
	@Autowired
	private PasswordEncoderUtil passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<?> resetPassword(ResetPasswordForm resetPasswordForm, String email, String token) {
				
		PasswordReset passwordReset = passwordResetRepository.findByEmail(email);
		
		if(Objects.isNull(passwordReset)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false, new ApiResponse(Boolean.FALSE, "Invalid reset link."));
		}
		
		User user = userRepository.findByEmailId(passwordReset.getEmail());
		
		if(Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false, new ApiResponse(Boolean.FALSE, "Invalid reset link."));
		}
		
		if(passwordEncoder.matches(token, passwordReset.getToken())) {
			System.out.println(passwordEncoder.matches(token, passwordReset.getToken()));
			user.setPassword(passwordEncoder.encode(resetPasswordForm.getNewPassword()));
			userRepository.save(user);
			passwordResetRepository.delete(passwordReset);
		} else {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false, new ApiResponse(Boolean.FALSE, "Invalid reset link."));
		}
		
		return ResponseHandler.response(HttpStatus.OK, true, new ApiResponse(Boolean.TRUE, "Successfully Reset"));
	}

}
