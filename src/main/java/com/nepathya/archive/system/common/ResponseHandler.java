package com.nepathya.archive.system.common;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	private ResponseHandler() {
	}

	public static ResponseEntity<Object> response(HttpStatus httpStatus, Boolean success, Object responseObject) {
		Map<String, Object> map = new TreeMap<>();
		map.put("success", success);
		map.put("responseObject", responseObject);
		return new ResponseEntity<>(map, httpStatus);
	}
}
