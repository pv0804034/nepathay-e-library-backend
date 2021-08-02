package com.nepathya.archive.system.security.changePassword;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.common.utils.GenericUtils;
import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.security.user.UserService;
import com.nepathya.archive.system.service.common.LocaleService;

import io.swagger.annotations.Api;

@Api
@RestController()
@RequestMapping(UrlConstant.CHANGE_PASSWORD)
public class ChangePasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private LocaleService localeService;

	@PutMapping("")
	public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePasswordForm passwordForm,
			BindingResult result, @RequestHeader String authorization) {
		if (result.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(result));
		}
		boolean response = userService.changePassword(GenericUtils.getLoggedInUser(), passwordForm);
		if (response) {
			return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("password.changed"));
		}

		return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, localeService.getMessage("password.wrong"));
	}

}
