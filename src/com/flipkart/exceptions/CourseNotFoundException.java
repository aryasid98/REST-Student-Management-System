package com.flipkart.exceptions;

@SuppressWarnings("serial")
public class CourseNotFoundException extends Exception{

	String course;
	public CourseNotFoundException(){
		
	}
	
	public String getMessage()
	{
		return	"Course Not Available!!";
	}
}
