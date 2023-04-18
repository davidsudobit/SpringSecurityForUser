package com.Users.CustomException;

public class NotAuthorizedException extends Exception{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException() {
		
	}
	
	public NotAuthorizedException(String message) {
		super(message);
	}

}
