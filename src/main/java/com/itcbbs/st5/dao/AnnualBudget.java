package com.itcbbs.st5.dao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "annual_budget")
public class AnnualBudget {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordid;	
	private int divisionid;
	private String financialyear;
	private String budgetno;
	private String formonth;
	private String head;
	private String subhead;
	private String month;
	private double value;
	private Date creationdate ;
	private Date lastupdated ;
	
	public AnnualBudget() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public int getDivisionid() {
		return divisionid;
	}
	public void setDivisionid(int divisionid) {
		this.divisionid = divisionid;
	}
	public String getFinancialyear() {
		return financialyear;
	}
	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}
	public String getBudgetno() {
		return budgetno;
	}
	public void setBudgetno(String budgetno) {
		this.budgetno = budgetno;
	}
	public String getFormonth() {
		return formonth;
	}
	public void setFormonth(String formonth) {
		this.formonth = formonth;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSubhead() {
		return subhead;
	}
	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
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
		return "AnnualBudget [recordid=" + recordid + ", divisionid=" + divisionid + ", financialyear=" + financialyear
				+ ", budgetno=" + budgetno + ", formonth=" + formonth + ", head=" + head + ", subhead=" + subhead
				+ ", month=" + month + ", value=" + value + ", creationdate=" + creationdate + ", lastupdated="
				+ lastupdated + "]";
	}
	
	
	


}
