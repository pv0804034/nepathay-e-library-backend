package com.nepathya.archive.system.common;

import com.nepathya.archive.system.common.enums.ErrorCode;

public class Error {
	public Error(String errorMessage, ErrorCode errorCode, String message) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorDesription = message;
	}

	private String errorMessage;

	private String errorDesription;

	private ErrorCode errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDesription() {
		return errorDesription;
	}

	public void setErrorDesription(String errorDesription) {
		this.errorDesription = errorDesription;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
