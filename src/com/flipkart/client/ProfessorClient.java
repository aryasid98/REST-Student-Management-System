package com.flipkart.client;

import java.sql.SQLException;
import java.util.List;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.service.ProfessorService;

public class ProfessorClient {
	//Logger
	private static Logger logger= Logger.getLogger(ProfessorClient.class);
	ProfessorService professorService= new ProfessorService();
	
	//Display Professor Options
	public void display(int userId) {
		logger.info("\n---PROFESSOR OPTIONS---\n1. View Catalog \n2. View Student List \n3. Upload Grades\n"
				+ "4. Add Course to Teach\n5. Delete Course to teach\n6. View Enrolled Courses\n7. Logout");
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		sc.nextLine();
		while(option>=1 && option<=6)
		{
			switch(option) {
			case 1:
				List<Course> courseList=professorService.viewCatalog();
				courseList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				break;
			case 2:	
				List<Report> studentList= professorService.viewStudentsList(userId);
				System.out.println(userId);
				logger.info("Course Id" + " "+ "Student Id");
				studentList.forEach(report->logger.info(report.getCourseId() + " " + report.getStudentId()));
				break;
			case 3:
				logger.info("Enter studentdId to upload marks");
				int id=sc.nextInt();
				if(!professorService.isRegistered(id)) {
					logger.info(id + " is	 not Registered!");
					break;
				}
				logger.info("Enter Course ID");
				int cid=sc.nextInt();
				sc.nextLine();
				logger.info("Enter Grades(A+/A/B+/B/C/F)");
				String grades=sc.nextLine();
				professorService.uploadGrades(id,cid,grades);
				
				logger.info("Course_Id Grades");
				List<Report> reportList=professorService.viewReport(id);
				reportList.forEach(report->logger.info(report.getCourseId() + "        "+ report.getGrades()));
				break;
				
			case 4:
				logger.info("Enter id of Course to add");
				int no=sc.nextInt();
				sc.nextLine();
				try {
					professorService.addCourseToTeach(userId,no);
				} catch (CourseNotFoundException e) {
					logger.error(e.getMessage());
				}
				break;
			case 5:
				logger.info("Enter id of Course to delete");
				no=sc.nextInt();
				sc.nextLine();
				try {
					professorService.deleteCourseToTeach(userId,no);
				} catch (CourseNotFoundException e) {
					logger.error(e.getMessage());
				}
				break;
			case 6:
				List<Course> enrolledList=professorService.viewEnrolledCourses(userId);
				enrolledList.forEach(course->logger.info(course.getCourseId() + " " + course.getCourseName()));
				break;
			case 7:
				break;
				
			}
			logger.info("Enter option to do further else enter anything except(1-4)");
			logger.info("\n---PROFESSOR OPTIONS---\n1. View Catalog \n2. View Student List \n3. Upload Grades\n"
					+ "4. Add Course to Teach\n5. Delete Course to teach\n6. View Enrolled Courses\n7. Logout");
			option=sc.nextInt();
			sc.nextLine();
		}
		
	}

	
}
