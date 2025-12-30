package com.itcbbs.st5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcbbs.st5.dao.AnnualBudget;
import com.itcbbs.st5.dao.StationEarning;
import com.itcbbs.st5.repository.StationEarningRepo;
import com.itcbbs.st5.repository.StationMasterRepo;

import jakarta.transaction.Transactional;

@Service
public class StationEarningServiceImp {

	@Autowired 
	private StationEarningRepo serepo;
	
	@Autowired 
	private StationMasterRepo smrepo;
	
	@Transactional
	public List<String> getStationsByDivByPercentage(String div,String percent){
		
		List<String> dataList = null;	
		try {
			
			dataList = smrepo.getStationsByDivByPercentage(div,percent);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataList;
	}
	
	@Transactional
	public List<StationEarning> getStationEarning(String fy, String formonth, String period,
			String entrytype, String system, String headofacct,   
			String divcode,String stntype,String stncode, String valtype){
		
		List<StationEarning> dataList = null;	
		try {
			
			dataList = serepo.getStationEarning(fy,formonth,period,
					entrytype,system,headofacct,
					divcode,stntype,stncode,valtype);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dataList;

	}
	
	@Transactional
	public List<String> insertOrUpdateStationEarning(List<StationEarning> earninglist) {
		
		
		List<String> statusList = new ArrayList<>();	
		try {
			
			for (StationEarning obj : earninglist) {
				
				System.out.println("obj.getAmount() =" +obj.getAmount());
				String status = serepo.inserOrUpdateStnEarning(obj.getFinancialyear(), obj.getFormonth(), obj.getPeriod(),
						obj.getEntrytype(), obj.getSystm(),obj.getHeadofaccount(),obj.getDivcode(), obj.getStntype(),
						obj.getStncode(), obj.getValuetype(), obj.getReceivedon(), obj.getRemarks(),obj.getEarningcat(), 
						obj.getEarningsubcat(), obj.getQuantity(), obj.getAmount());
				System.out.println("status =" + status);
				statusList.add(status);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return statusList;
		
	}
}
