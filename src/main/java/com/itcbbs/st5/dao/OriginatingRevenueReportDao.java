package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OriginatingRevenueReportDao {

	private String revenuehead;
	private String systemtype;
	private BigDecimal budget;
	private BigDecimal pfytotal;
	private BigDecimal cfytotal;
	
	/*==============================
	  Variation over Budget 
	  ==============================*/
	private BigDecimal actVarOverBudget;
	private BigDecimal pctVarOverBudget;
	
	
	/*==============================
	  Variation over Previous Year 
	  ==============================*/
	private BigDecimal actVarOverPrevYear;
	private BigDecimal pctVarOverPrevYear;
	
	public OriginatingRevenueReportDao(String revenuehead,String systemtype, BigDecimal budget, BigDecimal pfytotal,
			BigDecimal cfytotal) {
		super();
		this.revenuehead =  revenuehead;
		this.systemtype =  systemtype;
		this.budget =  nz(budget);
		this.pfytotal = nz(pfytotal);
		this.cfytotal = nz(cfytotal);
		calculateVariations();
	}
	
	
	 private void calculateVariations() {

		 /*==============================
		  Calculate Variation over Budget 
		  ==============================*/
	     this.actVarOverBudget = this.cfytotal.subtract(this.budget);
	     this.pctVarOverBudget =  percent(this.actVarOverBudget, this.budget);
	     
	     
	     /*=====================================
		  Calculate Variation over Previous Year 
		  ======================================*/
	     this.actVarOverPrevYear = this.cfytotal.subtract(this.pfytotal);
	     this.pctVarOverPrevYear =  percent(this.actVarOverPrevYear, this.pfytotal);
	     
	 }
	 
	
	 
	 private BigDecimal nz(BigDecimal v) {
	     return v == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP) : v.setScale(2, RoundingMode.HALF_UP);
	 }

	 private BigDecimal percent(BigDecimal numerator, BigDecimal denominator) {
		 
	     if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
	         return BigDecimal.ZERO;
	     }
	     
	     return numerator
	             .multiply(BigDecimal.valueOf(100))
	             .divide(denominator, 2, RoundingMode.HALF_UP);
	 }


	 public String getRevenuehead() {
		 return revenuehead;
	 }


	 public void setRevenuehead(String revenuehead) {
		 this.revenuehead = revenuehead;
	 }
	 
	 
	 public String getSystemtype() {
		return systemtype;
	}


	 public void setSystemtype(String systemtype) {
		 this.systemtype = systemtype;
	 }


	 public BigDecimal getBudget() {
		 return budget;
	 }


	 public void setBudget(BigDecimal budget) {
		 this.budget = budget;
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


	 public BigDecimal getActVarOverPrevYear() {
		 return actVarOverPrevYear;
	 }


	 public void setActVarOverPrevYear(BigDecimal actVarOverPrevYear) {
		 this.actVarOverPrevYear = actVarOverPrevYear;
	 }


	 public BigDecimal getPctVarOverPrevYear() {
		 return pctVarOverPrevYear;
	 }


	 public void setPctVarOverPrevYear(BigDecimal pctVarOverPrevYear) {
		 this.pctVarOverPrevYear = pctVarOverPrevYear;
	 }

	

}
