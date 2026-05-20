package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.itcbbs.st5.util.AppUtil;

public class OtherCoachingReportDao {

	/* =====================
    IDENTIFIERS
    ===================== */
	private String divCode;
	private String sytemType;
	private String sytemTypeDesc;

 /* =====================
    ACTUALS
    ===================== */
 private BigDecimal currFyAmt;
 private BigDecimal prevFyAmt;

 /* =====================
    BUDGET
    ===================== */
 private BigDecimal budgetAmt;

 /* =====================
    VARIATIONS
    ===================== */
  
 private BigDecimal varOverBudgetActAmt;
 private BigDecimal varOverBudgetPctAmt;
 private BigDecimal varOverPrevFyActAmt;
 private BigDecimal varOverPrevFyPctAmt;
 
 public OtherCoachingReportDao(String divCode,String sytemType, BigDecimal currFyAmt, BigDecimal prevFyAmt, BigDecimal budgetAmt) {
		super();
		
		this.divCode =  divCode;
		this.sytemType = sytemType;	
		this.sytemTypeDesc = AppUtil.getSystemTypeDescription(sytemType);
		this.currFyAmt = AppUtil.nz(currFyAmt);
		this.prevFyAmt = AppUtil.nz(prevFyAmt);
		this.budgetAmt = AppUtil.nz(budgetAmt);
		calculateVariations();
	 }
	 
	 
	 private void calculateVariations() {	     
	     
	     
	     // Actual Variation of passenger count over Budget
	     this.varOverBudgetActAmt = this.currFyAmt.subtract(this.budgetAmt);

	     // % Variation of passenger count over Budget
	     this.varOverBudgetPctAmt =  AppUtil.percent(this.varOverBudgetActAmt, this.budgetAmt);
	     
	     // Actual Variation of passenger count over previous year 
	     this.varOverPrevFyActAmt = this.currFyAmt.subtract(this.prevFyAmt);

	     // % Variation of passenger count over previous year
	     this.varOverPrevFyPctAmt =  AppUtil.percent(this.varOverPrevFyActAmt,this.prevFyAmt);
	     
	 }
	 

	 public String getDivCode() {
		 return divCode;
	 }


	 public void setDivCode(String divCode) {
		 this.divCode = divCode;
	 }
	 

	 public String getSytemType() {
		return sytemType;
	}


	 public void setSytemType(String sytemType) {
		 this.sytemType = sytemType;
	 }


	 public String getSytemTypeDesc() {
		 return sytemTypeDesc;
	 }


	 public void setSytemTypeDesc(String sytemTypeDesc) {
		 this.sytemTypeDesc = sytemTypeDesc;
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


	 public BigDecimal getBudgetAmt() {
		 return budgetAmt;
	 }


	 public void setBudgetAmt(BigDecimal budgetAmt) {
		 this.budgetAmt = budgetAmt;
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
