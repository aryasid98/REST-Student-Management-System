package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.model.Course;
import com.flipkart.model.Student;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class StudentDaoImpl implements StudentDao{
	private static Logger logger= Logger.getLogger(StudentDaoImpl.class);
	
	//Register student
	@Override
	public void register(Student student) {
		
		Connection connect=DBUtil.getConnection();
		try {
			String sql=SQLConstantsQuery.ADD_STUDENT;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, student.getStudentId());
			stmt.setString(2, student.getUsername());
			stmt.setString(3, student.getName());
			stmt.setString(4, student.getContactNo());
			stmt.setString(5, student.getGender());
			stmt.executeUpdate();
			return;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
	}

	//List of Students
	@Override
	public List<Student> listStudent() {
		List <Student> studentList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.VIEW_STUDENT_LIST;
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){		
		     String studentName= rs.getString("name");
			 int studentId = rs.getInt("studentId");
			 String gender = rs.getString("gender");
			 String contactNo=rs.getString("contactNo");
			 String username=rs.getString("username");
	         //logger.info("ID: " + studentName + ", Name: " + username + " " + gender);
	         Student student=new Student();
	         student.name=studentName;
	         student.studentId=studentId;
	         student.gender=gender;
	         student.username=username;
	         student.contactNo=contactNo;
	         studentList.add(student);
	         }
			
	       return studentList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;

	}

}
