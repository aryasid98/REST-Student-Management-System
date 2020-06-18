package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;



public class UserDaoImpl implements UserDao {
	private static Logger logger= Logger.getLogger(UserDaoImpl.class);
	

	//Check Credentials of user to login
	public int checkCredentials(User user) throws UserNotFoundException {
		Connection connect=DBUtil.getConnection();
		try {
			String sql = SQLConstantsQuery.CHECK_CREDENTIALS;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();			
	
			int role=0;
			while (rs.next()) {
			role = rs.getInt("roleId");
			}
	       if(role==0)
	       {
	    	   throw new UserNotFoundException(user.username);
	       }
	       return role;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}


	//Add user to user List
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql = SQLConstantsQuery.ADD_USER;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getRoleId());
			stmt.executeUpdate();
	       	return;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	
	//Get User Id to add Particular user
	@Override
	public int getUserId(User user) {
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql = "Select userId from user where username=? and password=?";
			//String sql=SQLConstantsQuery.ADD_STUDENT;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();			
			int userId=0;
			while (rs.next()) {
			userId = rs.getInt("userId");
			}
	       	return userId;
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}


}
