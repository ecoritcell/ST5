package com.itcbbs.st5.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itcbbs.st5.dao.AnnualBudget;
import com.itcbbs.st5.dao.BudgetClassItem;
import com.itcbbs.st5.dao.BudgetDetail;
import com.itcbbs.st5.dao.BudgetForm;
import com.itcbbs.st5.dao.BudgetHeader;
import com.itcbbs.st5.dao.ClassItem;
import com.itcbbs.st5.dao.StationEarningDetail;
import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.services.AnnualBudgetServiceImp;
import com.itcbbs.st5.services.BudgetServiceImp;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/budget")
public class AnnualBudgetController {
	
	@Autowired
	private AnnualBudgetServiceImp abs;
	
	@Autowired
	private BudgetServiceImp bs;
	
	public enum BudgetMonth {
	    APR("04", "Apr"),
	    MAY("05", "May"),
	    JUN("06", "Jun"),
	    JUL("07", "Jul"),
	    AUG("08", "Aug"),
	    SEP("09", "Sep"),
	    OCT("10", "Oct"),
	    NOV("11", "Nov"),
	    DEC("12", "Dec"),
	    JAN("01", "Jan"),
	    FEB("02", "Feb"),
	    MAR("03", "Mar"),
		TOT("TOT", "TOTAL"); 		

	    public final String code;
	    public final String label;

