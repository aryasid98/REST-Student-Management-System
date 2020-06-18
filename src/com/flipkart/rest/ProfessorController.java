package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.flipkart.service.ProfessorService;

//Professor Rest Controller
@Path("/professor")
public class ProfessorController {
	private static Logger logger= Logger.getLogger(ProfessorController.class);
	ProfessorService professorService= new ProfessorService();
	
	//View list of students under professor
	@GET
	@Path("/viewStudentList/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getStudentList	(@PathParam("professorId") int professorId){
		List<Report> studentList= professorService.viewStudentsList(professorId);
		return studentList;
	}
	
	//Upload grades of students 
	@PUT
	@Path("/uploadGrades/{studentId}/{courseId}/{grade}")
	@Consumes("application/json")
	public Response updateCourse(@PathParam("studentId") int studentId,@PathParam("courseId")int courseId,@PathParam("grade") String grade) {
	
		
		String result="";
		if(!professorService.isRegistered(studentId)) {
			result = (studentId + " is	 not Registered!");
			return Response.status(200).entity(result).build();	
		}
		
		professorService.uploadGrades(studentId,courseId,grade);
		
		result="Record Updated.";
		
		return Response.status(200).entity(result).build();	
	}
	
	//Add course in Professor's List
	@POST
	@Path("/addCourse/{professorId}/{courseId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("professorId") int professorId,@PathParam("courseId") int courseId){
		
		try {
			professorService.addCourseToTeach(professorId,courseId);
		} catch (CourseNotFoundException e) {
			logger.error(e.getMessage());
		}
		String result="Course added: " + courseId;
		
		return Response.status(201).entity(result).build();	
		
	}
	
	//Delete Course from Professor's List
	@DELETE
	@Path("/deleteCourse/{professorId}/{courseId}")
	public Response deleteStudentCourse(@PathParam("professorId") int professorId,@PathParam("courseId")int courseId) throws URIReferenceException{
	
		try {
			professorService.deleteCourseToTeach(professorId,courseId);
		} catch (CourseNotFoundException e) {
			logger.error(e.getMessage());
		}
		
		String result="Course Id: " + courseId + " deleted.";
		return Response.status(200).entity(result).build();	
	}
	
	//VIew Professor's Courses
	@GET
	@Path("/enrolledCourses/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getEnrolledCourses	(@PathParam("professorId") int professorId){
		return professorService.viewEnrolledCourses(professorId);
	}
	
	
	
}
