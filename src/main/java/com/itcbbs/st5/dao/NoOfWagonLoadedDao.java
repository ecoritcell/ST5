package com.itcbbs.st5.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.itcbbs.st5.util.AppUtil;

public class NoOfWagonLoadedDao {

	private String headofaccounts;
	private BigDecimal pfytotal = BigDecimal.ZERO;
	private BigDecimal cfytotal = BigDecimal.ZERO;
	
	private BigDecimal pfy_load_perday = BigDecimal.ZERO;
	private BigDecimal cfy_load_perday = BigDecimal.ZERO;
	
	/*==============================
	  Variation over previous financial year
	  ==============================*/
	private BigDecimal actVarOverPfy;
	private BigDecimal pctVarOverPfy;
	
	
	public NoOfWagonLoadedDao(String headofaccounts, BigDecimal pfytotal, BigDecimal cfytotal,int numberOfDays,String period) {
		super();
		
		this.headofaccounts = headofaccounts;
		this.pfytotal = AppUtil.nz(pfytotal);
		this.cfytotal = AppUtil.nz(cfytotal);
		
		
		
		if(period.equalsIgnoreCase("I") || period.equalsIgnoreCase("II")) {
			
			this.pfy_load_perday = this.pfytotal != BigDecimal.ZERO? this.pfytotal.divide(BigDecimal.TEN, RoundingMode.DOWN): BigDecimal.ZERO;
			this.cfy_load_perday = this.cfytotal != BigDecimal.ZERO? this.cfytotal.divide(BigDecimal.TEN, RoundingMode.DOWN): BigDecimal.ZERO;
			
		}else if(period.equalsIgnoreCase("III")) {
			
			this.pfy_load_perday = this.pfytotal != BigDecimal.ZERO? this.pfytotal.divide(BigDecimal.valueOf(numberOfDays-20), RoundingMode.DOWN): BigDecimal.ZERO;
			this.cfy_load_perday = this.cfytotal != BigDecimal.ZERO? this.cfytotal.divide(BigDecimal.valueOf(numberOfDays-20), RoundingMode.DOWN): BigDecimal.ZERO;
		}
		
		calculateVariations();
	}
	
	
	private void calculateVariations() {	     
	     
	     
	     // Actual Variation of no. of wagons loaded over previous financial year
	     this.actVarOverPfy = this.cfytotal.subtract(this.pfytotal);

	     // % Variation of no. of wagons loaded over previous financial year
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


	public BigDecimal getPfy_load_perday() {
		return pfy_load_perday;
	}


	public void setPfy_load_perday(BigDecimal pfy_load_perday) {
		this.pfy_load_perday = pfy_load_perday;
	}


	public BigDecimal getCfy_load_perday() {
		return cfy_load_perday;
	}


	public void setCfy_load_perday(BigDecimal cfy_load_perday) {
		this.cfy_load_perday = cfy_load_perday;
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
	
	
	
	
	
}
