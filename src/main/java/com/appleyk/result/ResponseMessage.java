package com.appleyk.result;

public enum ResponseMessage {

	/**
	 * 成功
	 */
	OK(200,"成功!"),

	/**
	 * 操作失败
	 */
	FAIL(500, "操作失败!");


	private final int status;
	
	private final String message;
	
	ResponseMessage(int status, String message){
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
}
