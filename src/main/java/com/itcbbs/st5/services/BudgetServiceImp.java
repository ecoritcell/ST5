package com.itcbbs.st5.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itcbbs.st5.dao.BudgetHeader;
import com.itcbbs.st5.repository.BudgetRepo;

import jakarta.transaction.Transactional;

@Service
public class BudgetServiceImp implements BudgetService {

	@Autowired
	BudgetRepo budgetRepo;
	
	@Transactional
	public BudgetHeader getBudgetHeader(String financialYear, int budgetno, String month,int divid) {
		
		BudgetHeader header = null;
		try {
			
			header = budgetRepo.getBudgetHeader(financialYear, budgetno, month, divid); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return header;
	}
	
	@Transactional
	public void saveBudgetHeader(BudgetHeader header) {
		
		try {
			
			budgetRepo.save(header);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	public BudgetHeader  findByBheaderid(Long bheaderid){		
		
		BudgetHeader bheader = null;
		try {
			
			bheader = budgetRepo.findByBheaderid(bheaderid).orElse(null); 
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bheader;
	}
	
	
}
