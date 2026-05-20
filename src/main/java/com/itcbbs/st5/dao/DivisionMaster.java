package com.itcbbs.st5.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "div_master")
public class DivisionMaster {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int divid;
	private String divcode;
	private String divname;
	private String isactive;
	
	@Transient
	private String creationdate;
	
	@Transient
	private String lastupdated;

	public DivisionMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDivid() {
		return divid;
	}

	public void setDivid(int divid) {
		this.divid = divid;
	}

	public String getDivcode() {
		return divcode;
	}

	public void setDivcode(String divcode) {
		this.divcode = divcode;
	}

	public String getDivname() {
		return divname;
	}

	public void setDivname(String divname) {
		this.divname = divname;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Override
	public String toString() {
		return "DivisionMaster [divid=" + divid + ", divcode=" + divcode + ", divname=" + divname + ", isactive="
				+ isactive + ", creationdate=" + creationdate + ", lastupdated=" + lastupdated + "]";
	}
	
	
}
