package com.piseth.java.school.spring_webflux_demo.exception;

public class InputFailedValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177704628756262427L;
	private static final int ERROR_CODE = 100;
	private static final String MESSAGE = "Allowed range is between 10 - 20";
	private int input;
	
	public InputFailedValidationException(int input){
		super(MESSAGE);
		this.input = input;
	}
	
	public int getInput() {
		return input;
	}

	public int getErrorCode() {
		return ERROR_CODE;
	}

}
