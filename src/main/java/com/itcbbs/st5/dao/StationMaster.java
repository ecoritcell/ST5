package com.itcbbs.st5.dao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stn_master")
public class StationMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stnid; 
	private String stncode; 
	private String stnname; 
	private String percentage; 
	private String divcode; 
	private int isactive; 
	private Date creationdate; 
	private Date lastupdated;
	public StationMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStnid() {
		return stnid;
	}
	public void setStnid(int stnid) {
		this.stnid = stnid;
	}
	public String getStncode() {
		return stncode;
	}
	public void setStncode(String stncode) {
		this.stncode = stncode;
	}
	public String getStnname() {
		return stnname;
	}
	public void setStnname(String stnname) {
		this.stnname = stnname;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getDivcode() {
		return divcode;
	}
	public void setDivcode(String divcode) {
		this.divcode = divcode;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
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
		return "StationMaster [stnid=" + stnid + ", stncode=" + stncode + ", stnname=" + stnname + ", percentage="
				+ percentage + ", divcode=" + divcode + ", isactive=" + isactive + ", creationdate=" + creationdate
				+ ", lastupdated=" + lastupdated + "]";
	}
	
	

}
