package com.itcbbs.st5.dao;

public class MonthDao {

	private String monthNumber;
	private String monthName;
	
	public MonthDao(String monthNumber, String monthName) {
		super();
		this.monthNumber = monthNumber;
		this.monthName = monthName;
	}

	public String getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(String monthNumber) {
		this.monthNumber = monthNumber;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	
}
