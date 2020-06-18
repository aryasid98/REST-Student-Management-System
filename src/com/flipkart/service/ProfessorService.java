package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import com.flipkart.DAO.CatalogDao;
import com.flipkart.DAO.CatalogDaoImpl;
import com.flipkart.DAO.ProfessorCoursesDao;
import com.flipkart.DAO.ProfessorCoursesDaoImpl;
import com.flipkart.DAO.RegisterationDao;
import com.flipkart.DAO.RegisterationDaoImpl;
import com.flipkart.DAO.StudentCoursesDao;
import com.flipkart.DAO.StudentCoursesDaoImpl;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;

public class ProfessorService implements ProfessorInterface {

	CatalogDao catalogImpl=new CatalogDaoImpl();
	ProfessorCoursesDao professorCoursesImpl=new ProfessorCoursesDaoImpl();
	StudentCoursesDao studentCoursesImpl=new StudentCoursesDaoImpl();
	RegisterationDao registerationImpl=new RegisterationDaoImpl();
	
	//View Catalog
	public List<Course> viewCatalog() {
		// TODO Auto-generated method stub
		List<Course> courseList=catalogImpl.viewCatalog();
		return courseList;
	}
	
	//Add courses to Professor teaching List
	public void addCourseToTeach(int userId, int id) throws CourseNotFoundException {		
		professorCoursesImpl.addCoursesToTeach(userId,id);		
	}
	
	//View Student List under Professor
	public List<Report>  viewStudentsList(int userId) {
		// TODO Auto-generated method stub
		List<Report> studentList =professorCoursesImpl.viewStudentsList(userId);
		return studentList;
	}
	

	//Delete Course from Professor Course List
	public void deleteCourseToTeach(int userId, int no) throws CourseNotFoundException {
		professorCoursesImpl.deleteCoursesToTeach(userId,no);
	}
	
	//View Courses under Professor
	public List<Course> viewEnrolledCourses(int userId) {
		// TODO Auto-generated method stub
		List<Course> enrolledList=professorCoursesImpl.viewEnrolledCourses(userId);
		return enrolledList;
	}
	
	//Upload  Grades of Students
	public void uploadGrades(int id, int cid, String grades) {		
		studentCoursesImpl.uploadGrades(id,cid,grades);
	}
	
	//Check if Student is registered
	public boolean isRegistered(int id) {
		return registerationImpl.isRegistered(id);
	}
	
	//View report of Student
	public List<Report> viewReport(int id) {
		return studentCoursesImpl.viewReport(id);
	}



}
