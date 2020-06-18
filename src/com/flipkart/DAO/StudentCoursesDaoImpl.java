package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class StudentCoursesDaoImpl implements StudentCoursesDao{
	
	private static Logger logger= Logger.getLogger(StudentCoursesDaoImpl.class);
	
	
	//View Enrolled Courses
		@Override
		public List<Course> viewEnrolledCourses(int studentId) {
			// TODO Auto-generated method stub
			List <Course> enrolledList=new ArrayList<>();
			Connection connect=DBUtil.getConnection();
			try {
				String sql=SQLConstantsQuery.VIEW_ENROLLED_COURSES;
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setInt(1, studentId);
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

		//Add course in student List
		@Override
		public void addCourse(int studentId, int courseId) throws CourseNotFoundException{
			// TODO Auto-generated method stub
			Connection connect=DBUtil.getConnection();
			try {
				String sql=SQLConstantsQuery.ADD_STUDENT_COURSE;
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setInt(1, studentId);
				stmt.setInt(2, courseId);
				stmt.setString(3,null);
				stmt.executeUpdate();				
				
			} catch (SQLException e) {
				throw new CourseNotFoundException();
				//logger.error(e.getMessage());
			}
			
			
			
		}

		//Drop Course from student List
		@Override
		public void deleteCourse(int studentId, int courseId) throws CourseNotFoundException {
			// TODO Auto-generated method stub
			Connection connect=DBUtil.getConnection();
			String query = SQLConstantsQuery.DELETE_STUDENT_COURSE;
			
			try {
				PreparedStatement stmt = connect.prepareStatement(query);
				stmt.setInt(1, courseId);
				stmt.setInt(2, studentId);
				int row=stmt.executeUpdate();
				if(row==0) {
					throw new CourseNotFoundException();
				}
			} catch (SQLException e) {
				
				logger.error(e.getMessage());
			}
			
		}

		//Calculate fees for registration
		@Override
		public double calculateFees(int studentId) {
			Connection connect=DBUtil.getConnection();
			String query = SQLConstantsQuery.CALCULATE_FEE;
			double fee=0;
			try {
				PreparedStatement stmt = connect.prepareStatement(query);
				stmt.setInt(1,studentId);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){					
			    fee= rs.getDouble("fee");
				}
				return fee;
				
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			return 0;
			
			
		}

		//Upload Grades to report Card
		@Override
		public void uploadGrades(int studentId, int cid, String grades) {
			Connection connect=DBUtil.getConnection();
			String query = SQLConstantsQuery.UPLOAD_GRADES;
			
			try {
				PreparedStatement stmt = connect.prepareStatement(query);
				stmt.setString(1,grades);
				stmt.setInt(2, studentId);
				stmt.setInt(3, cid);
				stmt.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			
		}

		//View Report Card
		@Override
		public List<Report> viewReport(int studentId) {
			List <Report> reportList=new ArrayList<>();
			Connection connect=DBUtil.getConnection();
			String query = SQLConstantsQuery.VIEW_REPORT;
			double fee=0;
			try {
				PreparedStatement stmt = connect.prepareStatement(query);
				stmt.setInt(1, studentId);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){					
			    int courseId= rs.getInt("courseId");
			    String grades=rs.getString("grades");
			    Report report =new Report();
			    report.courseId=courseId;
			    report.grades=grades;
			    report.studentId=studentId;
			    reportList.add(report);
			    
				}
				return reportList;
				
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			
			return null;
		}

}
