package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;

public interface ProfessorInterface {
	//View Catalog
	public List<Course> viewCatalog();
	
	//Add courses to Professor teaching List
	public void addCourseToTeach(int userId, int id) throws CourseNotFoundException ;	
	
		
	//View Student List under Professor
	public List<Report>  viewStudentsList(int userId) ;
		
		
	//Delete Course from Professor Course List
	public void deleteCourseToTeach(int userId, int no) throws CourseNotFoundException ;
		
	//View Courses under Professor
	public List<Course> viewEnrolledCourses(int userId);
		
	//Upload  Grades of Students
	public void uploadGrades(int id, int cid, String grades) ;
	
	//Check if Student is Registered
	public boolean isRegistered(int id) ;
	
	//View Report
	public List<Report> viewReport(int id);

	
	
	
}
