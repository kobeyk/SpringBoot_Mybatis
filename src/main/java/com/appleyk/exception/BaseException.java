package com.appleyk.exception;


import com.appleyk.result.ResponseMessage;

public class BaseException extends Exception{

	private static final long serialVersionUID = 1636053543616552109L;
	
	private Integer errorCode;
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(ResponseMessage responseMessage) {
		super(responseMessage.getMessage());
		this.errorCode = responseMessage.getStatus();
	}
	public BaseException(ResponseMessage responseMessage, String message) {
		super(message);
		this.errorCode = responseMessage.getStatus();
	}
	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
