package com.itcbbs.st5.dao;

import java.math.BigDecimal;

public class OtherCoachingCombinedDao {

	 	private String divCode;
		private String sytemType;
		private String sytemTypeDesc;
	 	private BigDecimal forPeriodBudgetAmt;
	    private BigDecimal forPeriodPrevFyAmt;
	    private BigDecimal forPeriodCurrFyAmt;
	    private BigDecimal forPeriodVarOverBudgetActAmt;
	    private BigDecimal forPeriodVarOverBudgetPctAmt;
	    private BigDecimal forPeriodVarOverPrevFyActAmt;
	    private BigDecimal forPeriodVarOverPrevFyPctAmt;

	    // Upto the Period
	    private BigDecimal uptoperiodBudgetAmt;
	    private BigDecimal uptoPeriodPrevFyAmt;
	    private BigDecimal uptoPeriodCurrFyAmt;
	    private BigDecimal uptoPeriodVarOverBudgetActAmt;
	    private BigDecimal uptoPeriodVarOverBudgetPctAmt;
	    private BigDecimal uptoPeriodVarOverPrevFyActAmt;
	    private BigDecimal uptoPeriodVarOverPrevFyPctAmt;
		public OtherCoachingCombinedDao(String divCode, String sytemType, String sytemTypeDesc,
				BigDecimal forPeriodBudgetAmt, BigDecimal forPeriodPrevFyAmt, BigDecimal forPeriodCurrFyAmt,
				BigDecimal forPeriodVarOverBudgetActAmt, BigDecimal forPeriodVarOverBudgetPctAmt,
				BigDecimal forPeriodVarOverPrevFyActAmt, BigDecimal forPeriodVarOverPrevFyPctAmt,
				BigDecimal uptoperiodBudgetAmt, BigDecimal uptoPeriodPrevFyAmt, BigDecimal uptoPeriodCurrFyAmt,
				BigDecimal uptoPeriodVarOverBudgetActAmt, BigDecimal uptoPeriodVarOverBudgetPctAmt,
				BigDecimal uptoPeriodVarOverPrevFyActAmt, BigDecimal uptoPeriodVarOverPrevFyPctAmt) {
			super();
			this.divCode = divCode;
			this.sytemType = sytemType;
			this.sytemTypeDesc = sytemTypeDesc;
			this.forPeriodBudgetAmt = forPeriodBudgetAmt;
			this.forPeriodPrevFyAmt = forPeriodPrevFyAmt;
			this.forPeriodCurrFyAmt = forPeriodCurrFyAmt;
			this.forPeriodVarOverBudgetActAmt = forPeriodVarOverBudgetActAmt;
			this.forPeriodVarOverBudgetPctAmt = forPeriodVarOverBudgetPctAmt;
			this.forPeriodVarOverPrevFyActAmt = forPeriodVarOverPrevFyActAmt;
			this.forPeriodVarOverPrevFyPctAmt = forPeriodVarOverPrevFyPctAmt;
			this.uptoperiodBudgetAmt = uptoperiodBudgetAmt;
			this.uptoPeriodPrevFyAmt = uptoPeriodPrevFyAmt;
			this.uptoPeriodCurrFyAmt = uptoPeriodCurrFyAmt;
			this.uptoPeriodVarOverBudgetActAmt = uptoPeriodVarOverBudgetActAmt;
			this.uptoPeriodVarOverBudgetPctAmt = uptoPeriodVarOverBudgetPctAmt;
			this.uptoPeriodVarOverPrevFyActAmt = uptoPeriodVarOverPrevFyActAmt;
			this.uptoPeriodVarOverPrevFyPctAmt = uptoPeriodVarOverPrevFyPctAmt;
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
		public BigDecimal getForPeriodBudgetAmt() {
			return forPeriodBudgetAmt;
		}
		public void setForPeriodBudgetAmt(BigDecimal forPeriodBudgetAmt) {
			this.forPeriodBudgetAmt = forPeriodBudgetAmt;
		}
		public BigDecimal getForPeriodPrevFyAmt() {
			return forPeriodPrevFyAmt;
		}
		public void setForPeriodPrevFyAmt(BigDecimal forPeriodPrevFyAmt) {
			this.forPeriodPrevFyAmt = forPeriodPrevFyAmt;
		}
		public BigDecimal getForPeriodCurrFyAmt() {
			return forPeriodCurrFyAmt;
		}
		public void setForPeriodCurrFyAmt(BigDecimal forPeriodCurrFyAmt) {
			this.forPeriodCurrFyAmt = forPeriodCurrFyAmt;
		}
		public BigDecimal getForPeriodVarOverBudgetActAmt() {
			return forPeriodVarOverBudgetActAmt;
		}
		public void setForPeriodVarOverBudgetActAmt(BigDecimal forPeriodVarOverBudgetActAmt) {
			this.forPeriodVarOverBudgetActAmt = forPeriodVarOverBudgetActAmt;
		}
		public BigDecimal getForPeriodVarOverBudgetPctAmt() {
			return forPeriodVarOverBudgetPctAmt;
		}
		public void setForPeriodVarOverBudgetPctAmt(BigDecimal forPeriodVarOverBudgetPctAmt) {
			this.forPeriodVarOverBudgetPctAmt = forPeriodVarOverBudgetPctAmt;
		}
		public BigDecimal getForPeriodVarOverPrevFyActAmt() {
			return forPeriodVarOverPrevFyActAmt;
		}
		public void setForPeriodVarOverPrevFyActAmt(BigDecimal forPeriodVarOverPrevFyActAmt) {
			this.forPeriodVarOverPrevFyActAmt = forPeriodVarOverPrevFyActAmt;
		}
		public BigDecimal getForPeriodVarOverPrevFyPctAmt() {
			return forPeriodVarOverPrevFyPctAmt;
		}
		public void setForPeriodVarOverPrevFyPctAmt(BigDecimal forPeriodVarOverPrevFyPctAmt) {
			this.forPeriodVarOverPrevFyPctAmt = forPeriodVarOverPrevFyPctAmt;
		}
		public BigDecimal getUptoperiodBudgetAmt() {
			return uptoperiodBudgetAmt;
		}
		public void setUptoperiodBudgetAmt(BigDecimal uptoperiodBudgetAmt) {
			this.uptoperiodBudgetAmt = uptoperiodBudgetAmt;
		}
		public BigDecimal getUptoPeriodPrevFyAmt() {
			return uptoPeriodPrevFyAmt;
		}
		public void setUptoPeriodPrevFyAmt(BigDecimal uptoPeriodPrevFyAmt) {
			this.uptoPeriodPrevFyAmt = uptoPeriodPrevFyAmt;
		}
		public BigDecimal getUptoPeriodCurrFyAmt() {
			return uptoPeriodCurrFyAmt;
		}
		public void setUptoPeriodCurrFyAmt(BigDecimal uptoPeriodCurrFyAmt) {
			this.uptoPeriodCurrFyAmt = uptoPeriodCurrFyAmt;
		}
		public BigDecimal getUptoPeriodVarOverBudgetActAmt() {
			return uptoPeriodVarOverBudgetActAmt;
		}
		public void setUptoPeriodVarOverBudgetActAmt(BigDecimal uptoPeriodVarOverBudgetActAmt) {
			this.uptoPeriodVarOverBudgetActAmt = uptoPeriodVarOverBudgetActAmt;
		}
		public BigDecimal getUptoPeriodVarOverBudgetPctAmt() {
			return uptoPeriodVarOverBudgetPctAmt;
		}
		public void setUptoPeriodVarOverBudgetPctAmt(BigDecimal uptoPeriodVarOverBudgetPctAmt) {
			this.uptoPeriodVarOverBudgetPctAmt = uptoPeriodVarOverBudgetPctAmt;
		}
		public BigDecimal getUptoPeriodVarOverPrevFyActAmt() {
			return uptoPeriodVarOverPrevFyActAmt;
		}
		public void setUptoPeriodVarOverPrevFyActAmt(BigDecimal uptoPeriodVarOverPrevFyActAmt) {
			this.uptoPeriodVarOverPrevFyActAmt = uptoPeriodVarOverPrevFyActAmt;
		}
		public BigDecimal getUptoPeriodVarOverPrevFyPctAmt() {
			return uptoPeriodVarOverPrevFyPctAmt;
		}
		public void setUptoPeriodVarOverPrevFyPctAmt(BigDecimal uptoPeriodVarOverPrevFyPctAmt) {
			this.uptoPeriodVarOverPrevFyPctAmt = uptoPeriodVarOverPrevFyPctAmt;
		}
	    
	    
}
