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
import com.itcbbs.st5.services.AnnualBudgetServiceImp;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/annual_budget")
public class AnnualBudgetController {
	
	@Autowired
	private AnnualBudgetServiceImp abs;
	
	@PostMapping("/savebudgetdata")
	public Map<String, Object>  savebudgetdata(@RequestBody Map<String, Object> data,HttpSession session) {
		
		
		System.out.println("Inside savebudgetdata");
		System.out.println("data =" +data.toString());
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		 

		String fy = (String) data.get("financialyear");
		String budgetno = (String) data.get("budgetno");
		String formonth = (String) data.get("formonth");
		System.out.println(formonth);
		int divisionid = -1;
		String strdivsion = (String) data.get("division");
		if(strdivsion !=null && strdivsion.length() == 1)
			divisionid = Integer.parseInt(data.get("division").toString());

		
		if(fy != null && fy.length() ==7 && 
				budgetno !=null && budgetno.length() ==1 &&	 divisionid != -1) {
			
			 List<AnnualBudget> budgetlist = new ArrayList<>();
			 Map<String, Object> budgetData = (Map<String, Object>) data.get("data");
			 
			    for (Map.Entry<String, Object> entry : budgetData.entrySet()) {
			    	
			        // Each value is another map (recordid, orgval, isedited, newval)
			    	String key = entry.getKey();	
			    	
			        Map<String, Object> innerMap = (Map<String, Object>) entry.getValue();
			        String[] keys = null;
			        String head = "";
			        String subhead = "";
			        String month = "";
			        if(key !=null && key.length()>0) 
			        	keys = key.split("_");
			        
			        if(keys.length == 4) {
			        	head = keys[1];
			        	subhead = (keys[2].equals("1")?"Units":"Amount");
			        	month = getMonthValForIndex(keys[3]);
			        }else if(keys.length == 3) {
			        	
			        	head = keys[1];
			        	subhead = "";
			        	month = getMonthValForIndex(keys[2]);
			        }

			        // Safely get the 'isedited' value
			        Object isEditedObj = innerMap.get("isedited");
			        if (isEditedObj != null && Integer.parseInt(isEditedObj.toString()) == 1 && 
			        		head.length()>0 &&month.length()>0) {
			        	
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
			        						        	
			        	if(recordid > 0 && orgval != newval) {
			        		
				        	AnnualBudget abObj = new AnnualBudget();
			        		abObj.setRecordid(recordid);
			        		abObj.setDivisionid(divisionid);
			        		abObj.setFinancialyear(fy);
			        		abObj.setBudgetno(budgetno);
			        		abObj.setFormonth(formonth);
			        		abObj.setHead(head);
			        		abObj.setSubhead(subhead);
			        		abObj.setMonth(month);
			        		abObj.setValue(newval);
			        		
			        		budgetlist.add(abObj);
			        	}else {
			        		
			        		AnnualBudget abObj = new AnnualBudget();
			        		abObj.setDivisionid(divisionid);
			        		abObj.setFinancialyear(fy);
			        		abObj.setBudgetno(budgetno);
			        		abObj.setFormonth(formonth);
			        		abObj.setHead(head);
			        		abObj.setSubhead(subhead);
			        		abObj.setMonth(month);
			        		abObj.setValue(newval);
			        		
			        		budgetlist.add(abObj);
			        	}
			        	
			        }
			    }

			    System.out.println("budgetlist size = "+budgetlist.size());
			   List<String> statusist =  abs.insertOrUpdateAnnualBudget(budgetlist);					   
			    response.put("status", "success");
			 
				
		}
		

		return response;
	}
	
	@PostMapping("/getbudgetdata")
	public Map<String, Object> getbudgetdata(@RequestBody Map<String, Object> data,HttpSession session) {
					
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		System.out.println("data ="+data);
		try {
			
			String fy = (String) data.get("financialyear");
			String budgetno = (String) data.get("budgetno");
			String formonth = (String) data.get("formonth");
			String division = (String) data.get("division");

			if(fy != null && fy.length() ==7 && 
					budgetno !=null && budgetno.length() ==1 &&	
					division != null && division.length() ==1) {
				
				if((budgetno == "1"? true :( formonth != null && formonth.length() == 2))) {
					
					int divint = Integer.parseInt(division);
					List<AnnualBudget> dataList = abs.getbudgetdata(fy,budgetno,formonth,divint);
					response.put("status", "success");
					response.put("data", dataList);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return response;
	
	}
	
	@PostMapping("/getMaxBudgetnumber")
	public Map<String, Object> getMaxBudgetnumber(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("getMaxBudgetnumber called");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		try {
			
			String fy = (String) data.get("financialyear");
			System.out.println("fy = "+fy);
			if(fy != null && fy.length() == 7) {				
				int maxbudgetno = abs.getMaxBudgetNumber(fy);
				response.put("status", "success");
				response.put("maxbudgetno", maxbudgetno);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		
		return response;
	}
	
	
	@PostMapping("/getForMonthForBudgetnumber")
	public Map<String, Object> getForMonthForBudgetnumber(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("getForMonthForBudgetnumber called");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		try {
			
			String fy = (String) data.get("financialyear");
			String budgetno = (String) data.get("budgetnumber");
			System.out.println("fy = "+fy+"budgetno = "+budgetno);
			if(fy != null && fy.length() == 7 && budgetno !=null && budgetno.length() >0) {				
				String formonth = abs.getForMonthForBudgetnumber(fy,budgetno);
				response.put("status", "success");
				response.put("formonth", formonth);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		
		return response;
	}
	
	public  String getMonthValForIndex(String val) {
		
		String month = "";
		switch (val){
		
			case "1":
				month = "04";
				break;
			case "2":
				month = "05";
				break;
			case "3":
				month = "06";
				break;
			case "4":
				month = "07";
				break;
			case "5":
				month = "08";
				break;
			case "6":
				month = "09";
				break;
			case "7":
				month = "10";
				break;
			case "8":
				month = "11";
				break;
			case "9":
				month = "12";
				break;
			case "10":
				month = "01";
				break;
			case "11":
				month = "02";
				break;
			case "12":
				month = "03";
				break;
			case "13":
				month = "";
				break;
			default:
				month = "";				
		}
		
		return month;
}



}
