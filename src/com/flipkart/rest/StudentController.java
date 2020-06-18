package com.flipkart.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Report;
import com.flipkart.service.StudentService;
import com.flipkart.service.UserService;

//Student Rest Controller
@Path("/student")
public class StudentController {

	private static Logger logger= Logger.getLogger(StudentController.class);
	
	StudentService studentService=new StudentService();
	UserService userService=new UserService();
	
	//Add course in student list
	@POST
	@Path("/addStudentCourse/{studentId}/{courseId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentCourse(@PathParam("studentId") int studentId,@PathParam("courseId") int courseId){
		
		try {
			studentService.addCourse(studentId,courseId);
		} catch (CourseNotFoundException e) {
			logger.error(e.getMessage());
		}
		String result="Course added: " + courseId;
		
		return Response.status(201).entity(result).build();	
		
	}

	//Delete course in student list
	@DELETE
	@Path("/deleteStudentCourse/{studentId}/{courseId}")
	public Response deleteStudentCourse(@PathParam("studentId") int studentId,@PathParam("courseId")int courseId) throws URIReferenceException{
	
		try {
			studentService.deleteCourse(studentId,courseId);
		} catch (CourseNotFoundException e) {
			logger.error(e.getMessage());
		}
		
		String result="Course Id: " + courseId + " deleted.";
		return Response.status(200).entity(result).build();	
	}
	

	//View Enrolled Courses
	@GET
	@Path("/enrolledCourses/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getEnrolledCourses	(@PathParam("studentId") int studentId){
		List<Course> enrolledList=studentService.viewEnrolledCourses(studentId);
		return enrolledList;
	}
	
	//Register student with Payment Mode
	@POST
	@Path("/register/{studentId}/{mode}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@PathParam("studentId") int studentId, @PathParam("mode") int mode){
		
		List<Course> enrolledList=studentService.viewEnrolledCourses(studentId);
		int enrolledCourses=enrolledList.size();
		String result="";
		if(enrolledCourses!=4)
		{
			result=("You need to select 4 courses!!");
		}
		else {
				if(studentService.isRegistered(studentId))//Already Registered
					result=  ("Already Registered");
				else {
					double fee=studentService.calculateFees(studentId);
					result = ("Fees to be Paid: " + fee);
						studentService.payment(studentId,mode);
						result+=("\nFees Paid , Registered!!");
				}		
		}
		
		
		return Response.status(201).entity(result).build();	
		
	}
	
	
	//View Report Card
	@GET
	@Path("/report/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getReport(@PathParam("studentId") int studentId){
		if(!studentService.isRegistered(studentId))
			logger.info("You are not Registered!");
		else {
			logger.info("Course_Id Grades");
			List<Report> reportList=studentService.viewReport(studentId);
			return reportList;
			}
		return null;
		
	}
	
	
	
	
	
}
