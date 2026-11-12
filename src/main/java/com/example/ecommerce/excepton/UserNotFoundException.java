package com.example.ecommerce.excepton;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message) {
		super(message);
		
	}

}
