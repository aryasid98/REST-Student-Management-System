package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CatalogDao;
import com.flipkart.DAO.CatalogDaoImpl;
import com.flipkart.DAO.PaymentDao;
import com.flipkart.DAO.PaymentDaoImpl;
import com.flipkart.DAO.RegisterationDao;
import com.flipkart.DAO.RegisterationDaoImpl;
import com.flipkart.DAO.StudentCoursesDao;
import com.flipkart.DAO.StudentCoursesDaoImpl;
import com.flipkart.DAO.StudentDao;
import com.flipkart.DAO.StudentDaoImpl;
import com.flipkart.DAO.UserDao;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.model.Student;
import com.flipkart.model.User;

public class StudentService implements StudentInterface{
	StudentDao studentImpl=new StudentDaoImpl();
	CatalogDao catalogImpl=new CatalogDaoImpl();
	UserDao userImpl=new UserDaoImpl();
	StudentCoursesDao studentCoursesImpl=new StudentCoursesDaoImpl();
	RegisterationDao registerationImpl=new RegisterationDaoImpl();
	PaymentDao paymentImpl=new PaymentDaoImpl();
	
	//View Course Catalog
	public List<Course> viewCatalog() {
		List<Course> courseList=catalogImpl.viewCatalog();
		return courseList;		
	}
	
	//Add Course
	public void addCourse(int studentId, int courseId) throws CourseNotFoundException {		
		studentCoursesImpl.addCourse(studentId,courseId);
	}

	//Register Student
	public void register(Student student) {
		studentImpl.register(student);
		
		return;
	}

	//View Enrolled Courses
	public List<Course> viewEnrolledCourses(int userId) {
		List<Course> enrolledList=studentCoursesImpl.viewEnrolledCourses(userId);
		return enrolledList;
	}

	//Delete Course
	public void deleteCourse(int studentId, int courseId) throws CourseNotFoundException {
		studentCoursesImpl.deleteCourse(studentId,courseId);
	}

	//Calculate Fees
	public double calculateFees(int studentId) {
		// TODO Auto-generated method stub
		return studentCoursesImpl.calculateFees(studentId);
	}

	//Payment Method
	public void payment(int studentId, int mode) {
		registerationImpl.payment(studentId,mode);
	}
	
	//Check if Student is registered 
	public boolean isRegistered(int studentId) {
		// TODO Auto-generated method stub
		return registerationImpl.isRegistered(studentId);
	}

	//View Report Card
	public List<Report> viewReport(int studentId) {
		// TODO Auto-generated method stub
		return studentCoursesImpl.viewReport(studentId);
	}

	//Fetch Payment Modes
	public HashMap<Integer, String> paymentModes() {
		// TODO Auto-generated method stub
		return paymentImpl.paymentModes();
	}

	//Get User Id
	public int getUserid(User user) {
		// TODO Auto-generated method stub
		return userImpl.getUserId(user);
	}





}
