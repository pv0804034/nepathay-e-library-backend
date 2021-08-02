package com.nepathya.archive.system.security.register;

import org.springframework.http.ResponseEntity;

public interface UserRegisterService {
	
	ResponseEntity<?> saveUser(RegisterDTO registerDTO);
	
}
