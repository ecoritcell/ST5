package com.itcbbs.st5.services;


import com.itcbbs.st5.dao.BudgetHeader;

public interface BudgetService {

	public BudgetHeader getBudgetHeader(String financialYear, int budgetno,String month, int divid);
	
	public void saveBudgetHeader(BudgetHeader bheader);
	
	public BudgetHeader  findByBheaderid(Long bheaderid);
}
