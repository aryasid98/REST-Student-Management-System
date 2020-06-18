package com.flipkart.service;

import java.util.List;

import com.flipkart.DAO.AdminDao;
import com.flipkart.DAO.AdminDaoImpl;
import com.flipkart.DAO.CatalogDao;
import com.flipkart.DAO.CatalogDaoImpl;
import com.flipkart.DAO.ProfessorDaoImpl;
import com.flipkart.DAO.ProfessorDao;
import com.flipkart.DAO.StudentDao;
import com.flipkart.DAO.StudentDaoImpl;
import com.flipkart.DAO.UserDao;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.model.Admin;
import com.flipkart.model.Catalog;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;

public class AdminService implements AdminInterface{

	CatalogDao catalogImpl=new CatalogDaoImpl();
	UserDao userImpl=new UserDaoImpl();
	AdminDao adminImpl=new AdminDaoImpl();
	StudentDao studentImpl=new StudentDaoImpl();
	ProfessorDao professorImpl=new ProfessorDaoImpl();
	
	//View Course catalog
	public List<Course> viewCatalog() {
		// TODO Auto-generated method stub
		List<Course> courseList=catalogImpl.viewCatalog();
		return courseList;
	}
	
	//Add User to user List
	public void addUser(User user) {
		userImpl.addUser(user);
	}
	
	//Delete User to user List
	public void deleteUser(int id) {
		adminImpl.deleteUser(id);	
	}
	
	//Add Course to Catalog
	public void addCourse(Catalog catalog) {
		adminImpl.addCourse(catalog);
	}
	
	//Delete Coursefrom Catalog
	public void deleteCourse(int cId) {
		// TODO Auto-generated method stub
		adminImpl.deleteCourse(cId);
		
	}
	
	//View List of Students
	public List<Student> listStudents() {
		List<Student> studentList=studentImpl.listStudent();
		return studentList;
	}
	
	//View List of Professors
	public List<Professor> listProfessor() {
		List<Professor> professorList=professorImpl.listProfessor();
		return professorList;
	}

	//Register Professor
	public void register(Professor professor) {
		// TODO Auto-generated method stub
		professorImpl.register(professor);
	}
	
	//Register Admin
	public void register(Admin admin) {
		// TODO Auto-generated method stub
		adminImpl.register(admin);
		
	}

	//Get User Id
	public int getUserid(User user) {
		// TODO Auto-generated method stub
		return userImpl.getUserId(user);
	}

	public void register(Student student) {

		studentImpl.register(student);
		
	}

}
