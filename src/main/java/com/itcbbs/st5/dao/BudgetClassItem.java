package com.itcbbs.st5.dao;

public class BudgetClassItem {

	public String headCode; 
	public String headLabel; 
	public String measure; 
	public String measureLabel; 
	public boolean saveable; 	
	public boolean group;
	public int rowspan;

	public BudgetClassItem(String headCode, String headLabel, String measure, String measureLabel, boolean saveable,boolean group, int rowspan) {
		this.headCode = headCode;
		this.headLabel = headLabel;
		this.measure = measure;
		this.measureLabel = measureLabel;
		this.saveable = saveable;
		this.group = group;
		this.rowspan = rowspan;
	}
}
