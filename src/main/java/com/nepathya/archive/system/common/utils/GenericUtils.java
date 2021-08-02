package com.nepathya.archive.system.common.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nepathya.archive.system.security.user.User;


public class GenericUtils {

	/**
	 * 
	 */
	private GenericUtils() {
	}

	/**
	 * This method is use to check valid mail
	 * 
	 * @param <EmailValidator>
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidMail(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}

	public static User getLoggedInUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	
}
