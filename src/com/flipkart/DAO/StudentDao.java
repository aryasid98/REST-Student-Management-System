package com.flipkart.DAO;

import java.util.List;

import com.flipkart.model.Student;

public interface StudentDao {
	
	//Register Student
	public void register(Student student);

	
	//List of Students
	public List<Student> listStudent();
	
	
	
	
}
