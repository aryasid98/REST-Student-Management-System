package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class RegisterationDaoImpl implements RegisterationDao{
	private static Logger logger= Logger.getLogger(RegisterationDaoImpl.class);


	//Payment of Courses
	@Override
	public void payment(int studentId,int mode) {
		// TODO Auto-generated method stub
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.REGISTER_STUDENT;
			PreparedStatement stmt = connect.prepareStatement(sql);
			String instant=DateTimeUtil.getDayofWeek() + " " + DateTimeUtil.getDate()+ " " + DateTimeUtil.getTime();
			stmt.setInt(1,studentId);
			stmt.setString(2, instant);
			stmt.setInt(3,mode);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}


	//Check if student is registered
	@Override
	public boolean isRegistered(int studentId) {
		// TODO Auto-generated method stub
		Connection connect=DBUtil.getConnection();
		try {
		
		String sql=SQLConstantsQuery.IS_REGISTERED;
		PreparedStatement stmt = connect.prepareStatement(sql);
		stmt.setInt(1, studentId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){	
			int exist= rs.getInt("exist");
			return (exist==1);    
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	
		
		return false;
	}

}
