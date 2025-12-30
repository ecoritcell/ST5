package com.itcbbs.st5.services;

import com.itcbbs.st5.dao.StationEarningHeader;

public interface EarningService {

	public StationEarningHeader getEarningHeader(String financialYear, int entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, String division, String stncode, String valtype);
	
	public void saveEarningHeader(StationEarningHeader header);
}
