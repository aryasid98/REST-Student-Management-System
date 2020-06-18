package com.flipkart.DAO;

import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.model.User;

public interface UserDao {

	//Check Credentials of user to login
	public int checkCredentials(User user) throws UserNotFoundException;
	
	//public void register(User user);

	//Add User to userList
	public void addUser(User user);

	public int getUserId(User user);
	
	
}
