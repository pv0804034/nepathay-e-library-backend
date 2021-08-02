package com.nepathya.archive.system.security.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController
@CrossOrigin
@RequestMapping(UrlConstant.REGISTER)
public class RegisterController {
	
	@Autowired
	UserRegisterService userRegisterService;
	
	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
		return userRegisterService.saveUser(registerDTO);
	}
}
