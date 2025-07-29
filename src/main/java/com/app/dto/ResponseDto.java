package com.app.dto;

public class ResponseDto {

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDto(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseDto() {

	}

}
