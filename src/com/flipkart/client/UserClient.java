package com.flipkart.client;


import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.model.User;
import com.flipkart.service.UserService;
import com.flipkart.utils.DateTimeUtil;

public class UserClient{

	private static Logger logger= Logger.getLogger(UserClient.class);

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		UserService userservice=new UserService();
		
		logger.info("\n1. Student Registeration \n2. User Login");
		int option=sc.nextInt();
		sc.nextLine();
		StudentClient studentClient=new StudentClient();
		ProfessorClient professorClient=new ProfessorClient();
		AdminClient adminClient=new AdminClient();
		while(option>=1&&option<=2) {
		if(option==1)
		{
			studentClient.register();
		}
		else
		{
			String username;
			String password;
			int roleId=0;
			int count=0;	
			while(count<3 && roleId==0) {
				User user=new User();
				logger.info("Enter Username");
				
				username=sc.nextLine();
				logger.info("Enter Password");
				password=sc.nextLine();
				user.setUsername(username);
				user.setPassword(password);
				
				roleId=userservice.checkCredentials(user);
				int userId=userservice.getUserid(user);
				count++;
				if(roleId==3) {
					logger.info(username + "\nLogged in at: " + DateTimeUtil.TimeStamp());
					studentClient.display(userId);
					count=0;
					logger.info(username + "\nLogged out at: " + DateTimeUtil.TimeStamp());
					}
				else if(roleId==2) {
					logger.info(username + "\nLogged in at: " + DateTimeUtil.TimeStamp());
					professorClient.display(userId);
					count=0;
					logger.info(username + "\nLogged out at: " + DateTimeUtil.TimeStamp());
				}
				else if(roleId==1) {
					logger.info(username + "\nLogged in at: "  +DateTimeUtil.TimeStamp());
					adminClient.display(userId);
					count=0;
					logger.info(username + "\nLogged out at: " + DateTimeUtil.TimeStamp());
				}
			}
			
			if(count==3)
			logger.info("Wrong Username/Password.TRY AGAIN LATER!!");
		}

		logger.info("Enter Option 1 or 2 else exit");
		logger.info("\n1. Student Registeration \n2. User Login");
		option=sc.nextInt();
		sc.nextLine();
		}
		
		
		
	}
	
	
}
