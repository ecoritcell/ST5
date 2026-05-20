package com.itcbbs.st5.dao;

import java.math.BigDecimal;

public interface PassengerReportProjection {

	String getDivcode();
	String getSystemtype();	
	BigDecimal getPrevfycount();
	BigDecimal getCurrfycount();
	BigDecimal getPrevfyamt();
	BigDecimal getCurrfyamt();
	BigDecimal getBudunit();
	BigDecimal getBudamt();
}
