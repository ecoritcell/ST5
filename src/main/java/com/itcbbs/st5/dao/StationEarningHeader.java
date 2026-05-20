package com.itcbbs.st5.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.itcbbs.st5.enums.*;

@Entity
@Table (name="stn_earning_header")
@DynamicUpdate
public class StationEarningHeader {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long recordid; 
	private String financialyear;
	private String formonth;
	private String period;
	private String entrytype;
	private String systemtype;
	private String headofaccounts;
	private Integer divisionid;
	private String stationcode;
	private String stationtype;
	private String valuetype;
	
	private String receivedfy;
	private String receivedmonth;
	private String receivedperiod;
	
	private String remarks;
	private String userid;
	private Long parentrecordid;
	
	@Transient
	private Date createdon;
	
	@Transient
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
	
	

	public Integer getDivisionid() {
		return divisionid;
	}

	public void setDivisionid(Integer divisionid) {
		this.divisionid = divisionid;
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
	

	public String getReceivedfy() {
		return receivedfy;
	}

	public void setReceivedfy(String receivedfy) {
		this.receivedfy = receivedfy;
	}

	public String getReceivedmonth() {
		return receivedmonth;
	}

	public void setReceivedmonth(String receivedmonth) {
		this.receivedmonth = receivedmonth;
	}

	public String getReceivedperiod() {
		return receivedperiod;
	}

	public void setReceivedperiod(String receivedperiod) {
		this.receivedperiod = receivedperiod;
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
	
	

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public Long getParentrecordid() {
		return parentrecordid;
	}

	public void setParentrecordid(Long parentrecordid) {
		this.parentrecordid = parentrecordid;
	}

	@Override
	public String toString() {
		return "StationEarningHeader [recordid=" + recordid + ", financialyear=" + financialyear + ", formonth="
				+ formonth + ", period=" + period + ", entrytype=" + entrytype + ", systemtype=" + systemtype
				+ ", headofaccounts=" + headofaccounts + ", divisionid=" + divisionid + ", stationcode=" + stationcode
				+ ", stationtype=" + stationtype + ", valuetype=" + valuetype + ", receivedfy=" + receivedfy
				+ ", receivedmonth=" + receivedmonth + ", receivedperiod=" + receivedperiod + ", remarks=" + remarks
				+ ", userid=" + userid + ", parentrecordid=" + parentrecordid + ", createdon=" + createdon
				+ ", lastupdated=" + lastupdated + ", details=" + details + "]";
	}

	

	
	
	
	

}
