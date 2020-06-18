package com.flipkart.DAO;

import java.util.List;

import com.flipkart.model.Professor;

public interface ProfessorDao {

	//List of Professors
	public List<Professor> listProfessor();

	//Register Professor
	public void register(Professor professor);
}
