package com.flipkart.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.model.Course;
import com.flipkart.model.Student;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.utils.DBUtil;

public class CatalogDaoImpl implements CatalogDao {
	private static Logger logger= Logger.getLogger(CatalogDaoImpl.class);
	
	
	//View Catalog
	@Override
	public List<Course> viewCatalog() {
		// TODO Auto-generated method stub
		List <Course> courseList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.VIEW_CATALOG;
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){					
		     int courseId= rs.getInt("courseId");
			 String courseName = rs.getString("courseName");
	         //logger.info("ID: " + courseId + ", Name: " + courseName);
	         Course course=new Course();
	         course.courseId=courseId;
	         course.courseName=courseName;
	         courseList.add(course);
	         }
			
	       return courseList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}


	




}
