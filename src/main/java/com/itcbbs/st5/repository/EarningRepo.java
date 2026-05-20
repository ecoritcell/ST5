package com.itcbbs.st5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.itcbbs.st5.dao.StationEarningHeader;

public interface EarningRepo extends JpaRepository<StationEarningHeader, Long>{


	@Override
	public List<StationEarningHeader> findAll();
	
	public Optional<StationEarningHeader>  findByRecordid(Long recordid);
	
	@Procedure(procedureName = "earningHeaderCount")
	public int getEarningHeaderCount(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
	
	@Procedure(procedureName = "getEarningHeader")
	public StationEarningHeader getEarningHeader(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode, String valtype);
	
	@Procedure(procedureName = "getEarningHeaderWithoutValType")
	public StationEarningHeader getEarningHeaderWithoutValType(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
	
	@Procedure(procedureName = "getEarningHeaderId")
	public Long getEarningHeaderId(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
		
	@Procedure(procedureName = "toggle_value_type")
	public String toggle_value_type(long headerid);
	
	@Procedure(procedureName = "deleteHeaderAndDetail")
	public String deleteHeaderAndDetail(long headerid);
	
	@Procedure(procedureName = "getLast3EarningFy")
	public List<String> getLast3EarningFy(String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	@Procedure(procedureName = "getEarningMonths")
	public List<String> getEarningMonths(String fy, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	@Procedure(procedureName = "getEarningPeriod")
	public List<String>  getEarningPeriod(String fy,String formonth, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	@Procedure(procedureName = "getEarningValueType")
	public List<String>  getEarningValueType(String fy,String formonth,String period, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
		
}
