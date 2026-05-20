package com.itcbbs.st5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.itcbbs.st5.dao.AnnualBudget;
import com.itcbbs.st5.repository.AnnualBudgetRepo;

import jakarta.transaction.Transactional;

@Service
public class AnnualBudgetServiceImp  implements AnnualBudgetService{

	@Autowired 
	private AnnualBudgetRepo abr;
	
	@Transactional
	public List<AnnualBudget> saveAnnualBudgetForDivision(List<AnnualBudget> objList){
		
		List<AnnualBudget> annualBudgetList = null;
		try {
			
			annualBudgetList = abr.saveAll(objList);
			
		}catch (DataIntegrityViolationException e) {
		    System.out.println("Duplicate record found: " + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return annualBudgetList;
	}
	
	@Transactional
	public List<AnnualBudget> getbudgetdata(String fy,String budgetno, String formonth, int division){
		
		List<AnnualBudget> dataList = null;	
		try {
			
			dataList = abr.getbudgetdata(fy,budgetno,formonth,division);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dataList;
		
	}
	
	@Transactional
	public List<String> insertOrUpdateAnnualBudget(List<AnnualBudget> budgetlis) {
		
		
		List<String> statusList = new ArrayList<>();	
		try {
			
			for (AnnualBudget obj : budgetlis) {
				
				String status = abr.inserOrUpdateBudgetData(obj.getDivisionid(),obj.getFinancialyear(),obj.getBudgetno(),
						obj.getFormonth(),obj.getHead(),obj.getSubhead(),obj.getMonth(),obj.getValue());
				System.out.println("status =" + status);
				statusList.add(status);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return statusList;
		
	}
	
	@Transactional
	public int getMaxBudgetNumber(String fy,String division) {
		
		int budgetno = -1;	
		try {

			int divint = Integer.parseInt(division);
			budgetno = abr.getMaxBudgetNumber(fy,divint);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return budgetno;
	}
	
	
	@Transactional
	public String getForMonthForBudgetnumber(String fy,String division, String budgte_no) {
		
		String formonth = "-1";
		try {

			int divint = Integer.parseInt(division);
			formonth = abr.getForMonthForBudgetnumber(fy,divint,budgte_no);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return formonth;

	}
}
