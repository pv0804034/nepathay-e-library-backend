package com.nepathya.archive.system.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nepathya.archive.system.common.ApiResponse;


@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class InvalidFileFormatException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private ApiResponse apiResponse;

	public InvalidFileFormatException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	public InvalidFileFormatException() {
		super();
		this.setApiResponse();
	}
	
	public InvalidFileFormatException(String message) {
		super(message);
	}

	public InvalidFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiResponse getApiResponse() {
		return apiResponse;
	}
	
	public void setApiResponse() {
		apiResponse = new ApiResponse(Boolean.FALSE,"File Format not supported");
	}
	
}
