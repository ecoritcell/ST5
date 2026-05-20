package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BudgetForm {
	
	public Long bheaderid;
	public String financialYear;
	public String budgetNo;    
	public String divisionId;
	public String forMonth;
	public Map<String, BigDecimal> values = new HashMap<>();
	public BudgetForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getBheaderid() {
		return bheaderid;
	}
	public void setBheaderid(Long bheaderid) {
		this.bheaderid = bheaderid;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getBudgetNo() {
		return budgetNo;
	}
	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getForMonth() {
		return forMonth;
	}
	public void setForMonth(String forMonth) {
		this.forMonth = forMonth;
	}
	public Map<String, BigDecimal> getValues() {
		return values;
	}
	public void setValues(Map<String, BigDecimal> values) {
		this.values = values;
	}


    
}
