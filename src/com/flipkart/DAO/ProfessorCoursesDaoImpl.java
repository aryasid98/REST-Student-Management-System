package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class ProfessorCoursesDaoImpl implements ProfessorCoursesDao {
	private static Logger logger= Logger.getLogger(ProfessorCoursesDaoImpl.class);

	//Add Courses to List of Professor
	@Override
	public void addCoursesToTeach(int profId,int courseId) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.ADD_PROFESSOR_COURSES;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, profId);
			stmt.setInt(2, courseId);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new CourseNotFoundException();
		}
				
	}
	
	//Delete course from List of Professor
	@Override
	public void deleteCoursesToTeach(int profId, int no) throws CourseNotFoundException {
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.DELETE_PROFESSOR_COURSES;	
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, profId);
			stmt.setInt(2, no);
			int row=stmt.executeUpdate();
			if(row==0)
				throw new CourseNotFoundException();
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
	}

	//View List of students under professor
	@Override
	public List<Report> viewStudentsList(int userId) {
		// TODO Auto-generated method stub
		List<Report> studentList=new ArrayList<Report>();
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.VIEW_STUDENTS; 
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){	
			int courseId= rs.getInt("courseId");
			int studentId=rs.getInt("studentId");
			Report report=new Report();
			report.courseId=courseId;
			report.studentId=studentId;
			studentList.add(report);
			}
			return studentList;
	       
			
		} catch (SQLException e) {
			logger.info("Hello");
			logger.error(e.getMessage());
		}
		
		
		return null;
	}	


	//View Enrolled Courses
	@Override
	public List<Course> viewEnrolledCourses(int profId) {
		List <Course> enrolledList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {
			String sql=SQLConstantsQuery.VIEW_PROFESSOR_ENROLLED_COURSES;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, profId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){	
		     int courseId= rs.getInt("courseId");
			String courseName=rs.getString("courseName");
	        Course course=new Course();
	         course.courseId=courseId;
	         course.courseName=courseName;
	         enrolledList.add(course);         
			}
			  return enrolledList;  
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}


}
