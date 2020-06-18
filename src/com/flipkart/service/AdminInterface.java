package com.flipkart.service;

import java.util.List;

import com.flipkart.model.Admin;
import com.flipkart.model.Catalog;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;

public interface AdminInterface {

	public List<Course> viewCatalog();
	
	//Add User to user List
	public void addUser(User user);
		
		
	//Delete User to user List
	public void deleteUser(int id);
	
	//Add Course to Catalog
	public void addCourse(Catalog catalog);
	
	//Delete Course from Catalog
	public void deleteCourse(int cId);
		
	//View List of Students
	public List<Student> listStudents();
		
	//View List of Professors
	public List<Professor> listProfessor() ;

	//Register Professor
	public void register(Professor professor);
	
	//Register Admin
	public void register(Admin admin);

	//Get User Id
	public int getUserid(User user) ;

	
	
}
