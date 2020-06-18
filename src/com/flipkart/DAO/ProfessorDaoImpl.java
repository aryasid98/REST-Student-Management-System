package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.utils.DBUtil;

public class ProfessorDaoImpl implements ProfessorDao {
	private static Logger logger= Logger.getLogger(ProfessorDaoImpl.class);

	//List of professors
	@Override
	public List<Professor> listProfessor() {
		List <Professor> professorList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {
			
//			String sql=SQLConstantsQuery.VIEW_CATALOG;
			String sql=SQLConstantsQuery.VIEW_PROFESSOR_LIST;
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){					
		     String profname= rs.getString("name");
			 int profId = rs.getInt("profId");
			 String gender = rs.getString("gender");
	         //logger.info("ID: " + courseId + ", Name: " + courseName);
			 Professor professor=new Professor();
	         professor.profName=profname;
	         professor.profId=profId;
	         professor.gender=gender;
	         professorList.add(professor);
	         }
			
	       return professorList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	//register Professor
	@Override
	public void register(Professor professor) {
		
		Connection connect=DBUtil.getConnection();
		try {
		// TODO Auto-generated method stub
			String sql=SQLConstantsQuery.ADD_PROFESSOR;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, professor.getProfId());
			stmt.setString(2, professor.getUsername());
			stmt.setString(3, professor.getProfName());
			stmt.setString(4, professor.getContactNo());
			stmt.setString(5, professor.getGender());
			stmt.executeUpdate();
	       //System.out.println(role);  
	       
	       //return role;
			return;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}	
		
		
	}


	

}
