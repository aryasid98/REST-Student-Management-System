package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.model.Student;
import com.flipkart.model.User;

public interface StudentInterface {
	
	//View Course Catalog
	public List<Course> viewCatalog() ;
		
	//Add Course
	public void addCourse(int studentId, int courseId) throws CourseNotFoundException ;

	//Register Student
	public void register(Student student) ;

	//View Enrolled Courses
	public List<Course> viewEnrolledCourses(int userId);

	//Delete Course
	public void deleteCourse(int studentId, int courseId) throws CourseNotFoundException ;

	//Calculate Fees
	public double calculateFees(int studentId) ;

	//Payment Method
	public void payment(int studentId, int mode);
		
	//Check if Student is registered 
	public boolean isRegistered(int studentId) ;

	//View Report Card
	public List<Report> viewReport(int studentId);
	
	//Fetch Payment Modes
	public HashMap<Integer, String> paymentModes();

		
	//Get User Id
	public int getUserid(User user) ;




}
