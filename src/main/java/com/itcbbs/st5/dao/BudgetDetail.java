package com.itcbbs.st5.dao;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "budget_detail")
public class BudgetDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long detailid;
	/* private long bheaderid; */
	private String headcode;
	private String measure;
	private String month;
	private BigDecimal budgetval = BigDecimal.ZERO;
	
	@Transient
	private String createdon;
	@Transient
	private String updatedon;
	
	@ManyToOne
	@JoinColumn(name = "bheaderid" , nullable = false)
	private BudgetHeader bheader;
	
	
	public BudgetDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getDetailid() {
		return detailid;
	}
	public void setDetailid(long detailid) {
		this.detailid = detailid;
	}
	
	/*
	 * public long getBheaderid() { return bheaderid; } public void
	 * setBheaderid(long bheaderid) { this.bheaderid = bheaderid; }
	 */
	public String getHeadcode() {
		return headcode;
	}
	public void setHeadcode(String headcode) {
		this.headcode = headcode;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getBudgetval() {
		return budgetval;
	}
	public void setBudgetval(BigDecimal budgetval) {
		this.budgetval = budgetval;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}
	public BudgetHeader getBheader() {
		return bheader;
	}
	public void setBheader(BudgetHeader bheader) {
		this.bheader = bheader;
	}
	
	@Override
	public String toString() {
		return "BudgetDetail [detailid=" + detailid + ", headcode=" + headcode + ", measure=" + measure + ", month="
				+ month + ", budgetval=" + budgetval + ", createdon=" + createdon + ", updatedon=" + updatedon
				+ ", bheader=" + bheader + "]";
	}
	
	

	
	
	
}
