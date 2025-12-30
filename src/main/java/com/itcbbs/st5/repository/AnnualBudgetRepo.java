package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.itcbbs.st5.dao.AnnualBudget;

@Repository
public interface AnnualBudgetRepo extends JpaRepository<AnnualBudget, Integer>{

	@Override
	public List<AnnualBudget> findAll();
	
	@Procedure(procedureName = "getBudgetData")
	List<AnnualBudget> getbudgetdata(String fy, String budgetno,String formonth,int division);
	
	@Procedure(procedureName = "inserOrUpdateBudgetData")
	public String inserOrUpdateBudgetData(int div_id, String fy, String budget_no,String formonth,String head_val,String sub_head,String month_val, double budget_val);
	
	
	@Procedure(procedureName = "getMaxBudgetNumber")
	public int getMaxBudgetNumber(String fy);
	
	
	@Procedure(procedureName = "getForMonthForBudgetnumber")
	public String getForMonthForBudgetnumber(String fy,String budget_no);
		
}
