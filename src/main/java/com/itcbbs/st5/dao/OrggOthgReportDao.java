package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrggOthgReportDao {
	
	
	/* =====================
    IDENTIFIERS
    ===================== */
	private String divCode;
	
	 /* =====================
    BUDGET
    ===================== */
	private BigDecimal budgetAmt;


 /* =====================
    ACTUALS
    ===================== */
	private BigDecimal currFyAmt;
	private BigDecimal prevFyAmt;


 /* =====================
    VARIATIONS
    ===================== */
  
	 private BigDecimal varOverBudgetActAmt;
	 private BigDecimal varOverBudgetPctAmt;
	 private BigDecimal varOverPrevFyActAmt;
	 private BigDecimal varOverPrevFyPctAmt;
	 
	 
	 
	 
	 public OrggOthgReportDao(String divCode, BigDecimal budgetAmt, BigDecimal prevFyAmt,BigDecimal currFyAmt) {
		super();
		
		this.divCode = divCode;
		this.budgetAmt = nz(budgetAmt);
		this.prevFyAmt = nz(prevFyAmt);
		this.currFyAmt = nz(currFyAmt);

		calculateVariations();
	}



	 private void calculateVariations() {	     
	     
	     
	     // Actual Variation of passenger count over Budget
	     this.varOverBudgetActAmt = this.currFyAmt.subtract(this.budgetAmt);

	     // % Variation of passenger count over Budget
	     this.varOverBudgetPctAmt =  percent(this.varOverBudgetActAmt, this.budgetAmt);
	     
	     // Actual Variation of passenger count over previous year 
	     this.varOverPrevFyActAmt = this.currFyAmt.subtract(this.prevFyAmt);

	     // % Variation of passenger count over previous year
	     this.varOverPrevFyPctAmt =  percent(this.varOverPrevFyActAmt,this.prevFyAmt);
	     
	 }
	 
	 
	 
	 private BigDecimal nz(BigDecimal v) {
	     return v == null ? BigDecimal.ZERO : v;
	 }

	 private BigDecimal percent(BigDecimal numerator, BigDecimal denominator) {
		 
	     if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
	         return BigDecimal.ZERO;
	     }
	     
	     return numerator
	             .multiply(BigDecimal.valueOf(100))
	             .divide(denominator, 2, RoundingMode.HALF_UP);
	 }



	 public String getDivCode() {
		 return divCode;
	 }



	 public void setDivCode(String divCode) {
		 this.divCode = divCode;
	 }



	 public BigDecimal getBudgetAmt() {
		 return budgetAmt;
	 }



	 public void setBudgetAmt(BigDecimal budgetAmt) {
		 this.budgetAmt = budgetAmt;
	 }



	 public BigDecimal getCurrFyAmt() {
		 return currFyAmt;
	 }



	 public void setCurrFyAmt(BigDecimal currFyAmt) {
		 this.currFyAmt = currFyAmt;
	 }



	 public BigDecimal getPrevFyAmt() {
		 return prevFyAmt;
	 }



	 public void setPrevFyAmt(BigDecimal prevFyAmt) {
		 this.prevFyAmt = prevFyAmt;
	 }



	 public BigDecimal getVarOverBudgetActAmt() {
		 return varOverBudgetActAmt;
	 }



	 public void setVarOverBudgetActAmt(BigDecimal varOverBudgetActAmt) {
		 this.varOverBudgetActAmt = varOverBudgetActAmt;
	 }



	 public BigDecimal getVarOverBudgetPctAmt() {
		 return varOverBudgetPctAmt;
	 }



	 public void setVarOverBudgetPctAmt(BigDecimal varOverBudgetPctAmt) {
		 this.varOverBudgetPctAmt = varOverBudgetPctAmt;
	 }



	 public BigDecimal getVarOverPrevFyActAmt() {
		 return varOverPrevFyActAmt;
	 }



	 public void setVarOverPrevFyActAmt(BigDecimal varOverPrevFyActAmt) {
		 this.varOverPrevFyActAmt = varOverPrevFyActAmt;
	 }



	 public BigDecimal getVarOverPrevFyPctAmt() {
		 return varOverPrevFyPctAmt;
	 }



	 public void setVarOverPrevFyPctAmt(BigDecimal varOverPrevFyPctAmt) {
		 this.varOverPrevFyPctAmt = varOverPrevFyPctAmt;
	 }
	 

}
