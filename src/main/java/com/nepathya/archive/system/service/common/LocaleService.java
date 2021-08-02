package com.nepathya.archive.system.service.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocaleService {
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return this.messageSource.getMessage(code, null, locale);
	}
	
	public Object messageResponse(String code) {
		String message = getMessage(code);
		Map<String,String> res = new HashMap<String, String>();
		res.put("message",message);
		return res;
	}
}
