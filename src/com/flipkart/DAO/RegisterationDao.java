package com.flipkart.DAO;

public interface RegisterationDao  {

	//Payemnt Method
	void payment(int studentId, int mode);

	//Check if Student Is registered
	boolean isRegistered(int studentId);


}
