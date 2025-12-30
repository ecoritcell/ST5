package com.itcbbs.st5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcbbs.st5.dao.AnnualBudget;
import com.itcbbs.st5.dao.StationEarning;
import com.itcbbs.st5.services.StationEarningServiceImp;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/station_earning")
public class StationEarningController {

	@Autowired
	private StationEarningServiceImp sesimp;
	
	@PostMapping("/getstationdata")
	public Map<String, Object> getstationdata(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getstationdata ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		try {
			
			String division = (String) data.get("division");
			String percentage = (String) data.get("percentage");

			System.out.println("division = "+division+"percentage = "+percentage);
			
			if(division != null && division.length() ==3 && 
				percentage !=null && percentage.length() ==2) {
				
				List<String> dataList = sesimp.getStationsByDivByPercentage(division,percentage);
				response.put("status", "success");
				response.put("data", dataList);				
			}else {
				System.out.println("Some error in data");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	@PostMapping("/getstationearningdata")
	public Map<String, Object> getstationearningdata(@RequestBody StationEarning seobj,HttpSession session) {
					
		System.out.println("inside getstationearningdata");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		try {
			
			System.out.println(seobj.getFinancialyear());
			

			if(seobj.getFinancialyear() !=null && seobj.getFinancialyear().length() == 7 &&
					seobj.getFormonth() !=null && seobj.getFormonth().length() == 2 &&
					seobj.getPeriod() !=null && seobj.getPeriod().length() != 0 &&
					seobj.getEntrytype() !=null && seobj.getEntrytype().length() == 2 &&
					seobj.getSystm() !=null && seobj.getSystm().length() == 1 &&
					seobj.getHeadofaccount() !=null && seobj.getHeadofaccount().length() != 0) {
				
				
				System.out.println("inside if condition of getstationearningdata");
				
				String stncode = seobj.getStncode() != null?seobj.getStncode():"";
				String stntype = seobj.getStntype() != null?seobj.getStntype():""; 
				String division = seobj.getDivcode() !=null?seobj.getDivcode():"";
				
				List<StationEarning> dataList = sesimp.getStationEarning(seobj.getFinancialyear(),
						seobj.getFormonth(),
						seobj.getPeriod(),
						seobj.getEntrytype(),
						seobj.getSystm(),
						seobj.getHeadofaccount(),
						division,
						stntype,
						stncode,
						seobj.getValuetype());
				
				System.out.println("getstationearningdata size =" +dataList.size());
				response.put("status", "success");
				response.put("data", dataList);
				
				/*
				 * if((seobj.getEntrytype() == "SW" || seobj.getEntrytype() == "DW") &&
				 * division.length() != 0) {
				 * 
				 * 
				 * }
				 */
								
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return response;
	
	}
	
	@PostMapping("/savestationearningdata")
	public Map<String, Object>  saveStationEarningdata(@RequestBody Map<String, Object> data,HttpSession session) {
		
		
		System.out.println("Inside saveStationEarningdata");
		System.out.println("data =" +data.toString());
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		 

		String fy = data.get("financialyear") != null? (String) data.get("financialyear"):"";
		String formonth = data.get("formonth") !=null? (String)data.get("formonth"):"";
		String period = data.get("period") !=null? (String)data.get("period"):"";
		String entrytype = data.get("entrytype") !=null? (String)data.get("entrytype"):"";
		String system = data.get("system") !=null? (String)data.get("system"):"";
		String headofaccount = data.get("headofaccount") !=null? (String)data.get("headofaccount"):"";
		String divcode = data.get("divcode") !=null? (String)data.get("divcode"):"";
		String stntype = data.get("stntype") !=null? (String)data.get("stntype"):"";
		String stncode = data.get("stncode") !=null? (String)data.get("stncode"):"";
		String valuetype = data.get("valuetype") !=null? (String)data.get("valuetype"):"";
		String receivedon = data.get("receivedon") !=null? (String)data.get("receivedon"):"";
		String remarks = data.get("remarks") !=null? (String)data.get("remarks"):"";
		
		if(fy.length() !=7 || formonth.length() !=2 || period.length() == 0 ||
				entrytype.length() != 2 || system.length() != 1 || headofaccount.length() == 0)
			return response;		
		
		
		List<StationEarning> earninglist = new ArrayList<>();
		 Map<String, Object> earningData =  data.get("data") !=null?(Map<String, Object>) data.get("data"):null;
		 
		    for (Map.Entry<String, Object> entry : earningData.entrySet()) {
		    	
		        // Each value is another map (recordid, orgval, isedited, newval)
		    	String key = entry.getKey();			    	
		        Map<String, Object> innerMap = (Map<String, Object>) entry.getValue();
		        String[] keys = null;
		        String earncat = "";
		        String earnsubcat = "";
		        String unitoramount = "";
		        if(key !=null && key.length()>0) 
		        	keys = key.split("_");
		        
		        if(keys.length == 3) {
		        	earncat = keys[1];
		        	earnsubcat = getSubcategoryName(Integer.parseInt(keys[2]));
		        	unitoramount = getUnitOrAmount(Integer.parseInt(keys[2]));
		        }

		        // Safely get the 'isedited' value
		        Object isEditedObj = innerMap.get("isedited");
		        if (isEditedObj != null && Integer.parseInt(isEditedObj.toString()) == 1) {
		        	
		        	int recordid = -1;
		        	String strRecordid =  innerMap.get("recordid").toString();
		        	if(strRecordid !=null && strRecordid.length() >0)
		        		recordid = Integer.parseInt(strRecordid);
		        	
		        	Double orgval = -1.0;
		        	String strOrgval =  innerMap.get("orgval").toString();
		        	if(strOrgval !=null && strOrgval.length() >0)
		        		orgval = Double.parseDouble(strOrgval);
		        	
		        	Double newval = -1.0;
		        	String strNewval =  innerMap.get("newval").toString();
		        	if(strNewval !=null && strNewval.length() >0)
		        		newval = Double.parseDouble(strNewval);
		        				
		        	StationEarning stnObj= null;
		        	//When record already exist in the database
		        	if(recordid > 0) {
		        		if(orgval != newval) {
		        			
		        			if(earncat.length()>0 && earnsubcat.length() >0 ) {
		        				final String tempearncat =  earncat;
		        				final String tempearnsubcat =  earnsubcat;
		        				stnObj = earninglist.stream()
			        			        .filter(e -> tempearncat.equals(e.getEarningcat()) &&
			        			        		tempearnsubcat.equals(e.getEarningsubcat()))
			        			        .findFirst()
			        			        .orElse(null);
		        			}
		        			
		        			
		        			if(stnObj == null) {
		        				stnObj = new StationEarning();
					        	stnObj.setRecordid(recordid);
			        			stnObj.setAmount(-1.0);
			        			stnObj.setQuantity(-1.0);
		        			}
				        	
		        		}
		        		
		        	}else {
		        		
		        		if(earncat.length()>0 && earnsubcat.length() >0 ) {
	        				final String tempearncat =  earncat;
	        				final String tempearnsubcat =  earnsubcat;
	        				
	        				stnObj = earninglist.stream()
		        			        .filter(e -> tempearncat.equals(e.getEarningcat()) &&
		        			        		tempearnsubcat.equals(e.getEarningsubcat()))
		        			        .findFirst()
		        			        .orElse(null);
	        			}
		        		
		        		if(stnObj == null) {
		        			stnObj = new StationEarning();
		        			stnObj.setAmount(-1.0);
		        			stnObj.setQuantity(-1.0);
		        		}
		        	}
		        	
		        	if(stnObj != null) {
		        		
		        		stnObj.setFinancialyear(fy);
			        	stnObj.setFormonth(formonth);
			        	stnObj.setPeriod(period);
			        	stnObj.setEntrytype(entrytype);
			        	stnObj.setSystm(system);
			        	stnObj.setHeadofaccount(headofaccount);
			        	stnObj.setDivcode(divcode);
			        	stnObj.setStntype(stntype);
			        	stnObj.setStncode(stncode);
			        	stnObj.setValuetype(valuetype);
			        	stnObj.setReceivedon(receivedon);
			        	stnObj.setRemarks(remarks);
			        	stnObj.setEarningcat(earncat);
			        	stnObj.setEarningsubcat(earnsubcat);
			        	if(unitoramount == "unit")
			        		stnObj.setQuantity(newval);
			        	else if(unitoramount == "amount")
			        		stnObj.setAmount(newval);
		        		
			        	if(!earninglist.contains(stnObj))
			        		earninglist.add(stnObj);
		        	}
		        }
		    }

		    System.out.println("earninglist size = "+earninglist.size());
		    List<String> statusist =  sesimp.insertOrUpdateStationEarning(earninglist);					   
		    response.put("status", "success");		

		return response;
	}
	
	
	public String getSubcategoryName(int index) {
		String subcat ="";
		if(index == 1 || index == 2)
			subcat= "BKD";
		else if(index == 3 || index == 4)
			subcat = "RFD";
		else if(index == 5 || index == 6)
			subcat = "EF";
		else
			subcat= "";
		return subcat;
	}
	
	public String getUnitOrAmount(int index) {
		String unitoramt ="";
		if(index == 1 || index == 3 || index == 5)
			unitoramt = "unit";
		else if(index == 2 || index == 4 || index == 6)
			unitoramt = "amount";		
		else
			unitoramt= "";
		return unitoramt;
	}
}
