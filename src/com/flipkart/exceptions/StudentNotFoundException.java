package com.flipkart.exceptions;

public class StudentNotFoundException  extends Exception{
		
	private static final long serialVersionUID = 1L;

		public StudentNotFoundException(String username){
		}
		
		public String getMessage()
		{
			return	"Student Not Found!!";
		}
	
}
