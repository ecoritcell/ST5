package com.itcbbs.st5.dao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "signatory_detail")
public class SignatoryDetailDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordid;
	private String railwayname;
	private String address;
	private String signatoryname;
	private String signatorydesg;
	
	@Transient
	private Date createdon;
	
	@Transient
	private Date updatedon;

	public SignatoryDetailDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRailwayname() {
		return railwayname;
	}

	public void setRailwayname(String railwayname) {
		this.railwayname = railwayname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSignatoryname() {
		return signatoryname;
	}

	public void setSignatoryname(String signatoryname) {
		this.signatoryname = signatoryname;
	}

	public String getSignatorydesg() {
		return signatorydesg;
	}

	public void setSignatorydesg(String signatorydesg) {
		this.signatorydesg = signatorydesg;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	@Override
	public String toString() {
		return "SignatoryDetailDao [recordid=" + recordid + ", railwayname=" + railwayname + ", address=" + address
				+ ", signatoryname=" + signatoryname + ", signatorydesg=" + signatorydesg + ", createdon=" + createdon
				+ ", updatedon=" + updatedon + "]";
	}
	
	
}
