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

import com.flipkart.model.Catalog;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.service.AdminService;

//Admin Rest Controller
@Path("/admin")
public class AdminController {
	
	private static Logger logger= Logger.getLogger(AdminController.class);
	
	AdminService adminService=new AdminService();	

	
	//http://localhost:8080/RESTSMS/rest/admin/students
	//View Students
	@GET
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents(){
		
		List<Student> studentList=adminService.listStudents(); 
		return studentList;
	}
	
	//Get List of Professors
	@GET
	@Path("/professors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessor(){
		
		List<Professor> professorList=adminService.listProfessor();
		return professorList;
	}
	
	//Get List of Courses
	@GET
	@Path("/courseList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseList	(){
		
		List<Course> courseList=adminService.viewCatalog();
		return courseList;
	}
	
	//Add Course in Catalog
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Catalog catalog){
		
		logger.info("Course Id " + catalog.getCourseId());
		logger.info("Course Name " + catalog.getCourseName());
		logger.info("Course Fee " + catalog.getFee());
		adminService.addCourse(catalog);
		String result="Track saved: " + catalog;
		
		return Response.status(201).entity(result).build();	
		
	}
	
	//Delete course from catalog
	@DELETE
	@Path("/delete/{courseId}")
	public Response deleteCourse(@PathParam("courseId") int courseId) throws URIReferenceException{
	
		adminService.deleteCourse(courseId);
		
		String result="Course Id: " + courseId + " deleted.";
		return Response.status(200).entity(result).build();	
	}
	
	
//	@POST
//	@Path("/addStudent")
//	@Consumes("application/json")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addStudent(User user,Student student){
//		System.out.println("Hit post Service");
//		
//		adminService.addUser(user);
//		adminService.register(student);
//		String result="Track saved: " + student;
//		
//		return Response.status(201).entity(result).build();	
//		
//	}
//	@DELETE
//	@Path("/deleteStudent/{userId}")
//	public Response deleteUser(@PathParam("userId") int userId) throws URIReferenceException{
//	
//		adminService.deleteUser(userId);
//			
//		String result="User Id: " + userId + " deleted.";
//		return Response.status(200).entity(result).build();	
//	}
	
	
	
	
	
//	@PUT
//	@Path("/update/{courseId}")
//	@Consumes("application/json")
//	public Response updateCourse(@PathParam("courseId") int courseId,Catalog catalog) {
//	
//		
//		
//		adminService.updateCourse(courseId,catalog);
//		
//		String result="Course Id: " + courseId + " Updated.";
//		
//		return Response.status(200).entity(result).build();	
//	}
	
	
	
	
}
