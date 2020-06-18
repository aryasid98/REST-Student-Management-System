package com.flipkart.constants;

public class SQLConstantsQuery {
	
	public static final String  ADD_USER="insert into user (username, password,roleId) values(?,?,?)";	
	public static final String DELETE_USER="delete from user where userId=?";
	
	public static final String ADD_ADMIN="insert into admin values(?,?,?,?,?)";
	
	
	public static final String ADD_PROFESSOR_COURSES="insert into professorcourses values(?,?)";
	public static final String DELETE_PROFESSOR_COURSES="delete from professorcourses where profId=? and courseId=?";
	public static final String VIEW_STUDENTS="select sc.courseId as courseId,sc.studentId as StudentId from studentcourses sc,professorcourses pc where sc.courseId=pc.courseId and pc.profId=?";
	public static final String VIEW_PROFESSOR_ENROLLED_COURSES="select profId, professorcourses.courseId, courseName from professorcourses,courses where profId=? and professorcourses.courseId=courses.courseId";
	public static final String VIEW_PROFESSOR_LIST="select name,profId,gender from professor";
	
	
	public static final String ADD_STUDENT="insert into student values(?,?,?,?,?)";
	public static final String VIEW_STUDENT_LIST="select * from student";
	
	public static final String ADD_PROFESSOR="insert into professor values(?,?,?,?,?)";
	public static final String UPLOAD_GRADES="update studentcourses set grades=? where studentId=? and courseId=?";
	
	public static final String REGISTER_STUDENT="insert into registeration(studentId,registeredTimeStamp,paymentId) values(?,?,?)";
	public static final String IS_REGISTERED="select exists(select studentId from registeration where studentId=?) as exist";

	public static final String CALCULATE_FEE="select sum(fee) as fee from courses,studentcourses where studentId=?	and studentcourses.courseId=courses.courseId";
	
	public static final String VIEW_ENROLLED_COURSES="select studentId, studentcourses.courseId, courseName from studentcourses,courses where studentId=? and studentcourses.courseId=courses.courseId";
	public static final String ADD_STUDENT_COURSE="insert into studentcourses values(?,?,?)";
	public static final String DELETE_STUDENT_COURSE="delete from studentcourses where courseId = ? And studentId=?";
	
	public static final String CHECK_CREDENTIALS="select roleId from user where username=? and password=?";

	
	public static final String VIEW_CATALOG="select courseId, courseName from courses";

	public static final String VIEW_PAYMENT_MODES = "select paymentId,mode from payment";
	public static final String ADD_COURSE = "insert into courses values(?,?,?,?)";
	public static final String DELETE_COURSE = "delete from courses where courseId=?";
	public static final String VIEW_REPORT = "Select courseId,grades from studentcourses where studentId=?";

}
