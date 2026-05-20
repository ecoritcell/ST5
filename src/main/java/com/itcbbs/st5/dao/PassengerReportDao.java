package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.itcbbs.st5.util.AppUtil;

public class PassengerReportDao {

	
	/* =====================
    IDENTIFIERS
    ===================== */
	private String divCode;
	private String sytemType;
	private String sytemTypeDesc;

 /* =====================
    ACTUALS
    ===================== */
 private BigDecimal currFyCount;   
 private BigDecimal currFyAmt;

 private BigDecimal prevFyCount;   
 private BigDecimal prevFyAmt;

 /* =====================
    BUDGET
    ===================== */
 private BigDecimal budgetUnit;
 private BigDecimal budgetAmt;

 /* =====================
    VARIATIONS
    ===================== */
 
 private BigDecimal varOverBudgetActCount;
 private BigDecimal varOverBudgetPctCount;
 private BigDecimal varOverPrevFyActCount;
 private BigDecimal varOverPrevFyPctCount;
 
 private BigDecimal varOverBudgetActAmt;
 private BigDecimal varOverBudgetPctAmt;
 private BigDecimal varOverPrevFyActAmt;
 private BigDecimal varOverPrevFyPctAmt;
 
 
 public PassengerReportDao(String divCode,String sytemType, BigDecimal currFyCount, BigDecimal currFyAmt, BigDecimal prevFyCount,
		BigDecimal prevFyAmt, BigDecimal budgetUnit, BigDecimal budgetAmt) {
	super();
	this.divCode =  divCode;
	this.sytemType = sytemType;	
	this.sytemTypeDesc = AppUtil.getSystemTypeDescription(sytemType);
	this.currFyCount = AppUtil.nz(currFyCount) ;
	this.currFyAmt = AppUtil.nz(currFyAmt);
	this.prevFyCount = AppUtil.nz(prevFyCount);
	this.prevFyAmt = AppUtil.nz(prevFyAmt);
	this.budgetUnit = AppUtil.nz(budgetUnit);
	this.budgetAmt = AppUtil.nz(budgetAmt);
	calculateVariations();
 }
 
 
 private void calculateVariations() {

     // Actual Variation of passenger count over Budget
     this.varOverBudgetActCount = this.currFyCount.subtract(this.budgetUnit);

     // % Variation of passenger count over Budget
     this.varOverBudgetPctCount =  AppUtil.percent(this.varOverBudgetActCount, this.budgetUnit);
     
     // Actual Variation of passenger count over previous year 
     this.varOverPrevFyActCount = this.currFyCount.subtract(this.prevFyCount);

     // % Variation of passenger count over previous year
     this.varOverPrevFyPctCount =  AppUtil.percent(this.varOverPrevFyActCount,this.prevFyCount);
     
     
     
     
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


 public BigDecimal getCurrFyCount() {
	return currFyCount;
 }


 public void setCurrFyCount(BigDecimal currFyCount) {
	this.currFyCount = currFyCount;
 }


 public BigDecimal getCurrFyAmt() {
	return currFyAmt;
 }


 public void setCurrFyAmt(BigDecimal currFyAmt) {
	this.currFyAmt = currFyAmt;
 }


 public BigDecimal getPrevFyCount() {
	return prevFyCount;
 }


 public void setPrevFyCount(BigDecimal prevFyCount) {
	this.prevFyCount = prevFyCount;
 }


 public BigDecimal getPrevFyAmt() {
	return prevFyAmt;
 }


 public void setPrevFyAmt(BigDecimal prevFyAmt) {
	this.prevFyAmt = prevFyAmt;
 }


 public BigDecimal getBudgetUnit() {
	return budgetUnit;
 }


 public void setBudgetUnit(BigDecimal budgetUnit) {
	this.budgetUnit = budgetUnit;
 }


 public BigDecimal getBudgetAmt() {
	return budgetAmt;
 }


 public void setBudgetAmt(BigDecimal budgetAmt) {
	this.budgetAmt = budgetAmt;
 }


 public BigDecimal getVarOverBudgetActCount() {
	return varOverBudgetActCount;
 }


 public void setVarOverBudgetActCount(BigDecimal varOverBudgetActCount) {
	this.varOverBudgetActCount = varOverBudgetActCount;
 }


 public BigDecimal getVarOverBudgetPctCount() {
	return varOverBudgetPctCount;
 }


 public void setVarOverBudgetPctCount(BigDecimal varOverBudgetPctCount) {
	this.varOverBudgetPctCount = varOverBudgetPctCount;
 }


 public BigDecimal getVarOverPrevFyActCount() {
	return varOverPrevFyActCount;
 }


 public void setVarOverPrevFyActCount(BigDecimal varOverPrevFyActCount) {
	this.varOverPrevFyActCount = varOverPrevFyActCount;
 }


 public BigDecimal getVarOverPrevFyPctCount() {
	return varOverPrevFyPctCount;
 }


 public void setVarOverPrevFyPctCount(BigDecimal varOverPrevFyPctCount) {
	this.varOverPrevFyPctCount = varOverPrevFyPctCount;
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
