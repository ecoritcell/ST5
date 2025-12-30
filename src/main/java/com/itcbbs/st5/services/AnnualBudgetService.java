package com.itcbbs.st5.services;

import java.util.List;

import com.itcbbs.st5.dao.AnnualBudget;


public interface AnnualBudgetService {

	public List<AnnualBudget> saveAnnualBudgetForDivision(List<AnnualBudget> budgetlis); 
	public List<AnnualBudget> getbudgetdata(String fy,String budgetno, String formonth, int division);
	public List<String> insertOrUpdateAnnualBudget(List<AnnualBudget> budgetlis); 
	public int getMaxBudgetNumber(String fy);
	public String getForMonthForBudgetnumber(String fy,String budgte_no);
}
