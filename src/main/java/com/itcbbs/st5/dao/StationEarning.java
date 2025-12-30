package com.itcbbs.st5.dao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stn_earning")
public class StationEarning {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordid; 
	private String financialyear; 
	private String formonth; 
	private String period; 
	private String headofaccount; 
	private String systm; 
	private String entrytype; 
	private String stntype; 
	private String divcode; 
	private String stncode; 
	private String valuetype; 
	private String receivedon; 
	private String remarks; 
	private String earningcat; 
	private String earningsubcat; 
	private double quantity; 
	private double amount; 
	private Date creationdate; 
	private Date lastupdated;
	public StationEarning() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getFinancialyear() {
		return financialyear;
	}
	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}
	public String getFormonth() {
		return formonth;
	}
	public void setFormonth(String formonth) {
		this.formonth = formonth;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getHeadofaccount() {
		return headofaccount;
	}
	public void setHeadofaccount(String headofaccount) {
		this.headofaccount = headofaccount;
	}
	
	public String getSystm() {
		return systm;
	}
	public void setSystm(String systm) {
		this.systm = systm;
	}
	public String getEntrytype() {
		return entrytype;
	}
	public void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}
	public String getStntype() {
		return stntype;
	}
	public void setStntype(String stntype) {
		this.stntype = stntype;
	}
	public String getDivcode() {
		return divcode;
	}
	public void setDivcode(String divcode) {
		this.divcode = divcode;
	}
	public String getStncode() {
		return stncode;
	}
	public void setStncode(String stncode) {
		this.stncode = stncode;
	}
	public String getValuetype() {
		return valuetype;
	}
	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}
	
	public String getReceivedon() {
		return receivedon;
	}
	public void setReceivedon(String receivedon) {
		this.receivedon = receivedon;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEarningcat() {
		return earningcat;
	}
	public void setEarningcat(String earningcat) {
		this.earningcat = earningcat;
	}
	public String getEarningsubcat() {
		return earningsubcat;
	}
	public void setEarningsubcat(String earningsubcat) {
		this.earningsubcat = earningsubcat;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	@Override
	public String toString() {
		return "StationEarning [recordid=" + recordid + ", financialyear=" + financialyear + ", formonth=" + formonth
				+ ", period=" + period + ", headofaccount=" + headofaccount + ", system=" + systm + ", entrytype="
				+ entrytype + ", stntype=" + stntype + ", divcode=" + divcode + ", stncode=" + stncode + ", valuetype="
				+ valuetype + ", receivedon=" + receivedon + ", remarks=" + remarks + ", earningcat=" + earningcat
				+ ", earningsubcat=" + earningsubcat + ", quantity=" + quantity + ", amount=" + amount
				+ ", creationdate=" + creationdate + ", lastupdated=" + lastupdated + "]";
	}
		
	

}
