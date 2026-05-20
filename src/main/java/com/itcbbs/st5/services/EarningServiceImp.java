package com.itcbbs.st5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.dao.StationProjection;
import com.itcbbs.st5.repository.EarningRepo;
import com.itcbbs.st5.repository.StationMasterRepo;

import jakarta.transaction.Transactional;

@Service
public class EarningServiceImp implements EarningService {

	@Autowired
	EarningRepo earRepo;
	
	@Autowired 
	private StationMasterRepo smrepo;
	
	public int getEarningHeaderCount(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode) {
		
		int earningHeaderCount = 0;
		try {
			
			earningHeaderCount = earRepo.getEarningHeaderCount(financialYear, entryMonth, period, entryType, systemType, headOfAccount, divisionid, stncode); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return earningHeaderCount;
		
	}
	
	
	@Transactional
	public Long getEarningHeaderId(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode) {
		
		Long headerid = null;
		try {
			
			headerid = earRepo.getEarningHeaderId(financialYear, entryMonth, period, entryType, systemType, headOfAccount, divisionid, stncode); 
			System.out.println("headerid ="+headerid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return headerid;
	}
	
	@Transactional
	public StationEarningHeader getEarningHeader(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode, String valtype) {
		
		StationEarningHeader header = null;
		try {
			
			header = earRepo.getEarningHeader(financialYear, entryMonth, period, entryType, systemType, headOfAccount, divisionid, stncode, valtype); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return header;
	}
	
	@Transactional
	public StationEarningHeader getEarningHeaderWithoutValType(String financialYear, String entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, Integer divisionid, String stncode) {
		
		StationEarningHeader header = null;
		try {
			
			header = earRepo.getEarningHeaderWithoutValType(financialYear, entryMonth, period, entryType, systemType, headOfAccount, divisionid, stncode); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return header;
		
	}
	
	@Transactional
	public StationEarningHeader saveEarningHeader(StationEarningHeader header) {
		
		StationEarningHeader earningHeader = null;
		try {
			
			earningHeader = earRepo.save(header);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return earningHeader;
	}
	
	
	@Transactional
	public List<StationProjection> getStationsWithValueType(String fy, String formonth, String period, 
			String entrytype, String system, String hoa, String divid, String stntype){
		
		List<StationProjection> dataList = null;	
		try {
			
			int dividint = -1;
			if(divid != null && divid.length() >0)
				dividint = Integer.parseInt(divid);
			
			dataList = smrepo.getStationsWithValueType(fy,formonth, period, 
					 entrytype,  system,  hoa,  dividint,  stntype);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	@Transactional
	public String toggle_value_type(long headerid) {
		
		String result = null;	
		try {
					
			result = earRepo.toggle_value_type(headerid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@Transactional
	public String deleteHeaderAndDetail(long headerid) {
		
		String result = null;	
		try {
					
			result = earRepo.deleteHeaderAndDetail(headerid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public List<String> getLast3EarningFy(String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode){
		
		List<String> datalist= null;	
		try {
					
			datalist = earRepo.getLast3EarningFy(entryType, systemType, headOfAccount, divisionid, stncode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datalist;
	}
		
	@Transactional
	public List<String> getEarningMonths(String fy, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode){
		
		List<String> datalist= null;	
		try {
					
			datalist = earRepo.getEarningMonths(fy,entryType, systemType, headOfAccount, divisionid, stncode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datalist;
	}
	
	@Transactional
	public List<String>  getEarningPeriod(String fy,String formonth, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode){
	
		List<String> datalist= null;	
		try {
					
			datalist = earRepo.getEarningPeriod(fy,formonth,entryType, systemType, headOfAccount, divisionid, stncode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datalist;
	}
	
	@Transactional
	public List<String>  getEarningValueType(String fy,String formonth,String period, String entryType, String systemType,String headOfAccount, Integer divisionid, String stncode){
		
		List<String> datalist= null;	
		try {
					
			datalist = earRepo.getEarningValueType(fy,formonth,period,entryType, systemType, headOfAccount, divisionid, stncode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datalist;
	}

	
}
