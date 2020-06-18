package com.flipkart.client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.flipkart.model.Admin;
import com.flipkart.model.Catalog;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.service.AdminService;
import com.flipkart.service.StudentService;

public class AdminClient {
	
	private static Logger logger= Logger.getLogger(AdminClient.class);
	AdminService adminService=new AdminService();
	StudentService studentService=new StudentService();

	
	//Admin Options
	public void display(int adminId) {
		logger.info("\n---ADMIN OPTIONS--- \n1. Add User \n2. Delete User \n3. See List of Users \n"
				+ "4. View Catalog\n5. Add Course in Catalog\n6. Delete Course in Catalog\n7. Logout" );
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		sc.nextLine();
		while(option>=1 && option<=7)
		{
			switch(option) {
			case 1:
				logger.info("\nEnter Username");
				String userName=sc.nextLine();
				logger.info("\nEnter Password");
				String password=sc.nextLine();
				logger.info("\nEnter RoleId");
				int roleId=sc.nextInt();
				sc.nextLine();
				
				User user=new User();
				user.username=userName;
				user.password=password;
				user.roleId=roleId;
				
				
				
				logger.info("\nEnter Name");
				String name=sc.nextLine();
				logger.info("\nEnter Gender");
				String gender=sc.nextLine();
				logger.info("Enter Contact No");
				String contact=sc.nextLine();				
				
				adminService.addUser(user);
				int userId=adminService.getUserid(user);
				logger.info("User Id : " +  userId);
				assign(roleId,userId,userName,name,contact,gender);
				break;
			case 2:
				logger.info("\nEnter Id to Delete");
				int id=sc.nextInt();
				sc.nextLine();
				adminService.deleteUser(id);
				break;
			case 3:
				logger.info("\nSelect user role to see list\n1.Student\n2.Professor\n");
				int choice;
				choice=sc.nextInt();
				sc.hasNextLine();
				if(choice==1) {
					List<Student> studentList=adminService.listStudents(); 
					logger.info(
					studentList
					.stream()
					.flatMap(student-> Stream.of(student.getGender().equals("M")?"\nMr. ".concat(student.getName()):"\nMs. ".concat(student.getName()),"StudentId: ".concat(Integer.toString(student.getStudentId()))))
					.collect(Collectors.joining(" "))
					);	
				}
				else if(choice==2) {
					List<Professor> professorList=adminService.listProfessor(); 
					logger.info(
							professorList.stream().
							flatMap(professor-> Stream.of(professor.getGender().equals("M")?"\nMr. ".concat(professor.getProfName()):"\nMs. ".concat(professor.getProfName()),"ProfessorId: ".concat(Integer.toString(professor.getProfId())))).
							collect(Collectors.joining(" "))
						);
				}
				else
					logger.error("Try Again");				
					break;
			case 4:
				List<Course> courseList=adminService.viewCatalog();
				courseList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				break;
			case 5:
				Catalog catalog=new Catalog();
				logger.info("Enter Course Id");
				catalog.courseId=sc.nextInt();
				sc.nextLine();
				logger.error("Enter Course Name");
				catalog.courseName=sc.nextLine();
				logger.error("Enter Fee of Course");
				catalog.fee=sc.nextInt();
				sc.nextLine();
				adminService.addCourse(catalog);
				break;
			case 6:
				logger.info("Enter Course Id to delete");
				int cId=sc.nextInt();
				sc.nextLine();
				adminService.deleteCourse(cId);
				break;
			case 7:
				return;
			
				
			
				
				
			}
			logger.info("Enter option to do further else enter anything except(1-6)");
			logger.info("\n---ADMIN OPTIONS--- \n1. Add User \n2. Delete User \n3. See List of Users \n"
					+ "4. View Catalog\n5. Add Course in Catalog\n6. Delete Course in Catalog\n7. Logout" );
			option=sc.nextInt();
			sc.nextLine();
		}
	}

	//Assign details to specific user
	private void assign(int roleId,int userId,String username, String name, String contact, String gender) {
		// TODO Auto-generated method stub
		if(roleId==3) {
				Student student=new Student();
				student.username=username;
				student.name=name;
				student.contactNo=contact;
				student.gender=gender;
				student.studentId=userId;
				studentService.register(student);
		}
		else if(roleId==2) {
			Professor professor=new Professor();
			professor.username=username;
			professor.profName=name;
			professor.contactNo=contact;
			professor.gender=gender;
			professor.profId=userId;
			adminService.register(professor);
		}
		else if(roleId==1) {
			Admin admin=new Admin();
			admin.username=username;
			admin.name=name;
			admin.contactNo=contact;
			admin.gender=gender;
			admin.adminId=userId;
			adminService.register(admin);
		}
		else {
			logger.error("Enter Proper Role and try again");
			return;
		}

	
		
		
	}

	
}
