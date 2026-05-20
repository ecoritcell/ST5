package com.itcbbs.st5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import com.itcbbs.st5.dao.BudgetHeader;

public interface BudgetRepo extends JpaRepository<BudgetHeader, Long>{
	
	@Override
	public List<BudgetHeader> findAll();
	
	public Optional<BudgetHeader>  findByBheaderid(Long bheaderid);
	
	@Procedure(procedureName = "getBudgetHeader")
	public BudgetHeader getBudgetHeader(String financialYear, int budgetno, String month, int divid);

}
