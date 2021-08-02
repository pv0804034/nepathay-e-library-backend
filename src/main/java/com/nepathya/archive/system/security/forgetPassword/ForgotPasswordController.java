package com.nepathya.archive.system.security.forgetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController()
@RequestMapping(UrlConstant.FORGOT_PASSWORD)
public class ForgotPasswordController {
	
	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	@PostMapping("")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordForm forgotPasswordForm,BindingResult result){
		if(result.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(result));
		}
		return forgotPasswordService.forgotPassword(forgotPasswordForm);
	}
	
}
