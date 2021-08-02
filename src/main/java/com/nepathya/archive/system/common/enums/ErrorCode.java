package com.nepathya.archive.system.common.enums;

import org.springframework.lang.Nullable;

public enum ErrorCode {
	ERROR(101, "Not Accepted"), OK(102, "Success");

	private final int value;

	private final String reasonPhrase;

	ErrorCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	@Override
	public String toString() {
		return Integer.toString(this.value);
	}

	public static ErrorCode valueOf(int statusCode) {
		ErrorCode status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}

	@Nullable
	public static ErrorCode resolve(int statusCode) {
		for (ErrorCode status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}
}
