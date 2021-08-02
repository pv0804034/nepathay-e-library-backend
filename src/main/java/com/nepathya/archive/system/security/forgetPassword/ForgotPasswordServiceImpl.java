package com.nepathya.archive.system.security.forgetPassword;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ApiResponse;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.security.factory.JWTTokenFactory;
import com.nepathya.archive.system.security.resetPassword.PasswordReset;
import com.nepathya.archive.system.security.resetPassword.PasswordResetRepository;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserRepository;
import com.nepathya.archive.system.service.common.LocaleService;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordResetRepository passwordResetRepository;
	
	@Autowired
	JWTTokenFactory jwtTokenFactory;
	
	@Autowired
	LocaleService localeService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseEntity<?> forgotPassword(ForgotPasswordForm forgotPasswordForm) {
		User user = userRepository.findByEmailId(forgotPasswordForm.getEmail());
		if(Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false, new ApiResponse(Boolean.FALSE, localeService.getMessage(UrlConstant.USER_NOT_EXIST)));
		}
		PasswordReset passwordReset =passwordResetRepository.findByEmail(user.getEmailId());
		if(!Objects.isNull(passwordReset)) {
			passwordResetRepository.delete(passwordReset);
		}
		String token = UUID.randomUUID().toString();
		passwordResetRepository.save(new PasswordReset(user.getEmailId(), passwordEncoder.encode(token))); 
		return ResponseHandler.response(HttpStatus.OK, true , new ApiResponse(Boolean.TRUE, "/api/v1/resetPassword/"+token+"?email="+user.getEmailId()));
	}

}