package com.itcbbs.st5.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.itcbbs.st5.enums.*;

@Entity
@Table (name="stn_earning_header")
public class StationEarningHeader {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long recordid; 
	private String financialyear;
	private int formonth;
	private String period;
	private String entrytype;
	private String systemtype;
	private String headofaccounts;
	private String division;
	private String stationcode;
	private String valuetype;
	private String receivedon;
	private String remarks;
	private String userid;
	private Date createdon;
	private Date lastupdated;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StationEarningDetail> details = new ArrayList<>();

    // ✅ Helper method for bidirectional mapping
    public void addDetail(StationEarningDetail d) {
        d.setHeader(this);
        this.details.add(d);
    }
	
	public StationEarningHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRecordid() {
		return recordid;
	}

	public void setRecordid(Long recordid) {
		this.recordid = recordid;
	}

	public String getFinancialyear() {
		return financialyear;
	}

	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}

	public int getFormonth() {
		return formonth;
	}

	public void setFormonth(int formonth) {
		this.formonth = formonth;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getEntrytype() {
		return entrytype;
	}

	public void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}

	public String getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}

	public String getHeadofaccounts() {
		return headofaccounts;
	}

	public void setHeadofaccounts(String headofaccounts) {
		this.headofaccounts = headofaccounts;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getStationcode() {
		return stationcode;
	}

	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public List<StationEarningDetail> getDetails() {
		return details;
	}

	public void setDetails(List<StationEarningDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "StationEarningHeader [recordid=" + recordid + ", financialyear=" + financialyear + ", formonth="
				+ formonth + ", period=" + period + ", entrytype=" + entrytype + ", systemtype=" + systemtype
				+ ", headofaccounts=" + headofaccounts + ", divisionid=" + division + ", stationcode=" + stationcode
				+ ", valuetype=" + valuetype + ", receivedon=" + receivedon + ", remarks=" + remarks + ", userid="
				+ userid + ", createdon=" + createdon + ", lastupdated=" + lastupdated + ", details=" + details + "]";
	}

	
	

}