	    BudgetMonth(String code, String label) {
	        this.code = code;
	        this.label = label;
	    }
	}
	
	
	@GetMapping("/budget_detail")
	public String budgetDetail(Model model) {

		System.out.println("inside budgetDetail");
		
			
		List<BudgetClassItem> classes = getClasses();		
		
		/*
		 * BudgetHeader header = new BudgetHeader(); List<BudgetDetail> details = new
		 * ArrayList<>(); for (BudgetClassItem c : classes) { BudgetDetail d = new
		 * BudgetDetail(); d.setHeadcode(c.headCode); d.setMeasure(c.measure);
		 * details.add(d); }
		 * 
		 * header.setDetails(details);
		 */		
		BudgetForm form = new BudgetForm();
		prepareForm(null,form);
		model.addAttribute("budgetForm", form);
		model.addAttribute("bheaderid", form.getBheaderid());
		model.addAttribute("classes", classes);
		model.addAttribute("months", BudgetMonth.values());		
		
		/* return "originating_period"; */
		return "annual_budget";
	}
	
	
public void prepareForm(BudgetHeader bheader, BudgetForm form) {
			System.out.println("prepareForm called");
			
		if(bheader == null) {
			System.out.println("Header does not exist.");

			form.bheaderid = null;
			List<BudgetClassItem> classes = getClasses();
			for (BudgetClassItem classitem : classes) {
				
				for (BudgetMonth month : BudgetMonth.values()) {
					
					 String key = classitem.headCode + "_" + classitem.measure + "_" + month.code;			  
						 form.values.put(key, BigDecimal.ZERO); 
						/* form.values.put(key, BigDecimal.ONE); */
						/* System.out.println("key =" +key +"value = "+BigDecimal.ONE); */
				}
			}
			
		}else {
			
			form.bheaderid = bheader.getBheaderid();
			for (BudgetDetail d : bheader.getDetails()) { 
				  
				  String key = d.getHeadcode() + "_" + d.getMeasure() + "_" + d.getMonth();
					/* System.out.println("key =" +key + "=" + d.getBudgetval()); */
				  form.values.put(key, d.getBudgetval()); 
				  
				  //Calculate row wise total
				  String rowWiseTotkey = d.getHeadcode() + "_" + d.getMeasure() + "_" + "TOT";
				  if(form.values.containsKey(rowWiseTotkey)) {
					  BigDecimal rowwisetot =  form.values.get(rowWiseTotkey).add(d.getBudgetval());
					  form.values.put(rowWiseTotkey, rowwisetot);
				  }else {
					  form.values.put(rowWiseTotkey, d.getBudgetval());
				  }
				  
				//Calculate column wise total
				  String columnWiseTotkey = "TEARN" + "_" + d.getMeasure() + "_" + d.getMonth();
				  if(form.values.containsKey(columnWiseTotkey)) {
					  BigDecimal colwisetot =  form.values.get(columnWiseTotkey).add(d.getBudgetval());
					  form.values.put(columnWiseTotkey, colwisetot);
				  }else {
					  form.values.put(columnWiseTotkey, d.getBudgetval());
				  }
			  }
			
			
			//Calculate grand total for UNIT
			Set<String> filteredKeys = form.values.keySet().stream()
				    .filter(key -> key.startsWith("TEARN_UNIT_"))
				    .collect(Collectors.toSet());
			
			BigDecimal temptot = BigDecimal.ZERO;
			for (String totkey : filteredKeys) {				
				temptot = form.values.get(totkey).add(temptot);
			}
			
			form.values.put("TEARN_UNIT_TOT", temptot);
			
			
			//Calculate grand total for AMOUNT
			filteredKeys = form.values.keySet().stream()
				    .filter(key -> key.startsWith("TEARN_AMT_"))
				    .collect(Collectors.toSet());
			
			temptot = BigDecimal.ZERO;
			for (String totkey : filteredKeys) {
				temptot = form.values.get(totkey).add(temptot);
			}
			
			form.values.put("TEARN_AMT_TOT", temptot);
			
		}

}
	 		
	
	@PostMapping("/getbudgetdata")
	public String getBudgetData(@RequestParam String mode, @ModelAttribute BudgetForm form,Model model) {
					
		System.out.println("Inside getbudgetdata");
		System.out.println("mode = " +mode);
		try {
			
			System.out.println(form.bheaderid+","+form.financialYear+","+form.budgetNo+","+form.divisionId +","+form.forMonth);
			if(mode.equalsIgnoreCase("blank")) {
				
				form = new BudgetForm();
				prepareForm(null,form);
				
			}else {
				
				if(form.financialYear == null || form.budgetNo == null || form.divisionId == null || form.forMonth == null )
					return "redirect: getbudgetdata";
				
				int budgetnoint =  Integer.parseInt(form.budgetNo);			
				int dividint = Integer.parseInt(form.divisionId);
					
				BudgetHeader header = bs.getBudgetHeader(form.financialYear,budgetnoint,form.forMonth,dividint);
				prepareForm(header,form);			
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
	    model.addAttribute("budgetForm", form);
	    model.addAttribute("bheaderid", form.getBheaderid());
	    model.addAttribute("classes", getClasses());
	    model.addAttribute("months", BudgetMonth.values());
		
		return "fragments/budget_fragment :: BUDGET-DETAIL-DIV";
	
	}
	
	
	@PostMapping("/savebudgetdata")
	public String  savebudgetdata(@ModelAttribute BudgetForm form,
			@RequestParam String action,Model model,HttpSession session,RedirectAttributes ra) {
				
		
		try {
			
			
			System.out.println("Inside savebudgetdata");
			System.out.println("=====================================");
			System.out.println(form.bheaderid+","+form.financialYear+","+form.budgetNo+","+form.divisionId +","+form.forMonth);
			
			 Long bheaderid = form.getBheaderid();
				String financialYear = form.getFinancialYear();
				String budgetno = form.getBudgetNo();
				String formonth = form.getForMonth();
				String divid = form.getDivisionId();
				
				if(financialYear == null || financialYear.length() != 7 ||
						budgetno == null || budgetno.length() == 0	||
						formonth == null || formonth.length() == 0 ||
						divid == null || divid.length() == 0) {
				
					System.out.println("Insufficient data from front end.");
					ra.addFlashAttribute("msg", "Some error occurred !!");
					return "redirect:budget_detail";
				}
				
				int budget_no_int = Integer.parseInt(budgetno);
				int divid_int = Integer.parseInt(divid);
				if(budget_no_int <=0 || divid_int <=0) {
				
					System.out.println("Invalid budget number or division.");
					ra.addFlashAttribute("msg", "Some error occurred !!");
					return "redirect:budget_detail";
				}
				
				    
				if(bheaderid != null && bheaderid >0) {	
					
					System.out.println("Header id exist, so updated header = " +bheaderid);
					
				   	BudgetHeader bheader = bs.findByBheaderid(bheaderid);
				   	if(bheader != null) {

				   		System.out.println("Detail size = " +bheader.getDetails().size());
				   		
					   	 form.getValues().forEach((key, v) -> {
					   		
					   		if(v == null) return;
					   		
					   		String[] parts = key.split("_");
					        if (parts.length != 3) return;
					        
					        String headcode = parts[0];
					        String measure = parts[1];
					        String month = parts[2];
					        
					        if(headcode.equalsIgnoreCase("TEARN") || month.equalsIgnoreCase("TOT"))
					        	return;
					   		 
					   		BudgetDetail match = bheader.getDetails().stream()
					   			    .filter(d ->
					   			        Objects.equals(d.getHeadcode(), headcode) &&
					   			        Objects.equals(d.getMeasure(), measure) &&
					   			        Objects.equals(d.getMonth(), month)
					   			    )
					   			    .findFirst().orElse(null);
					   		
					   		if(match != null) {
					   			match.setBudgetval(v);
					   		}else {
					   			
					   			BudgetDetail detail = new BudgetDetail();
						        detail.setBheader(bheader);
						        detail.setHeadcode(headcode);
						        detail.setMeasure(measure);
						        detail.setMonth(month);
						        detail.setBudgetval(v);
						        bheader.addDetail(detail);		
					   		}
					   		 
					   	 });
					   	 
						    bs.saveBudgetHeader(bheader);
						    ra.addFlashAttribute("msg", "Data saved successfully.");
						    ra.addFlashAttribute("msgType", "success");

						    
							/*
							 * for (BudgetDetail detail : bheader.getDetails()) { String key =
							 * detail.getHeadcode() + "_" + detail.getMeasure() + "_" + detail.getMonth();
							 * BigDecimal val = form.getValues().get(key); detail.setBudgetval(val); }
							 */

					} else {

						System.out.println("Budget Header detail not found for id = " + bheaderid);
						ra.addFlashAttribute("msg", "Some error occurred !!");
						ra.addFlashAttribute("msgType", "error");
					}
				    	
				 }else {
				    	
					 System.out.println("Header id does not exist, so insert header.");
					 
				    	BudgetHeader bheader = new BudgetHeader();
				    	bheader.setFinancialyear(financialYear.trim());
				    	bheader.setBudgetno(budget_no_int);
				    	bheader.setFormonth(formonth.trim());
				    	bheader.setDivid(divid_int);
				    	
					    form.getValues().forEach((key, v) -> {
					    	
					    	if(v == null) return;
					    	
					        String[] parts = key.split("_");
					        if (parts.length != 3) return;

					        
					        String headcode = parts[0];
					        String measure = parts[1];
					        String month = parts[2];
					        BigDecimal budgetval = v;
					        
					        if(headcode.equalsIgnoreCase("TEARN") || month.equalsIgnoreCase("TOT"))
					        	return;
					        
					        BudgetDetail detail = new BudgetDetail();
					        detail.setBheader(bheader);
					        detail.setHeadcode(headcode);
					        detail.setMeasure(measure);
					        detail.setMonth(month);
					        detail.setBudgetval(budgetval);
					        bheader.addDetail(detail);					        
					    });
				    	
					    
					    bs.saveBudgetHeader(bheader);
					    ra.addFlashAttribute("msg", "Data saved successfully.");
					    ra.addFlashAttribute("msgType", "success");
				    }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
			ra.addFlashAttribute("msg", "Exception occured, please try later.");
			ra.addFlashAttribute("msgType", "error");
			return "redirect:budget_detail";
		}
	   
		
		return "redirect:budget_detail";
	}
	
	@PostMapping("/getMaxBudgetnumber")
	@ResponseBody
	public Map<String, Object> getMaxBudgetnumber(@RequestParam String financialyear,@RequestParam String division, HttpSession session) {
					
		System.out.println("getMaxBudgetnumber called");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		try {
			
			System.out.println("financialyear = "+financialyear);
			System.out.println("division = "+division);
			if(financialyear != null && financialyear.length() == 7 && division !=null && division.length() >0 && !division.equalsIgnoreCase("-2")) {				
				int maxbudgetno = abs.getMaxBudgetNumber(financialyear,division);
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
	@ResponseBody
	public Map<String, Object> getForMonthForBudgetnumber(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("getForMonthForBudgetnumber called");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		try {
			
			String fy = (String) data.get("financialyear");
			String budgetno = (String) data.get("budgetnumber");
			String division = (String) data.get("division");
			System.out.println("fy = "+fy+"budgetno = "+budgetno + " division = "+division);
			if(fy != null && fy.length() == 7 && budgetno !=null && budgetno.length() >0 && division !=null && division.length() >0) {				
				String formonth = abs.getForMonthForBudgetnumber(fy,division,budgetno);
				response.put("status", "success");
				response.put("formonth", formonth);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		
		return response;
	}
	
	/*
	 * public String getMonthValForIndex(String val) {
	 * 
	 * String month = ""; switch (val){
	 * 
	 * case "1": month = "04"; break; case "2": month = "05"; break; case "3": month
	 * = "06"; break; case "4": month = "07"; break; case "5": month = "08"; break;
	 * case "6": month = "09"; break; case "7": month = "10"; break; case "8": month
	 * = "11"; break; case "9": month = "12"; break; case "10": month = "01"; break;
	 * case "11": month = "02"; break; case "12": month = "03"; break; case "13":
	 * month = ""; break; default: month = ""; }
	 * 
	 * return month; }
	 */


	public List<BudgetClassItem> getClasses() {

		List<BudgetClassItem> classes = new ArrayList<>();
		classes.add(new BudgetClassItem("PNSUB", "PASSENGER","UNIT","Units",true,true,2));
		classes.add(new BudgetClassItem("PNSUB", "PASSENGER","AMT","Amount",true,false,0));

		classes.add(new BudgetClassItem("ORGG", "ORIGINATING GOODS","UNIT","Units",true,true,2));
		classes.add(new BudgetClassItem("ORGG", "ORIGINATING GOODS","AMT","Amount",true,false,0));

		
		classes.add(new BudgetClassItem("OTHC", "OTHER COACHING","AMT","Amount",true,true,1));				
		classes.add(new BudgetClassItem("OTHG", "OTHER GOODS","AMT","Amount",true,true,1));
		classes.add(new BudgetClassItem("SUN", "SUNDRY","AMT","Amount",true,true,1));
		
		classes.add(new BudgetClassItem("TEARN", "TOTAL EARNING","UNIT","Units",false,true,2));
		classes.add(new BudgetClassItem("TEARN", "TOTAL EARNING","AMT","Amount",false,false,0));
		
		
		return classes;
	}
	
	

}
