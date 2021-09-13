package com.stacksimplify.exceptions;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

	
	
	
}
