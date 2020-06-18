package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.UserDao;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.model.User;

public class UserService implements UserInterface {

		static UserDao userImpl=new UserDaoImpl();
		private static Logger logger= Logger.getLogger(UserService.class);
		
		//Check Credentials
		public int checkCredentials(User user) {
			
			int roleId=0;
			try {
				roleId = userImpl.checkCredentials(user);
			} catch (UserNotFoundException e) {
				logger.error(e.getMessage());
			}
			return roleId;
			
		}

		
		//Add User
		public void addUser(User user) {			
			userImpl.addUser(user);			
		}

		//get User Id
		public int getUserid(User user) {
			
			return userImpl.getUserId(user);
		}
		
	
}
