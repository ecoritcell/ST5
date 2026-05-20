package com.itcbbs.st5.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "budget_header")
public class BudgetHeader {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bheaderid;
	private String financialyear;
	private Integer budgetno;
	private Integer divid;
	private String formonth;
	private String createdby;
	
	@Transient
	private String createdon;
	
	@Transient
	private String updatedon;
	
	@OneToMany(mappedBy = "bheader", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BudgetDetail> details = new ArrayList<>();

    // ✅ Helper method for bidirectional mapping
    public void addDetail(BudgetDetail d) {
        d.setBheader(this);
        this.details.add(d);
    }
	
	public BudgetHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getBheaderid() {
		return bheaderid;
	}

	public void setBheaderid(long bheaderid) {
		this.bheaderid = bheaderid;
	}

	public String getFinancialyear() {
		return financialyear;
	}
	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}
	public Integer getBudgetno() {
		return budgetno;
	}
	public void setBudgetno(Integer budgetno) {
		this.budgetno = budgetno;
	}
	public Integer getDivid() {
		return divid;
	}
	public void setDivid(Integer divid) {
		this.divid = divid;
	}
	public String getFormonth() {
		return formonth;
	}
	public void setFormonth(String formonth) {
		this.formonth = formonth;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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
	
	
	
	public List<BudgetDetail> getDetails() {
		return details;
	}

	public void setDetails(List<BudgetDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "BudgetHeader [bheaderid=" + bheaderid + ", financialyear=" + financialyear + ", budgetno=" + budgetno
				+ ", divid=" + divid + ", formonth=" + formonth + ", createdby=" + createdby + ", createdon="
				+ createdon + ", updatedon=" + updatedon + ", details=" + details + "]";
	}

	
	
	
	
}
