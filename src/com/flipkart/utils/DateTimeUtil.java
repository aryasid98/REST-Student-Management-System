package com.flipkart.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeUtil {

	static public LocalTime getTime()
	{
		LocalTime localTime=LocalTime.now();
		return localTime;
	}
	static public LocalDate getDate()
	{
		LocalDate localDate=LocalDate.now();
		return localDate;
	}
	static public DayOfWeek getDayofWeek()
	{
		DayOfWeek localDate=LocalDate.now().getDayOfWeek();
		return localDate;
	}
	
	static public String TimeStamp()
	{
		return DateTimeUtil.getDayofWeek()+ " " +  DateTimeUtil.getDate() + "  at " + DateTimeUtil.getTime();
	}
}
