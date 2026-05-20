package com.itcbbs.st5.dao;

import java.math.BigDecimal;

import com.itcbbs.st5.util.AppUtil;

public class NoOfTonnesLoadedDao {

	private String headofaccounts;
	private BigDecimal budget = BigDecimal.ZERO;
	private BigDecimal pfytotal = BigDecimal.ZERO;
	private BigDecimal cfytotal  = BigDecimal.ZERO;
	
	
	/*==============================
	  Variation over Budget 
	  ==============================*/
	private BigDecimal actVarOverBudget;
	private BigDecimal pctVarOverBudget;
	
	/*==============================
	  Variation over previous financial year
	  ==============================*/
	private BigDecimal actVarOverPfy;
	private BigDecimal pctVarOverPfy;
	
	
	public NoOfTonnesLoadedDao(String headofaccounts,BigDecimal budget, BigDecimal pfytotal, BigDecimal cfytotal) {
		super();
		this.headofaccounts = headofaccounts;
		this.budget = AppUtil.nz(budget);
		this.pfytotal = AppUtil.nz(pfytotal);
		this.cfytotal = AppUtil.nz(cfytotal);
		
		calculateVariations();
	}
	
	private void calculateVariations() {	     
	     		
		/*==============================
		  Calculate Variation over Budget 
		  ==============================*/
	     this.actVarOverBudget = this.cfytotal.subtract(this.budget);
	     this.pctVarOverBudget =  AppUtil.percent(this.actVarOverBudget, this.budget);
	     
	     
	     /*=====================================
		  Calculate Variation over Previous Year 
		  ======================================*/
	     this.actVarOverPfy = this.cfytotal.subtract(this.pfytotal);
	     this.pctVarOverPfy =  AppUtil.percent(this.actVarOverPfy, pfytotal);
	     
	     
	 }

	public String getHeadofaccounts() {
		return headofaccounts;
	}

	public void setHeadofaccounts(String headofaccounts) {
		this.headofaccounts = headofaccounts;
	}

	public BigDecimal getPfytotal() {
		return pfytotal;
	}

	public void setPfytotal(BigDecimal pfytotal) {
		this.pfytotal = pfytotal;
	}

	public BigDecimal getCfytotal() {
		return cfytotal;
	}

	public void setCfytotal(BigDecimal cfytotal) {
		this.cfytotal = cfytotal;
	}

	public BigDecimal getActVarOverPfy() {
		return actVarOverPfy;
	}

	public void setActVarOverPfy(BigDecimal actVarOverPfy) {
		this.actVarOverPfy = actVarOverPfy;
	}

	public BigDecimal getPctVarOverPfy() {
		return pctVarOverPfy;
	}

	public void setPctVarOverPfy(BigDecimal pctVarOverPfy) {
		this.pctVarOverPfy = pctVarOverPfy;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public BigDecimal getActVarOverBudget() {
		return actVarOverBudget;
	}

	public void setActVarOverBudget(BigDecimal actVarOverBudget) {
		this.actVarOverBudget = actVarOverBudget;
	}

	public BigDecimal getPctVarOverBudget() {
		return pctVarOverBudget;
	}

	public void setPctVarOverBudget(BigDecimal pctVarOverBudget) {
		this.pctVarOverBudget = pctVarOverBudget;
	}
	
}
