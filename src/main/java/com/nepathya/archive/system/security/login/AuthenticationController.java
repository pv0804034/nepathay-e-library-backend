package com.nepathya.archive.system.security.login;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.security.factory.JWTTokenFactory;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserService;
import com.nepathya.archive.system.service.common.LocaleService;

import io.swagger.annotations.Api;

@Api(value = "Authentication Controller")
@RestController
@CrossOrigin
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@Autowired
	private LocaleService localeService;

	@Autowired
	private AuthenticationDtoMapper authenticationDtoMapper;

	@Autowired
	private JWTTokenFactory jwtTokenFactory;

	@PostMapping(UrlConstant.LOGIN)
	public ResponseEntity<Object> login(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		User user = userService.findByEmail(authenticationRequestDto.getEmail());
		if (Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.UNAUTHORIZED, false,
					localeService.getMessage("invalid.credential"));
		}
		boolean isValidCredentials = authenticationDtoMapper
				.authenticateUserCredentials(authenticationRequestDto.getPassword(), user);
		if (!isValidCredentials) {
			return ResponseHandler.response(HttpStatus.UNAUTHORIZED, false,
					localeService.getMessage("invalid.credential"));
		}
		if (!user.getIsActive()) {
			return ResponseHandler.response(HttpStatus.UNAUTHORIZED, false, localeService.getMessage("user.inactive"));
		}

		AuthenticationResponseDto authenticationResponseDto = userService.generateAuthenticationResponse(user);
		if (Boolean.TRUE.equals(authenticationResponseDto.getIsSuspended())) {
			return ResponseHandler.response(HttpStatus.UNAUTHORIZED, false, localeService.getMessage("user.suspended"));
		}

		String token = jwtTokenFactory.createToken(authenticationRequestDto.getEmail(),user.getRole().getRole(),user.getFirstName(),user.getLastName(),user.getIsActive(),user.getUserId());
		authenticationResponseDto.setToken(token);
		return ResponseHandler.response(HttpStatus.OK, true, authenticationResponseDto);
	}

}
