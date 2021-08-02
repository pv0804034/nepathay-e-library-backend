package com.nepathya.archive.system.security.resetPassword;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController()
@RequestMapping(UrlConstant.RESET_PASSWORD)
public class ResetPasswordController {

	@Autowired
	ResetPasswordService resetPasswordService;

	@PostMapping("/{token}")
	public ResponseEntity<?> saveResetPassword(@RequestBody ResetPasswordForm resetPasswordFrom,
			@PathVariable String token, @PathParam("email") String email) {
//		if(result.hasErrors()) {
//			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(result));
//		}
//		if(!resetPasswordFrom.getNewPassword().equals(resetPasswordFrom.getReNewPassword())) {
//			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, new ApiResponse("Password Doesnot matched"));
//		}

		System.out.println("For git testing purpose");
		return resetPasswordService.resetPassword(resetPasswordFrom, email, token);
	}
}
