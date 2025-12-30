package com.itcbbs.st5.services;

import java.util.List;
import com.itcbbs.st5.dao.StationEarning;

public interface StationEarningService {

	public List<String> getStationsByDivByPercentage(String div,String percent);
	
	public List<StationEarning> getStationEarning(String fy, String formonth, String period,
			String entrytype,String system,String headofacct,			
			String divcode,String stntype,String stncode, String valtype);
	
	public List<String> insertOrUpdateStationEarning(List<StationEarning> earninglist); 
}
