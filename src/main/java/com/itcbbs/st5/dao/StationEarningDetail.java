package com.itcbbs.st5.dao;

import java.beans.Transient;
import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stn_earning_detail")
public class StationEarningDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordid;
	/* private Long headerid; */
	private String classcode;
	private Integer bookedcount = 0;
	private BigDecimal bookedamt = BigDecimal.ZERO;
	private Integer refundcount = 0;
	private BigDecimal refundamt = BigDecimal.ZERO;
	private Integer excesscount = 0;
	private BigDecimal excessamt = BigDecimal.ZERO;
	
	private BigDecimal pmwagon  = BigDecimal.ZERO; 
	private BigDecimal pmtonne = BigDecimal.ZERO;
	private BigDecimal tpwagon = BigDecimal.ZERO;
	private BigDecimal wagon = BigDecimal.ZERO;
	private BigDecimal tonne = BigDecimal.ZERO;
	private BigDecimal rate = BigDecimal.ZERO;
	private BigDecimal amount = BigDecimal.ZERO;
	private BigDecimal weight = BigDecimal.ZERO;
	private BigDecimal earning = BigDecimal.ZERO;
		
	private int userid;
	
	@jakarta.persistence.Transient
	private Date createdon;
	
	@jakarta.persistence.Transient
	private Date lastupdated;
	
	@ManyToOne
	@JoinColumn(name = "headerid" , nullable = false)
	private StationEarningHeader header;
	
	public StationEarningDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Long getRecordid() {
		return recordid;
	}




	public void setRecordid(Long recordid) {
		this.recordid = recordid;
	}




	public String getClasscode() {
		return classcode;
	}




	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}




	public Integer getBookedcount() {
		return bookedcount;
	}




	public void setBookedcount(Integer bookedcount) {
		this.bookedcount = bookedcount;
	}




	public BigDecimal getBookedamt() {
		return bookedamt;
	}




	public void setBookedamt(BigDecimal bookedamt) {
		this.bookedamt = bookedamt;
	}




	public Integer getRefundcount() {
		return refundcount;
	}




	public void setRefundcount(Integer refundcount) {
		this.refundcount = refundcount;
	}




	public BigDecimal getRefundamt() {
		return refundamt;
	}




	public void setRefundamt(BigDecimal refundamt) {
		this.refundamt = refundamt;
	}




	public Integer getExcesscount() {
		return excesscount;
	}




	public void setExcesscount(Integer excesscount) {
		this.excesscount = excesscount;
	}




	public BigDecimal getExcessamt() {
		return excessamt;
	}




	public void setExcessamt(BigDecimal excessamt) {
		this.excessamt = excessamt;
	}

	
	
	


	public BigDecimal getPmwagon() {
		return pmwagon;
	}




	public void setPmwagon(BigDecimal pmwagon) {
		this.pmwagon = pmwagon;
	}




	public BigDecimal getPmtonne() {
		return pmtonne;
	}




	public void setPmtonne(BigDecimal pmtonne) {
		this.pmtonne = pmtonne;
	}




	public BigDecimal getTpwagon() {
		return tpwagon;
	}




	public void setTpwagon(BigDecimal tpwagon) {
		this.tpwagon = tpwagon;
	}




	public BigDecimal getWagon() {
		return wagon;
	}




	public void setWagon(BigDecimal wagon) {
		this.wagon = wagon;
	}




	public BigDecimal getTonne() {
		return tonne;
	}




	public void setTonne(BigDecimal tonne) {
		this.tonne = tonne;
	}




	public BigDecimal getRate() {
		return rate;
	}




	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}




	public BigDecimal getAmount() {
		return amount;
	}




	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}




	public BigDecimal getWeight() {
		return weight;
	}




	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}




	public BigDecimal getEarning() {
		return earning;
	}




	public void setEarning(BigDecimal earning) {
		this.earning = earning;
	}




	public int getUserid() {
		return userid;
	}




	public void setUserid(int userid) {
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




	public StationEarningHeader getHeader() {
		return header;
	}




	public void setHeader(StationEarningHeader header) {
		this.header = header;
	}


	 // ===============================
    // DERIVED FIELDS (NOT STORED)
    // ===============================

    @Transient
    public Integer getTotalno() {
        return safe(bookedcount) - safe(refundcount) + safe(excesscount);
    }

    @Transient
    public BigDecimal getTotalrs() {
        return safe(bookedamt)
                .subtract(safe(refundamt))
                .add(safe(excessamt));
    }

	/*
	 * @Transient public BigDecimal getDfcrs() { // example logic return
	 * getTotalrs().multiply(new BigDecimal("0.02")); }
	 */

    // ===============================
    // SAFETY HELPERS
    // ===============================
    private int safe(Integer v) {
        return v == null ? 0 : v;
    }

    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }




	@Override
	public String toString() {
		return "StationEarningDetail [recordid=" + recordid + ", classcode=" + classcode + ", bookedcount="
				+ bookedcount + ", bookedamt=" + bookedamt + ", refundcount=" + refundcount + ", refundamt=" + refundamt
				+ ", excesscount=" + excesscount + ", excessamt=" + excessamt + ", pmwagon=" + pmwagon + ", pmtonne="
				+ pmtonne + ", tpwagon=" + tpwagon + ", wagon=" + wagon + ", tonne=" + tonne + ", rate=" + rate
				+ ", amount=" + amount + ", weight=" + weight + ", earning=" + earning + ", userid=" + userid
				+ ", createdon=" + createdon + ", lastupdated=" + lastupdated + ", header=" + header + "]";
	}	
	
	

}
