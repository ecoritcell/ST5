package com.itcbbs.st5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.repository.EarningRepo;

import jakarta.transaction.Transactional;

@Service
public class EarningServiceImp implements EarningService {

	@Autowired
	EarningRepo earRepo;
	
	@Transactional
	public StationEarningHeader getEarningHeader(String financialYear, int entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, String division, String stncode, String valtype) {
		
		StationEarningHeader header = null;
		try {
			
			header = earRepo.getEarningHeader(financialYear, entryMonth, period, entryType, systemType, headOfAccount, division, stncode, valtype); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return header;
	}
	
	@Transactional
	public void saveEarningHeader(StationEarningHeader header) {
		
		try {
			
			earRepo.save(header);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
