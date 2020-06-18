package com.flipkart.service;

import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.model.User;

public interface UserInterface {
	
	//Check Credentials
	public int checkCredentials(User user) ;

	//Add User
	public void addUser(User user);

	//Get User Id
	public int getUserid(User user);
}
