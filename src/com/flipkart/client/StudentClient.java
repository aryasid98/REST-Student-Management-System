package com.flipkart.client;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.service.StudentService;
import com.flipkart.service.UserService;

public class StudentClient {
	
	//Logger
	private static Logger logger= Logger.getLogger(StudentClient.class);
		
	StudentService studentService=new StudentService();
	UserService userService=new UserService();
	
	//Display Student Options
	public void display(int studentId) {
		
		logger.info("\n---STUDENT OPTIONS---\n1. Add Course \n2. Drop Course \n3. View Catalog \n"
				+ "4. View Courses Enrolled \n5. Register and Payment \n6. View Report Card\n7. Logout ");
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		sc.nextLine();
		while(option>=1 && option<=7)
		{
			switch(option) {
			case 1:
				List<Course> enrolledList=studentService.viewEnrolledCourses(studentId);
				//enrolledList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				int enrolledCourses=enrolledList.size();
				if(studentService.isRegistered(studentId))//Already Registered
					logger.info("Already Registered");
				else if(enrolledCourses==4)
				{
					logger.info("You have already added 4 courses,you may proceed forr Payment and Registeration.");
				}
				else
				{
					logger.info("Select course id");
					int courseId=sc.nextInt();
					sc.nextLine();
					try {
						studentService.addCourse(studentId,courseId);
					} catch (CourseNotFoundException e) {
						logger.error(e.getMessage());
					}
				}
				
				break;
			case 2:
				if(studentService.isRegistered(studentId))//Already Registered
					logger.info("Already Registered");
				else {
					logger.info("Select course id to Delete");
					int courseId=sc.nextInt();
					sc.nextLine();
					try {
						studentService.deleteCourse(studentId,courseId);
					} catch (CourseNotFoundException e) {
						logger.error(e.getMessage());
					}
				}
				
				break;
			case 3:
				List<Course> courseList=studentService.viewCatalog();
				courseList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				break;
			case 4:
				enrolledList=studentService.viewEnrolledCourses(studentId);
				enrolledList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				break;
			case 5:
				enrolledList=studentService.viewEnrolledCourses(studentId);
				enrolledCourses=enrolledList.size();
				if(enrolledCourses!=4)
				{
					logger.info("You need to select 4 courses!!");
				}
				else {
						if(studentService.isRegistered(studentId))//Already Registered
							logger.info("Already Registered");
						else {
							double fee=studentService.calculateFees(studentId);
							logger.info("Fees to be Paid: " + fee + "\nWish to proceed for payment(Y/N)");
							char c=sc.next().charAt(0); 
							if(c=='Y'||c=='y')
							{
								HashMap<Integer, String> paymentModes=studentService.paymentModes();
								logger.info("Payment Id" + " "+ "Mode");
								paymentModes.forEach((i,j)->logger.info(i + "	  " + j));
								int mode=sc.nextInt();
								sc.nextLine();
								studentService.payment(studentId,mode);
								logger.info("Fees Paid , Registered!!");
							}	
						}		
				}
				break;
			case 6:
				if(!studentService.isRegistered(studentId))
					logger.info("You are not Registered!");
				else {
					logger.info("Course_Id Grades");
					List<Report> reportList=studentService.viewReport(studentId);
					reportList.forEach(report->logger.info(report.getCourseId() + "        "+ report.getGrades()));
					}
				break;
			case 7:
				return;
					
				
			}
			logger.info("Enter option to do further else enter anything except(1-5)");
			logger.info("\n---STUDENT OPTIONS---\n1. Add Course \n2. Drop Course \n3. View Catalog \n"
					+ "4. View Courses Enrolled \n5. Register and Payment \n6. View Report Card\n7. Logout ");
			option=sc.nextInt();
			sc.nextLine();
		}
		
		
	}

	//Student Registration
	public void register() {
		
		User user=new User();
		Student student=new Student();
		Scanner sc=new Scanner(System.in);
		String username,gender,name,password,contactNo;
		logger.info("Enter Username");
		username=sc.nextLine();
		logger.info("Enter Password");
		password = sc.nextLine();
		user.password=password;
		
		user.password=password;
		user.username=username;
		user.roleId=3;
		
		
		logger.info("Enter Name");
		name = sc.nextLine();
		logger.info("Enter Contact No.");
		contactNo = sc.nextLine();
		logger.info("Enter Gender");
		gender = sc.nextLine();
		
		userService.addUser(user);
		int userId=studentService.getUserid(user);
		logger.info("User id: " + userId);		
		
		student.username=username;
		student.contactNo=contactNo;
		student.gender=gender;
		student.name=name;		
		student.studentId=userId;
		studentService.register(student);
		return;		
		
	}

}
