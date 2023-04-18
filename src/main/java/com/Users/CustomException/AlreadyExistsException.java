package com.Users.CustomException;

public class AlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AlreadyExistsException() {
		
	}
	
	public AlreadyExistsException(String message) {
		super(message);
	}

}
