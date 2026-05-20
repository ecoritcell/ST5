package com.itcbbs.st5.services;

import java.util.List;

import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.dao.StationProjection;

public interface EarningService {

	public List<StationProjection> getStationsWithValueType(String fy, String formonth, String period, 
			String entrytype, String system, String hoa, String divid, String stntype);
	
	public int getEarningHeaderCount(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
	
	public StationEarningHeader getEarningHeader(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode, String valtype);
	
	public StationEarningHeader getEarningHeaderWithoutValType(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
	
	
	
	public Long getEarningHeaderId(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode);
	
	
	
	public StationEarningHeader saveEarningHeader(StationEarningHeader header);
	
	public String toggle_value_type(long headerid);
	
	public String deleteHeaderAndDetail(long headerid);
	
	
	public List<String> getLast3EarningFy(String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	public List<String> getEarningMonths(String fy, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	public List<String>  getEarningPeriod(String fy,String formonth, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
	
	public List<String>  getEarningValueType(String fy,String formonth,String period, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode);
		
}
