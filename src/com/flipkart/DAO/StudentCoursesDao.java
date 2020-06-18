package com.flipkart.DAO;

import java.util.List;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;

public interface StudentCoursesDao {
	
	//View Enrolled Courses
	public List<Course> viewEnrolledCourses(int userId);

	//Add course in student List
	public void addCourse(int studentId, int courseId) throws CourseNotFoundException;

	//Drop Course from student List
	public void deleteCourse(int studentId, int courseId) throws CourseNotFoundException;

	//Calculate fees for registration
	public double calculateFees(int studentId);

	//Upload Grades to report Card
	public void uploadGrades(int id, int cid, String grades);

	//View Report Card
	public List<Report> viewReport(int studentId);
	
	
}
