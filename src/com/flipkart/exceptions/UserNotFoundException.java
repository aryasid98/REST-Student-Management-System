package com.flipkart.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	
	String username;
	public UserNotFoundException(String username){
		this.username=username;
	}
	
	public String getMessage()
	{
		return	"Wrong Credentials, Try Again!";
	}
	
	
}
