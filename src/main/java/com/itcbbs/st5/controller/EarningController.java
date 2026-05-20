package com.itcbbs.st5.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.itcbbs.st5.dao.ClassItem;
import com.itcbbs.st5.dao.MonthDao;
import com.itcbbs.st5.dao.StationEarningDetail;
import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.dao.StationProjection;
import com.itcbbs.st5.dao.User;
import com.itcbbs.st5.repository.EarningDetailRepo;
import com.itcbbs.st5.repository.EarningRepo;
import com.itcbbs.st5.services.EarningService;
import com.itcbbs.st5.util.AppUtil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/earning")
public class EarningController {

	@Autowired
	EarningRepo earnRepo;
	
	@Autowired
	EarningDetailRepo earnDetailRepo;

	@Autowired
	EarningService earnser;
	
	
	public String getFragmentForHeadOfAccount(String headOfAcct, Long headerid ) {
		if(headOfAcct.equalsIgnoreCase("PNSUB") || headOfAcct.equalsIgnoreCase("PSUB"))			
			return "fragments/earning_fragment :: PNSUB-DIV(headerId="+ headerid+")";
		else if(headOfAcct.equalsIgnoreCase("ORGG"))
			return "fragments/earning_fragment :: ORGG-DIV(headerId="+ headerid+")";
		else if(headOfAcct.equalsIgnoreCase("OTHC"))
			return "fragments/earning_fragment :: OTHC-DIV(headerId=" + headerid+")";
		else if(headOfAcct.equalsIgnoreCase("OTHG"))
			return "fragments/earning_fragment :: OTHG-DIV(headerId="+ headerid+")";
		else if(headOfAcct.equalsIgnoreCase("SUN"))
			return "fragments/earning_fragment :: SUN-DIV(headerId="+ headerid+")";
		else
			return "fragments/header_footer :: ERROR-DIV";
	}
	
	@PostMapping("/deleteData")
	@ResponseBody
	public String deleteData(@ModelAttribute StationEarningHeader earningHeader, Model model,HttpSession session) {

		System.out.println("inside deleteData");
		System.out.println("recordid = "+earningHeader.getRecordid());		
		Long headerId = null;
		try {
			
			if(earningHeader.getRecordid() != null && earningHeader.getRecordid() >0) {
			
				
				String status = earnser.deleteHeaderAndDetail(earningHeader.getRecordid());
				System.out.println("status = "+status);
				/*
				 * if(status.equalsIgnoreCase("SUCCESS")) {
				 * 
				 * headerId= null; StationEarningHeader newHeader = cloneHeader(earningHeader,
				 * earningHeader.getPeriod(), earningHeader.getFormonth(),
				 * earningHeader.getFinancialyear()); List<ClassItem> classes =
				 * getClasses(earningHeader.getHeadofaccounts()); Map<String,
				 * StationEarningDetail> detailMap = new LinkedHashMap<>();
				 * 
				 * // 1️ Create empty rows for ALL classes for (ClassItem c : classes) {
				 * StationEarningDetail d = new StationEarningDetail(); d.setClasscode(c.code);
				 * detailMap.put(c.code, d); }
				 * 
				 * newHeader.setDetails(new ArrayList<>(detailMap.values()));
				 * model.addAttribute("headerId", null); model.addAttribute("classes", classes);
				 * model.addAttribute("earningHeader", newHeader); model.addAttribute("seg_msg",
				 * "Data deleted successfully."); model.addAttribute("seg_msgType", "success");
				 * 
				 * }else {
				 * 
				 * Map<String, StationEarningDetail> detailMap = new LinkedHashMap<>();
				 * List<ClassItem> classes = getClasses(earningHeader.getHeadofaccounts()); // ✅
				 * Existing data → Load full object with details for (StationEarningDetail dbd :
				 * earningHeader.getDetails()) { detailMap.put(dbd.getClasscode(), dbd); }
				 * 
				 * headerId= earningHeader.getRecordid(); model.addAttribute("headerId",
				 * earningHeader.getRecordid()); model.addAttribute("classes", classes);
				 * model.addAttribute("earningHeader", earningHeader);
				 * model.addAttribute("seg_msg", "Some error occured while deleting data.");
				 * model.addAttribute("seg_msgType", "error");
				 * 
				 * 
				 * }
				 */					
				return status;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		/*
		 * return
		 * getFragmentForHeadOfAccount(earningHeader.getHeadofaccounts(),headerId);
		 */
		return "error";
	}
	
	
	@PostMapping("/getstationdata")
	@ResponseBody
	public Map<String, Object> getstationdata(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getstationdata ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		try {
			
			String fy = (String) data.get("fy");
			String formonth = (String) data.get("formonth");
			String period = (String) data.get("period");
			String hoa = (String) data.get("hoa");
			String system = (String) data.get("system");
			String entrytype = (String) data.get("entrytype");			
			String division = (String) data.get("division");
			String stntype = (String) data.get("stntype");

			/*
			 * System.out.println("fy = "+fy); System.out.println("formonth = "+formonth);
			 * System.out.println("period = "+period); System.out.println("hoa = "+hoa);
			 * 
			 * System.out.println("system = "+system);
			 * System.out.println("entrytype = "+entrytype);
			 * System.out.println("division = "+division);
			 * System.out.println("stntype = "+stntype);
			 * 
			 */			
			
			if(!entrytype.equalsIgnoreCase("SW") || ( division != null && !division.equalsIgnoreCase("-1") && 
					stntype !=null && !stntype.equalsIgnoreCase("-1")  && stntype.length() ==2) ) {
				
				List<StationProjection> dataList = earnser.getStationsWithValueType(fy,formonth,period,entrytype,system,hoa,division,stntype);
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
	
	@PostMapping("/getLast3EarningFy")
	@ResponseBody
	public Map<String, Object> getLast3EarningFy(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getLast3EarningFy ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		
		try {
			
			String entrytype = (String) data.get("entrytype");
			entrytype = entrytype == null?"":entrytype.trim();
			
			String system = (String) data.get("system");
			system = system == null?"":system.trim();
			
			String headOfAccounts = (String) data.get("hoa");
			headOfAccounts = headOfAccounts == null?"":headOfAccounts.trim();
			
			String divisionid = (String) data.get("division");			
			divisionid = divisionid == null?"":divisionid.trim();
			
			int divisionint = -1;
			if(divisionid.length()>0)
				divisionint = Integer.parseInt(divisionid);
			
			String station = (String) data.get("station");
			station = station == null?"":station.trim();

			System.out.println("entrytype = "+entrytype+" system = "+system +" headOfAccounts = "+headOfAccounts +" divisionid = "+divisionid +" station = "+station );
			
			List<String> dataList = earnser.getLast3EarningFy(entrytype,system,headOfAccounts,divisionint,station);
			response.put("status", "success");
			response.put("data", dataList);	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	@PostMapping("/getEarningMonths")
	@ResponseBody
	public Map<String, Object> getEarningMonths(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getLast3EarningFy ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		
		try {
			
			String fy = (String) data.get("fy");
			fy = fy == null?"":fy.trim();
			
			String entrytype = (String) data.get("entrytype");
			entrytype = entrytype == null?"":entrytype.trim();
			
			String system = (String) data.get("system");
			system = system == null?"":system.trim();
			
			String headOfAccounts = (String) data.get("hoa");
			headOfAccounts = headOfAccounts == null?"":headOfAccounts.trim();
			
			String divisionid = (String) data.get("division");			
			divisionid = divisionid == null?"":divisionid.trim();
			
			int divisionint = -1;
			if(divisionid.length()>0)
				divisionint = Integer.parseInt(divisionid);
			
			String station = (String) data.get("station");
			station = station == null?"":station.trim();

			System.out.println("fy= "+fy+"entrytype = "+entrytype+" system = "+system +" headOfAccounts = "+headOfAccounts +" divisionid = "+divisionid +" station = "+station );
			
			List<String> dataList = earnser.getEarningMonths(fy,entrytype,system,headOfAccounts,divisionint,station);
			List<MonthDao> monthList = AppUtil.generateMonthDetails(dataList);
			response.put("status", "success");
			response.put("data", monthList);	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	@PostMapping("/getEarningPeriod")
	@ResponseBody
	public Map<String, Object> getEarningPeriod(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getLast3EarningFy ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		
		try {
			
			String fy = (String) data.get("fy");
			fy = fy == null?"":fy.trim();
			
			String formonth = (String) data.get("formonth");
			formonth = formonth == null?"":formonth.trim();
			
			String entrytype = (String) data.get("entrytype");
			entrytype = entrytype == null?"":entrytype.trim();
			
			String system = (String) data.get("system");
			system = system == null?"":system.trim();
			
			String headOfAccounts = (String) data.get("hoa");
			headOfAccounts = headOfAccounts == null?"":headOfAccounts.trim();
			
			String divisionid = (String) data.get("division");			
			divisionid = divisionid == null?"":divisionid.trim();
			
			int divisionint = -1;
			if(divisionid.length()>0)
				divisionint = Integer.parseInt(divisionid);
			
			String station = (String) data.get("station");
			station = station == null?"":station.trim();

			System.out.println("fy = " + fy+" formonth ="+ formonth+" entrytype = "+entrytype+" system = "+system +" headOfAccounts = "+headOfAccounts +" divisionid = "+divisionid +" station = "+station );
			
			List<String> dataList = earnser.getEarningPeriod(fy,formonth,entrytype,system,headOfAccounts,divisionint,station);
			response.put("status", "success");
			response.put("data", dataList);	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	@PostMapping("/getEarningValueType")
	@ResponseBody
	public Map<String, Object> getEarningValueType(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside getLast3EarningFy ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		
		try {
			
			String fy = (String) data.get("fy");
			fy = fy == null?"":fy.trim();
			
			String formonth = (String) data.get("formonth");
			formonth = formonth == null?"":formonth.trim();
			
			String period = (String) data.get("period");
			period = period == null?"":period.trim();
			
			String entrytype = (String) data.get("entrytype");
			entrytype = entrytype == null?"":entrytype.trim();
			
			String system = (String) data.get("system");
			system = system == null?"":system.trim();
			
			String headOfAccounts = (String) data.get("hoa");
			headOfAccounts = headOfAccounts == null?"":headOfAccounts.trim();
			
			String divisionid = (String) data.get("division");			
			divisionid = divisionid == null?"":divisionid.trim();
			
			int divisionint = -1;
			if(divisionid.length()>0)
				divisionint = Integer.parseInt(divisionid);
			
			String station = (String) data.get("station");
			station = station == null?"":station.trim();

			System.out.println("entrytype = "+entrytype+" system = "+system +" headOfAccounts = "+headOfAccounts +" divisionid = "+divisionid +" station = "+station );
			
			List<String> dataList = earnser.getEarningValueType(fy,formonth,period,entrytype,system,headOfAccounts,divisionint,station);
			response.put("status", "success");
			response.put("data", dataList);	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	
	@PostMapping("/toggle_value_type")
	@ResponseBody
	public Map<String, Object> toggle_value_type(@RequestBody Map<String, Object> data,HttpSession session) {
					
		System.out.println("inside approximate_to_original ");
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");		
		response.put("valuetype","");
		try {
			
			String headerid = (String) data.get("headerid");
			System.out.println("Convert header ="+headerid +"to original");
			long l_headerid = -1;
			if(headerid !=null && headerid.length()>0)
				l_headerid = Integer.parseInt(headerid);
						
			if(l_headerid >0) {				
				String result = earnser.toggle_value_type(l_headerid);
				System.out.println("result = " +result);
				if(result.equalsIgnoreCase("UPDATED_TO_O")) {
					response.put("status", "success");
					response.put("valuetype", "O");
				}else if(result.equalsIgnoreCase("UPDATED_TO_A")) {
					response.put("status", "success");
					response.put("valuetype", "A");
				}
					
				
			}else {
				System.out.println("Some error in data");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	
	@PostMapping("/getApproximateData")
	public String getApproximateData(@ModelAttribute StationEarningHeader earningHeader, @RequestParam String org_period, Model model,HttpSession session) {
		
		Integer togglebtn =  (Integer)model.getAttribute("togglebtn");
		System.out.println("togglebtn = " + togglebtn);
		try {


			System.out.println("inside getApproximateData");
			System.out.println("org_period = "+org_period);
			List<ClassItem> classes = getClasses(earningHeader.getHeadofaccounts());


			System.out.println("FY = " + earningHeader.getFinancialyear() + "\n" + "For month ="
					+ earningHeader.getFormonth() + "\n" + "Period = " + earningHeader.getPeriod() + "\n"
					+ "Entry type = " + earningHeader.getEntrytype() + "\n" + "System Type = "
					+ earningHeader.getSystemtype() + "\n" + "Head of Accounts = " + earningHeader.getHeadofaccounts()
					+ "\n" + "Division = " + earningHeader.getDivisionid() + "\n" + "Station = "
					+ earningHeader.getStationcode() + "\n" + "Value type = " + earningHeader.getValuetype());


			StationEarningHeader dbheader = null;
			int earningheaderCount = 0;
			dbheader = earnser.getEarningHeader(earningHeader.getFinancialyear(),
						earningHeader.getFormonth(), earningHeader.getPeriod(), earningHeader.getEntrytype(),
						earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivisionid(),
						earningHeader.getStationcode(), earningHeader.getValuetype());
					

			int daysInMonth  = AppUtil.getDaysInMonth(earningHeader.getFormonth() , earningHeader.getFinancialyear());			
			Map<String, StationEarningDetail> detailMap = new LinkedHashMap<>();

			// 1️ Create empty rows for ALL classes
			for (ClassItem c : classes) {
			    StationEarningDetail d = new StationEarningDetail();
			    d.setClasscode(c.code);
			    detailMap.put(c.code, d);
			}
			
			
			if (dbheader != null) {				
				System.out.println("Header exist");

				// ✅ Existing data → Load full object with details
				 for (StationEarningDetail dbd : dbheader.getDetails()) {
					 
					 dbd.setRecordid(null);
				     detailMap.put(dbd.getClasscode(), 
				        		(!org_period.equalsIgnoreCase("MON") && earningHeader.getPeriod().equalsIgnoreCase("MON"))?cloneDetailWithProjection(dbd, daysInMonth,org_period):dbd);
				  }
				 
				earningHeader = dbheader;
				
			}
			
			earningHeader.setRecordid(null);			
			earningHeader.setDetails(new ArrayList<>(detailMap.values()));
			
			calculateAggregateValues(earningHeader);		
			model.addAttribute("headerId", null);
			model.addAttribute("classes", classes);
			model.addAttribute("earningHeader", earningHeader);
			model.addAttribute("earningHeaderCount", earningheaderCount);
			
			model.addAttribute("iii_period_headerid", null);		
			model.addAttribute("iii_period_parentrecordid", null);		
			model.addAttribute("monthlyHeaderId",null);


			
			model.addAttribute("submitbtn", 1);
			model.addAttribute("togglebtn", 0);
			model.addAttribute("deletebtn", 0);
			model.addAttribute("aprxbtn", 1);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			model.addAttribute("submitbtn", 0);
			model.addAttribute("deletebtn", 0);
			model.addAttribute("aprxbtn", 0);
			model.addAttribute("togglebtn", 0);
		}

		
		return getFragmentForHeadOfAccount(earningHeader.getHeadofaccounts(), earningHeader.getRecordid());
		
	}
	
	
	
	@GetMapping("/earning_detail")
	public String originating_period(Model model) {

		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */
		List<ClassItem> classes = getClasses("PNSUB");
		StationEarningHeader header = new StationEarningHeader();

		List<StationEarningDetail> details = new ArrayList<>();
		for (ClassItem c : classes) {
			StationEarningDetail d = new StationEarningDetail();
			d.setClasscode(c.code);
			details.add(d);
		}

		header.setDetails(details);
		
		model.addAttribute("earningHeader", header);		
		model.addAttribute("classes", classes);
		model.addAttribute("earningHeaderCount", 0);
		/* return "originating_period"; */
		return "earning_detail";
	}

	@PostMapping("/getdetails")
	public String getEarningDetails(@RequestParam String mode, @ModelAttribute StationEarningHeader earningHeader, Model model,
			HttpSession session, HttpServletRequest request,
	        HttpServletResponse response) {


		
		try {


			System.out.println("inside getdetails");
			System.out.println("mode = " +mode);	
			List<ClassItem> classes = getClasses(earningHeader.getHeadofaccounts());


			System.out.println("Record Id = " + earningHeader.getRecordid() + "\n"+"FY = " + earningHeader.getFinancialyear() + "\n" + "For month ="
					+ earningHeader.getFormonth() + "\n" + "Period = " + earningHeader.getPeriod() + "\n"
					+ "Entry type = " + earningHeader.getEntrytype() + "\n" + "System Type = "
					+ earningHeader.getSystemtype() + "\n" + "Head of Accounts = " + earningHeader.getHeadofaccounts()
					+ "\n" + "Division = " + earningHeader.getDivisionid() + "\n" + "Station = "
					+ earningHeader.getStationcode() + "\n" + "Value type = " + earningHeader.getValuetype());

			int earningheaderCount = 0; 
			Long monthlyHeaderId = null; 
			StationEarningHeader dbheader = null;
			StationEarningHeader iii_period_header = null;

			if("load".equals(mode)) {
				
								 
				
				dbheader = earnser.getEarningHeader(earningHeader.getFinancialyear(),
						earningHeader.getFormonth(), earningHeader.getPeriod(), earningHeader.getEntrytype(),
						earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivisionid(),
						earningHeader.getStationcode(), earningHeader.getValuetype());
				
				//This is to check if both A & O data is there then disable toggle button
				earningheaderCount = earnser.getEarningHeaderCount(earningHeader.getFinancialyear(),
						earningHeader.getFormonth(), earningHeader.getPeriod(), earningHeader.getEntrytype(),
						earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivisionid(),
						earningHeader.getStationcode());

				
				
				if(earningHeader.getPeriod().equalsIgnoreCase("MON")) {
					
					System.out.println("Check header for III period");
					//DOES III period data exist. 
					//If exist then don't allow to feed Monthly data.
					iii_period_header = earnser.getEarningHeaderWithoutValType(earningHeader.getFinancialyear(),
							earningHeader.getFormonth(), "III", earningHeader.getEntrytype(),
							earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivisionid(),
							earningHeader.getStationcode());
				}
				
				
				if(!earningHeader.getPeriod().equalsIgnoreCase("MON")) {
					
					//Check for monthly data exist or not 
					//If exist then don't allow to feed any period data. 
					//AS III = MONTHLY - (I+II)
					monthlyHeaderId = earnser.getEarningHeaderId(earningHeader.getFinancialyear(),
							earningHeader.getFormonth(), "MON", earningHeader.getEntrytype(),
							earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivisionid(),
							earningHeader.getStationcode());
					
				}
				
				
			}
					

			
			Map<String, StationEarningDetail> detailMap = new LinkedHashMap<>();

			// 1️ Create empty rows for ALL classes
			for (ClassItem c : classes) {
			    StationEarningDetail d = new StationEarningDetail();
			    d.setClasscode(c.code);
			    detailMap.put(c.code, d);
			}
			
			
			
			if (dbheader != null) {
				
				System.out.println("Header exist");

				// ✅ Existing data → Load full object with details
				 for (StationEarningDetail dbd : dbheader.getDetails()) {
				        detailMap.put(dbd.getClasscode(), dbd);
				  }
				 
				earningHeader = dbheader;
				
				model.addAttribute("headerId", dbheader.getRecordid());
				System.out.println("headerId = " + dbheader.getRecordid());
				System.out.println("dbheader Detail size = " + dbheader.getDetails().size());
				System.out.println("earningHeader Detail size = " + earningHeader.getDetails().size());

			} else {

				System.out.println("Header doesn't exist");

				/*
				 * List<StationEarningDetail> details = new ArrayList<>(); for (ClassItem c :
				 * classes) { StationEarningDetail d = new StationEarningDetail();
				 * d.setClasscode(c.code); details.add(d); }
				 */

				earningHeader.setRecordid(null);
				model.addAttribute("headerId", null);
			}			
			earningHeader.setDetails(new ArrayList<>(detailMap.values()));
			
			calculateAggregateValues(earningHeader);		
			
			Long iii_period_headerid = 	iii_period_header == null ? null : iii_period_header.getRecordid();
			Long iii_period_parentrecordid =  iii_period_header == null ? null : iii_period_header.getParentrecordid();
			
			model.addAttribute("classes", classes);
			model.addAttribute("earningHeader", earningHeader);					
			model.addAttribute("iii_period_headerid", iii_period_headerid);		
			model.addAttribute("iii_period_parentrecordid", iii_period_parentrecordid);		
			model.addAttribute("earningHeaderCount", earningheaderCount);
			model.addAttribute("monthlyHeaderId", monthlyHeaderId);
			
			
			System.out.println("iii_period_header id =" +iii_period_headerid);
			System.out.println("iii_period_header parent id =" +iii_period_parentrecordid);
			//Submit button disable criteria
			//1. When monthly data is there, then we won't allow any period data to be edited
			//2. When III period data is there then we won't allow monthly data to feed
			if(earningHeader.getPeriod().equalsIgnoreCase("MON") &&  iii_period_header !=null && iii_period_header.getParentrecordid() == null) {
				model.addAttribute("submitbtn", 0);
			}
			else if(!earningHeader.getPeriod().equalsIgnoreCase("MON") && monthlyHeaderId != null) {
				
				model.addAttribute("submitbtn", 0);
				
			}else {
				model.addAttribute("submitbtn", 1);
			}
			
			
			// O-A, A-O button disable criteria
			// 1. Button should be disabled when there is no data at all
			// 2. Button should be disabled when III period record is dragged, that means it means filled from monthly data.
			// 3. Button should be disabled when we go to feed MONTHLY data but III period data already exist, 
			// 4. Button should be disabled when both approximate and original data available 		 
			if(earningHeader.getRecordid() == null ||	earningheaderCount > 1) {
				model.addAttribute("togglebtn", 0);
			}else if (earningHeader.getPeriod().equalsIgnoreCase("MON") &&  iii_period_header !=null && iii_period_header.getParentrecordid() == null) {
				model.addAttribute("togglebtn", 0);
			}else if(!earningHeader.getPeriod().equalsIgnoreCase("MON") && monthlyHeaderId != null) {
				model.addAttribute("togglebtn", 0);
			}
			else {
				model.addAttribute("togglebtn", 1);
			}
			
			// Delete button disable criteria
			if(earningHeader.getRecordid() == null ) {
				model.addAttribute("deletebtn", 0);
			}else if (earningHeader.getPeriod().equalsIgnoreCase("MON") &&  iii_period_header !=null && iii_period_header.getParentrecordid() == null) {
				model.addAttribute("deletebtn", 0);;
			}else if(!earningHeader.getPeriod().equalsIgnoreCase("MON") && monthlyHeaderId != null) {
				model.addAttribute("deletebtn", 0);
			}else {
				model.addAttribute("deletebtn", 1);
			}
						
			// GetApproximate button disable criteria
			if (earningHeader.getRecordid() != null ) {
				model.addAttribute("aprxbtn", 0);
			}else if (earningHeader.getPeriod().equalsIgnoreCase("MON") &&  iii_period_header !=null && iii_period_header.getParentrecordid() == null) {
				model.addAttribute("aprxbtn", 0);
			}else if(!earningHeader.getPeriod().equalsIgnoreCase("MON") && monthlyHeaderId != null) {
				model.addAttribute("aprxbtn", 0);
			}else {
				model.addAttribute("aprxbtn", 1);
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("submitbtn", 0);
			model.addAttribute("deletebtn", 0);
			model.addAttribute("aprxbtn", 0);
			model.addAttribute("togglebtn", 0);
			
		}

		/* setAllClasses(model); */
		/* return "earning_detail"; */
		
		return getFragmentForHeadOfAccount(earningHeader.getHeadofaccounts(), earningHeader.getRecordid());
		
		/*
		 * if(earningHeader.getHeadofaccounts().equalsIgnoreCase("PNSUB")||
		 * earningHeader.getHeadofaccounts().equalsIgnoreCase("PSUB")) return
		 * "fragments/earning_fragment :: PNSUB-DIV(headerId=${headerId})"; else
		 * if(earningHeader.getHeadofaccounts().equalsIgnoreCase("ORGG")) return
		 * "fragments/earning_fragment :: ORGG-DIV(headerId=${headerId})"; else
		 * if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHC")) return
		 * "fragments/earning_fragment :: OTHC-DIV(headerId=${headerId})"; else
		 * if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHG")) return
		 * "fragments/earning_fragment :: OTHG-DIV(headerId=${headerId})"; else
		 * if(earningHeader.getHeadofaccounts().equalsIgnoreCase("SUN")) return
		 * "fragments/earning_fragment :: SUN-DIV(headerId=${headerId})"; else return
		 * "fragments/header_footer :: ERROR-DIV";
		 */
		 
		
	}
	
	
	private void calculateAggregateValues(StationEarningHeader earningHeader) {
		
		if(earningHeader.getHeadofaccounts().equalsIgnoreCase("PNSUB") ||
				earningHeader.getHeadofaccounts().equalsIgnoreCase("PSUB")) {
			
			//Calculate aggregate values
			Map<String, StationEarningDetail> map =
				    earningHeader.getDetails().stream()
				        .collect(Collectors.toMap(
				            StationEarningDetail::getClasscode,
				            Function.identity()
				        ));

				sum("AC", map, "EC","1AC","2AC","3AC","3E","CC");
				sum("FC", map, "FCSEA","FCME","FCORD");
				sum("SLC", map, "SLSEA","SLME","SLORD");
				sum("SC", map, "SCSEA","SCME","SCORD");
				sum("TOT", map, "AC","FC","SLC","SC");
		}else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHC")) {
		
			BigDecimal totalWeight = earningHeader.getDetails().stream()
				            .filter(d -> !"OTHC_TOT".equals(d.getClasscode()))
				            .map(StationEarningDetail::getWeight)
				            .filter(Objects::nonNull)
				            .reduce(BigDecimal.ZERO, BigDecimal::add);
			
			BigDecimal totalEarning =earningHeader.getDetails().stream()
				            .filter(d -> !"OTHC_TOT".equals(d.getClasscode()))
				            .map(StationEarningDetail::getEarning)
				            .filter(Objects::nonNull)
				            .reduce(BigDecimal.ZERO, BigDecimal::add);
			
			//Set values back
			earningHeader.getDetails().stream()
	        .filter(d -> "OTHC_TOT".equals(d.getClasscode()))
	        .findFirst()
	        .ifPresent(tot -> {
	            tot.setWeight(totalWeight);
	            tot.setEarning(totalEarning);
	        });
		}else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHG")) {
		
			BigDecimal totalEarning = earningHeader.getDetails().stream()
				            .filter(d -> !"OTHG_TOT".equals(d.getClasscode()))
				            .map(StationEarningDetail::getEarning)
				            .filter(Objects::nonNull)
				            .reduce(BigDecimal.ZERO, BigDecimal::add);
			
			
			//Set values back
			earningHeader.getDetails().stream()
	        .filter(d -> "OTHG_TOT".equals(d.getClasscode()))
	        .findFirst()
	        .ifPresent(tot -> {
	            tot.setEarning(totalEarning);
	        });
		}else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("SUN")) {
		
			BigDecimal totalEarning = earningHeader.getDetails().stream()
				            .filter(d -> !"SUN_TOT".equals(d.getClasscode()))
				            .map(StationEarningDetail::getEarning)
				            .filter(Objects::nonNull)
				            .reduce(BigDecimal.ZERO, BigDecimal::add);
			
			
			//Set values back
			earningHeader.getDetails().stream()
	        .filter(d -> "SUN_TOT".equals(d.getClasscode()))
	        .findFirst()
	        .ifPresent(tot -> {
	            tot.setEarning(totalEarning);
	        });
		}
		
		
	}
	
	private void sum(String target, Map<String, StationEarningDetail> map, String... sources) {
	    StationEarningDetail t = map.get(target);
	    if (t == null) return;

	    int bc = 0, rc = 0, ec = 0;
	    BigDecimal ba = BigDecimal.ZERO, ra = BigDecimal.ZERO, ea = BigDecimal.ZERO;

	    for (String s : sources) {
	        StationEarningDetail d = map.get(s);
	        if (d == null) continue;

	        bc += safe(d.getBookedcount());
	        rc += safe(d.getRefundcount());
	        ec += safe(d.getExcesscount());

	        ba = ba.add(safe(d.getBookedamt()));
	        ra = ra.add(safe(d.getRefundamt()));
	        ea = ea.add(safe(d.getExcessamt()));
	    }

	    t.setBookedcount(bc);
	    t.setRefundcount(rc);
	    t.setExcesscount(ec);
	    t.setBookedamt(ba);
	    t.setRefundamt(ra);
	    t.setExcessamt(ea);
	}
	
    // ===============================
    // SAFETY HELPERS
    // ===============================
    private int safe(Integer v) {
        return v == null ? 0 : v;
    }

    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
	

	/*
	 * @GetMapping("/getdetails") public String getEarningDetails(Model model
	 * ,HttpSession session) {
	 * 
	 * User usr = (User)session.getAttribute("loggedInUser"); int userid = -1;
	 * if(usr != null) userid = usr.getUserid();
	 * 
	 * 
	 * StationEarningHeader header = new StationEarningHeader();
	 * header.addDetail(create("EC", userid)); header.addDetail(create("1AC",
	 * userid)); header.addDetail(create("2AC", userid));
	 * header.addDetail(create("3AC", userid)); header.addDetail(create("3E",
	 * userid)); header.addDetail(create("CC", userid));
	 * header.addDetail(create("FCSEA", userid)); header.addDetail(create("FCME",
	 * userid)); header.addDetail(create("FCORD", userid));
	 * header.addDetail(create("SLSEA", userid)); header.addDetail(create("SLORD",
	 * userid)); header.addDetail(create("SCSEA", userid));
	 * header.addDetail(create("SCME", userid)); header.addDetail(create("SCORD",
	 * userid));
	 * 
	 * model.addAttribute("earningHeader", header);
	 * 
	 * return "originating_period"; }
	 */

	private StationEarningDetail create(String classCode, int userid) {
		StationEarningDetail sed = new StationEarningDetail();
		sed.setClasscode(classCode);
		sed.setBookedcount(0);
		sed.setBookedamt(BigDecimal.ZERO);

		sed.setRefundcount(0);
		sed.setRefundamt(BigDecimal.ZERO);

		sed.setExcesscount(0);
		sed.setExcessamt(BigDecimal.ZERO);

		sed.setUserid(userid);

		return sed;
	}

	@PostMapping("/process")	
	@ResponseBody
	public HashMap<String, Object>  processEarningDetails(@ModelAttribute StationEarningHeader header, @RequestParam String action,
			Model model,RedirectAttributes ra) {

		System.out.println("inside processEarningDetails ");
		System.out.println("action = " + action);
		System.out.println("Header id = " + header.getRecordid());
		System.out.println("Detail size = " + header.getDetails().size());

		HashMap<String, Object> response = new HashMap<>();
		
		/*
		 * if ("go".equalsIgnoreCase(action)) {
		 * 
		 * return processGo(header, model); }
		 */

		  if ("save".equalsIgnoreCase(action)) {
		  
			  
				/*
				 * System.out.println("Value type = " + header.getValuetype() +"\n"+ "Fy ="+
				 * header.getFinancialyear()+"\n"+ "Formonth = "+header.getFormonth() +"\n"+
				 * "Period = "+header.getPeriod() +"\n"+ "RFY ="+header.getReceivedfy() +"\n"+
				 * "RMonth ="+header.getReceivedmonth() +"\n"+
				 * "RPeriod ="+header.getReceivedperiod() +"\n" );
				 */			  

			  // Here we are checking,			
			  // if approximate data exists then don't allow original data with same receiving period 

			  if(	  header.getValuetype().equalsIgnoreCase("O") &&
					  header.getFinancialyear().equalsIgnoreCase(header.getReceivedfy()) &&
					  header.getFormonth().equalsIgnoreCase(header.getReceivedmonth()) &&
					  header.getPeriod().equalsIgnoreCase(header.getReceivedperiod()) ) {
				  
				  System.out.println("Check for approximate header");
				  StationEarningHeader approxHeader = earnser.getEarningHeader(header.getFinancialyear(),
						  header.getFormonth(), header.getPeriod(), header.getEntrytype(),
						  header.getSystemtype(), header.getHeadofaccounts(), header.getDivisionid(),
						  header.getStationcode(), "A");
				  
				  if(approxHeader !=null ) {
					  
					  System.out.println("Approximate data  present.");
					  response.put("status", "error");
					  response.put("msg", "Aproximate data already exist.\n So Original data receiving period can't same as Originating period.");
					  return response;
				  }else {
					  
					  System.out.println("Approximate data not present.");
				  }
			  }
			  
			  
			  
			 // return processSave(header, model,ra);			 
			  
			  StationEarningHeader savedHeader =  processSave(header, model,ra);
				/*
				 * List<ClassItem> classes = getClasses(savedHeader.getHeadofaccounts());
				 * 
				 * model.addAttribute("classes", classes); model.addAttribute("earningHeader",
				 * savedHeader); model.addAttribute("headerId", savedHeader.getRecordid());
				 * model.addAttribute("seg_msg", savedHeader !=null
				 * ?"Date saved successfully.":"Some error occured.");
				 * model.addAttribute("seg_msgType", savedHeader !=null ?"success":"error");
				 * return getFragmentForHeadOfAccount(savedHeader.getHeadofaccounts(),
				 * savedHeader.getRecordid());
				 */
			  response.put("status", "success");
			  return response;
		  }
		 	
		//return "redirect:earning_detail";
		  
		  //return "fragments/header_footer :: ERROR-DIV";
		  response.put("status", "error");
		  response.put("msg", "Something went wrong. Please try after some time...");
		  return response;
	}

	private boolean isSaveableClass(String classCode) {

		/* System.out.println("classCode = " +classCode); */
		if (classCode.equalsIgnoreCase("AC") || classCode.equalsIgnoreCase("FC") || classCode.equalsIgnoreCase("SLC")
			|| classCode.equalsIgnoreCase("SC") || classCode.equalsIgnoreCase("TOT") || classCode.equalsIgnoreCase("OTHC_TOT")
			|| classCode.equalsIgnoreCase("OTHG_TOT") || classCode.equalsIgnoreCase("SUN_TOT") || classCode.equalsIgnoreCase("ORGG_TOT")) {

			/* System.out.println("saveable  = false"); */
			return false;
		} else {

			/* System.out.println("saveable  = true"); */
			return true;
		}
	}

	private StationEarningHeader  processSave(StationEarningHeader earningHeader, Model model,RedirectAttributes ra) {

		System.out.println("FY = " + earningHeader.getFinancialyear() + "\n" + "For month ="
				+ earningHeader.getFormonth() + "\n" + "Period = " + earningHeader.getPeriod() + "\n" + "Entry type = "
				+ earningHeader.getEntrytype() + "\n" + "System Type = " + earningHeader.getSystemtype() + "\n"
				+ "Head of Accounts = " + earningHeader.getHeadofaccounts() + "\n" 
				+ "Division = "	+ earningHeader.getDivisionid() + "\n"
				+ "Station type = "	+ earningHeader.getStationtype() + "\n"
				+ "Station = " + earningHeader.getStationcode() + "\n"
				+ "Value type = " + earningHeader.getValuetype());


		StationEarningHeader savedHeader = null;

		try {

			boolean isNew = (earningHeader.getRecordid() == null);

			if (!isNew) {

				System.out.println("Old object so update");
				StationEarningHeader dbHeader = earnRepo.findById(earningHeader.getRecordid()).orElse(null);
				if (dbHeader != null) {

					dbHeader.getDetails().clear();
					
					// This is to update the header part like receiving year, month & period etc
					cloneCompleteHeader(dbHeader, earningHeader);

					for (StationEarningDetail d : earningHeader.getDetails()) {

						System.out.println("d recordid = " + d.getRecordid());

						
						  if(!isSaveableClass(d.getClasscode())) 
							  continue;
						 
						d.setBookedcount(d.getBookedcount() == null ? 0 : d.getBookedcount());
						d.setRefundcount(d.getRefundcount() == null ? 0 : d.getRefundcount());
						d.setExcesscount(d.getExcesscount() == null ? 0 : d.getExcesscount());

						d.setBookedamt(d.getBookedamt() == null ? BigDecimal.ZERO : d.getBookedamt());
						d.setRefundamt(d.getRefundamt() == null ? BigDecimal.ZERO : d.getRefundamt());
						d.setExcessamt(d.getExcessamt() == null ? BigDecimal.ZERO : d.getExcessamt());
						
						
						d.setPmwagon(d.getPmwagon() == null?BigDecimal.ZERO : d.getPmwagon());
						d.setPmtonne(d.getPmtonne()==null?BigDecimal.ZERO :d.getPmtonne());
						d.setTpwagon(d.getTpwagon()==null?BigDecimal.ZERO :d.getTpwagon());
						d.setWagon(d.getWagon()==null?BigDecimal.ZERO :d.getWagon());
						d.setTonne(d.getTonne()==null?BigDecimal.ZERO :d.getTonne());
						d.setRate(d.getRate()==null?BigDecimal.ZERO :d.getRate());
						d.setAmount(d.getAmount()==null?BigDecimal.ZERO :d.getAmount());
						d.setWeight(d.getWeight()==null?BigDecimal.ZERO :d.getWeight());
						d.setEarning(d.getEarning()==null?BigDecimal.ZERO :d.getEarning());
						
						

						d.setHeader(dbHeader);
						dbHeader.getDetails().add(d);
					}

					savedHeader = earnser.saveEarningHeader(dbHeader);
					handleMonthlyExtraEntries(dbHeader);
					ra.addFlashAttribute("msg", "Data saved successfully.");
					ra.addFlashAttribute("msgType", "success");
					
				}

			} else {

				System.out.println("New object so insert");

				 List<StationEarningDetail> cleaned = new ArrayList<>(); 

				for (StationEarningDetail d : earningHeader.getDetails()) {

					d.setBookedcount(d.getBookedcount() == null ? 0 : d.getBookedcount());
					d.setRefundcount(d.getRefundcount() == null ? 0 : d.getRefundcount());
					d.setExcesscount(d.getExcesscount() == null ? 0 : d.getExcesscount());

					d.setBookedamt(d.getBookedamt() == null ? BigDecimal.ZERO : d.getBookedamt());
					d.setRefundamt(d.getRefundamt() == null ? BigDecimal.ZERO : d.getRefundamt());
					d.setExcessamt(d.getExcessamt() == null ? BigDecimal.ZERO : d.getExcessamt());
					
					d.setPmwagon(d.getPmwagon() == null ? BigDecimal.ZERO : d.getPmwagon());
					d.setPmtonne(d.getPmtonne() == null ? BigDecimal.ZERO :d.getPmtonne());
					d.setTpwagon(d.getTpwagon() == null ? BigDecimal.ZERO :d.getTpwagon());
					d.setWagon(d.getWagon() == null ? BigDecimal.ZERO :d.getWagon());
					d.setTonne(d.getTonne() == null ? BigDecimal.ZERO :d.getTonne());
					d.setRate(d.getRate() == null ? BigDecimal.ZERO :d.getRate());
					d.setAmount(d.getAmount() == null ? BigDecimal.ZERO :d.getAmount());
					d.setWeight(d.getWeight() ==null ? BigDecimal.ZERO :d.getWeight());
					d.setEarning(d.getEarning() ==null ? BigDecimal.ZERO :d.getEarning());

					d.setHeader(earningHeader);

					  if(isSaveableClass(d.getClasscode())) { 
						  cleaned.add(d); 
					  }
					 
				}

				earningHeader.setDetails(cleaned); 
				savedHeader =  earnser.saveEarningHeader(earningHeader);
				System.out.println("savedEarnHeader recordid = " +savedHeader.getRecordid());
				handleMonthlyExtraEntries(savedHeader);
				ra.addFlashAttribute("msg", "Data saved successfully.");
				ra.addFlashAttribute("msgType", "success");
				/* earnser.save(earningHeader); */
				
			}

			// ✅ Reload blank page after save

		} catch (Exception e) {
			// TODO: handle exception
			ra.addFlashAttribute("msg", "Exception occured, please try later.");
			ra.addFlashAttribute("msgType", "error");
			e.printStackTrace();
			
		}

		//return "redirect:earning_detail";
		return savedHeader;
	}
	
	
	private void handleMonthlyExtraEntries(StationEarningHeader monthlyHeader) {

		System.out.println("handleMonthlyExtraEntries called");
		if(!monthlyHeader.getPeriod().equalsIgnoreCase("MON") ||
		   !monthlyHeader.getEntrytype().equalsIgnoreCase("SW") ||
		   !monthlyHeader.getSystemtype().equalsIgnoreCase("M") ||
		   !(monthlyHeader.getHeadofaccounts().equalsIgnoreCase("PNSUB") || 
				   monthlyHeader.getHeadofaccounts().equalsIgnoreCase("OTHC"))) {
			
			System.out.println("Dragging not required");
			return;
		}
			
			
		System.out.println("Dragging  required");
		
		String curMonth  = monthlyHeader.getFormonth();
		String curFY     = monthlyHeader.getFinancialyear();
		//String nextMonth = AppUtil.getNextMonth(curMonth);
		//String nextFY    = AppUtil.getNextFinancialYear(curFY, curMonth);
		//int daysInMonth  = AppUtil.getDaysInMonth(curMonth, curFY);
		
//		if(nextMonth == "-1" || nextFY == "-1") {			
//			System.out.println("Invalid next month or next financial year");
//			return;
//		}
			
		
		// ── Fetch Period I & II for CURRENT month ───────────────────────────────
		StationEarningHeader p1Header = earnser.getEarningHeaderWithoutValType(curFY, curMonth, "I", monthlyHeader.getEntrytype(),monthlyHeader.getSystemtype(),
				monthlyHeader.getHeadofaccounts(),monthlyHeader.getDivisionid(),monthlyHeader.getStationcode());
		StationEarningHeader p2Header = earnser.getEarningHeaderWithoutValType(curFY, curMonth, "II", monthlyHeader.getEntrytype(),monthlyHeader.getSystemtype(),
				monthlyHeader.getHeadofaccounts(),monthlyHeader.getDivisionid(),monthlyHeader.getStationcode());
		
		List<StationEarningDetail> p1Details = p1Header != null ? p1Header.getDetails() : new ArrayList<>();
		List<StationEarningDetail> p2Details = p2Header != null ? p2Header.getDetails() : new ArrayList<>();
		
		// ── 1. Save Period III = MONTHLY - (I + II) for CURRENT month ───────────
		/*
		 * StationEarningHeader p3Header = cloneHeader(monthlyHeader, "III", curMonth,
		 * curFY); computePeriodIIIDetails(monthlyHeader.getDetails(), p1Details,
		 * p2Details).forEach(p3Header::addDetail); earnser.saveEarningHeader(p3Header);
		 */

		// ── 1. Save OR Update Period III for CURRENT month ──────────────────────
	    saveOrOverwritePeriodIII(monthlyHeader, monthlyHeader.getDetails(), p1Details, p2Details, curMonth, curFY);
		
		// ── 2. Save/Overwrite Period I for NEXT month ────────────────────────────
		//saveOrOverwritePeriod(monthlyHeader, monthlyHeader.getDetails(), "I",  nextMonth, nextFY, daysInMonth);
		
		// ── 3. Save/Overwrite Period II for NEXT month ───────────────────────────
		//saveOrOverwritePeriod(monthlyHeader, monthlyHeader.getDetails(), "II", nextMonth, nextFY, daysInMonth);
		
		 // ── 4. Recompute Period III for NEXT month (NEW) ─────────────────────────
	    // Fetch newly saved I & II for next month
		//recomputePeriodIIIForNextMonth(monthlyHeader,nextMonth, nextFY);
}
	
	private StationEarningHeader cloneHeader(StationEarningHeader source, String period, String forMonth,String financialYear) {
		StationEarningHeader cloned = new StationEarningHeader();
		cloned.setFinancialyear(financialYear);
		cloned.setFormonth(forMonth);
		cloned.setPeriod(period);
		cloned.setEntrytype(source.getEntrytype());
		cloned.setSystemtype(source.getSystemtype());
		cloned.setHeadofaccounts(source.getHeadofaccounts());
		cloned.setDivisionid(source.getDivisionid());
		cloned.setStationcode(source.getStationcode());
		cloned.setStationtype(source.getStationtype());
		cloned.setValuetype(source.getValuetype());
		cloned.setReceivedfy(source.getReceivedfy());
		cloned.setReceivedmonth(source.getReceivedmonth());
		cloned.setReceivedperiod(source.getReceivedperiod());
		cloned.setRemarks(source.getRemarks());
		cloned.setParentrecordid(source.getParentrecordid());
		return cloned;
	}
	
	private void cloneCompleteHeader(StationEarningHeader destination, StationEarningHeader source) {
		destination.setFinancialyear(source.getFinancialyear());
		destination.setFormonth(source.getFormonth());
		destination.setPeriod(source.getPeriod());
		destination.setEntrytype(source.getEntrytype());
		destination.setSystemtype(source.getSystemtype());
		destination.setHeadofaccounts(source.getHeadofaccounts());
		destination.setDivisionid(source.getDivisionid());
		destination.setStationcode(source.getStationcode());
		destination.setStationtype(source.getStationtype());
		destination.setValuetype(source.getValuetype());
		destination.setReceivedfy(source.getReceivedfy());
		destination.setReceivedmonth(source.getReceivedmonth());
		destination.setReceivedperiod(source.getReceivedperiod());
		destination.setRemarks(source.getRemarks());
		destination.setParentrecordid(source.getParentrecordid());
	}
	
	private void cloneReceivingPeriodAndRemark(StationEarningHeader destination, StationEarningHeader source) {
		
		destination.setReceivedfy(source.getReceivedfy());
		destination.setReceivedmonth(source.getReceivedmonth());
		destination.setReceivedperiod(source.getReceivedperiod());
		destination.setRemarks(source.getRemarks());
	}
	
	private List<StationEarningDetail> computePeriodIIIDetails( List<StationEarningDetail> monthly,List<StationEarningDetail> p1List, List<StationEarningDetail> p2List) {

		System.out.println("computePeriodIIIDetails called");
	    List<StationEarningDetail> result = new ArrayList<>();

	    for (StationEarningDetail m : monthly) {
	        String cc = m.getClasscode();

	        StationEarningDetail p1 = p1List.stream()
	                .filter(d -> Objects.equals(d.getClasscode(), cc))
	                .findFirst().orElse(null);

	        StationEarningDetail p2 = p2List.stream()
	                .filter(d -> Objects.equals(d.getClasscode(), cc))
	                .findFirst().orElse(null);

	        // Helper to safely get value or ZERO
	        BigDecimal p1Earning  = p1 != null ? p1.getEarning()   : BigDecimal.ZERO;
	        BigDecimal p2Earning  = p2 != null ? p2.getEarning()   : BigDecimal.ZERO;
	        BigDecimal p1Booked   = p1 != null ? p1.getBookedamt() : BigDecimal.ZERO;
	        BigDecimal p2Booked   = p2 != null ? p2.getBookedamt() : BigDecimal.ZERO;
	        BigDecimal p1Refund   = p1 != null ? p1.getRefundamt() : BigDecimal.ZERO;
	        BigDecimal p2Refund   = p2 != null ? p2.getRefundamt() : BigDecimal.ZERO;
	        BigDecimal p1Excess   = p1 != null ? p1.getExcessamt() : BigDecimal.ZERO;
	        BigDecimal p2Excess   = p2 != null ? p2.getExcessamt() : BigDecimal.ZERO;
	        BigDecimal p1Wagon    = p1 != null ? p1.getWagon()     : BigDecimal.ZERO;
	        BigDecimal p2Wagon    = p2 != null ? p2.getWagon()     : BigDecimal.ZERO;
	        BigDecimal p1Tonne    = p1 != null ? p1.getTonne()     : BigDecimal.ZERO;
	        BigDecimal p2Tonne    = p2 != null ? p2.getTonne()     : BigDecimal.ZERO;
	        BigDecimal p1Weight   = p1 != null ? p1.getWeight()    : BigDecimal.ZERO;
	        BigDecimal p2Weight   = p2 != null ? p2.getWeight()    : BigDecimal.ZERO;
	        BigDecimal p1Amount   = p1 != null ? p1.getAmount()    : BigDecimal.ZERO;
	        BigDecimal p2Amount   = p2 != null ? p2.getAmount()    : BigDecimal.ZERO;

	        int p1BookedCnt = p1 != null && p1.getBookedcount() != null ? p1.getBookedcount() : 0;
	        int p2BookedCnt = p2 != null && p2.getBookedcount() != null ? p2.getBookedcount() : 0;
	        int p1RefundCnt = p1 != null && p1.getRefundcount() != null ? p1.getRefundcount() : 0;
	        int p2RefundCnt = p2 != null && p2.getRefundcount() != null ? p2.getRefundcount() : 0;
	        int p1ExcessCnt = p1 != null && p1.getExcesscount() != null ? p1.getExcesscount() : 0;
	        int p2ExcessCnt = p2 != null && p2.getExcesscount() != null ? p2.getExcesscount() : 0;

	        StationEarningDetail p3 = new StationEarningDetail();
	        p3.setClasscode(cc);

	        // ── MONTHLY - (I + II) ───────────────────────────────────────────────
	        p3.setEarning  (m.getEarning()  .subtract(p1Earning).subtract(p2Earning));
	        p3.setBookedamt(m.getBookedamt().subtract(p1Booked) .subtract(p2Booked));
	        p3.setRefundamt(m.getRefundamt().subtract(p1Refund) .subtract(p2Refund));
	        p3.setExcessamt(m.getExcessamt().subtract(p1Excess) .subtract(p2Excess));
	        p3.setWagon    (m.getWagon()   .subtract(p1Wagon)  .subtract(p2Wagon));
	        p3.setTonne    (m.getTonne()   .subtract(p1Tonne)  .subtract(p2Tonne));
	        p3.setWeight   (m.getWeight()  .subtract(p1Weight) .subtract(p2Weight));
	        p3.setAmount   (m.getAmount()  .subtract(p1Amount) .subtract(p2Amount));
	        p3.setRate     (m.getRate());   // Rate carried as-is

	        p3.setBookedcount(m.getBookedcount() - p1BookedCnt - p2BookedCnt);
	        p3.setRefundcount(m.getRefundcount() - p1RefundCnt - p2RefundCnt);
	        p3.setExcesscount(m.getExcesscount() - p1ExcessCnt - p2ExcessCnt);

	        // pmwagon, pmtonne, tpwagon carried as-is (or subtract if needed)
	        p3.setPmwagon(m.getPmwagon());
	        p3.setPmtonne(m.getPmtonne());
	        p3.setTpwagon(m.getTpwagon());

	        result.add(p3);
	    }
	    return result;
	}
	
	private void saveOrOverwritePeriodIII(StationEarningHeader monthlyHeader, List<StationEarningDetail> monthlyDetails,
			List<StationEarningDetail> p1Details, List<StationEarningDetail> p2Details, String curMonth, String curFY) {

		// Compute III = MONTHLY - (I + II)
		List<StationEarningDetail> p3Details = computePeriodIIIDetails(monthlyDetails, p1Details, p2Details);

		// Check if Period III already exists for current month		
		StationEarningHeader existingP3 = earnser.getEarningHeader(curFY, curMonth, "III", monthlyHeader.getEntrytype(),monthlyHeader.getSystemtype(),
				monthlyHeader.getHeadofaccounts(),monthlyHeader.getDivisionid(),monthlyHeader.getStationcode(),monthlyHeader.getValuetype());

		if (existingP3 != null) {
			// ── UPDATE: clear old details and repopulate ─────────────────────────
			System.out.println("Period III exists → Updating for month: " + curMonth);
			
			System.out.println("existingP3.getDetails() size =  " + existingP3.getDetails().size());
			

			
			// ✅ Step 1: Explicitly delete old details from DB first
	       // earnDetailRepo.deleteAll(existingP3.getDetails());
	        // ✅ Step 2: Force DB delete before re-insert	        
	      //  earnDetailRepo.flush(); 
			
	        // ✅ Step 3: Clear the list and add new computed details
			existingP3.getDetails().clear();			
			earnRepo.saveAndFlush(existingP3);
			p3Details.forEach(existingP3::addDetail);
			
			//This is to update receiving year month and period as per monthly header
			cloneReceivingPeriodAndRemark(existingP3, monthlyHeader);
			earnser.saveEarningHeader(existingP3);
		} else {
			// ── INSERT: create new Period III header ─────────────────────────────
			System.out.println("Period III not found → Inserting for month: " + curMonth);
			StationEarningHeader newP3Header = cloneHeader(monthlyHeader, "III", curMonth, curFY);
			// This is to identify that it is dragged period
			newP3Header.setParentrecordid(monthlyHeader.getRecordid());
			
			p3Details.forEach(newP3Header::addDetail);
			earnser.saveEarningHeader(newP3Header);
		}
	}
	
	private void saveOrOverwritePeriod(StationEarningHeader monthlyHeader, List<StationEarningDetail> monthlyDetails,String period, String nextMonth, String nextFY, int daysInMonth) {

		StationEarningHeader existing = earnser.getEarningHeader(nextFY, nextMonth, period, monthlyHeader.getEntrytype(),monthlyHeader.getSystemtype(),
				monthlyHeader.getHeadofaccounts(),monthlyHeader.getDivisionid(),monthlyHeader.getStationcode(),monthlyHeader.getValuetype()); 
				
		if (existing != null) {
			// ── Overwrite: clear old details ────────────────────────────────────
			 // ✅ Step 1: Explicitly delete old details from DB
			// earnDetailRepo.deleteAll(existing.getDetails());
			// ✅ Step 2: Force DELETE before INSERT
		   //  earnDetailRepo.flush(); 

		  // ✅ Step 3: Clear list and add new projected details
			existing.getDetails().clear();
			earnRepo.saveAndFlush(existing);
			monthlyDetails.stream().filter(d -> isSaveableClass(d.getClasscode()))
					.map(d -> cloneDetailWithProjection(d, daysInMonth,period)).forEach(existing::addDetail);
			earnser.saveEarningHeader(existing);
		} else {
			// ── Create new ───────────────────────────────────────────────────────
			StationEarningHeader newHeader = cloneHeader(monthlyHeader, period, nextMonth, nextFY);
			monthlyDetails.stream().filter(d -> isSaveableClass(d.getClasscode()))
					.map(d -> cloneDetailWithProjection(d, daysInMonth,period)).forEach(newHeader::addDetail);
			earnser.saveEarningHeader(newHeader);
		}
	}
	
	
	private void recomputePeriodIIIForNextMonth(StationEarningHeader monthlyHeader, String nextMonth, String nextFY) {

		// ── Fetch MONTHLY for next month (may or may not exist) ──────────────────
		StationEarningHeader nextMonthlyHeader = earnser.getEarningHeader(nextFY, nextMonth, "MON",
				monthlyHeader.getEntrytype(), monthlyHeader.getSystemtype(), monthlyHeader.getHeadofaccounts(),
				monthlyHeader.getDivisionid(), monthlyHeader.getStationcode(), monthlyHeader.getValuetype());

		if (nextMonthlyHeader == null) {
			// MONTHLY for next month doesn't exist yet — skip Period III recompute
			System.out.println("MONTHLY for next month " + nextMonth + " not found → Skipping Period III recompute");
			return;
		}

		// ── Fetch newly saved Period I & II for next month ───────────────────────
		StationEarningHeader nextP1Header = earnser.getEarningHeader(nextFY, nextMonth, "I",
				monthlyHeader.getEntrytype(), monthlyHeader.getSystemtype(), monthlyHeader.getHeadofaccounts(),
				monthlyHeader.getDivisionid(), monthlyHeader.getStationcode(), monthlyHeader.getValuetype());

		StationEarningHeader nextP2Header = earnser.getEarningHeader(nextFY, nextMonth, "II",
				monthlyHeader.getEntrytype(), monthlyHeader.getSystemtype(), monthlyHeader.getHeadofaccounts(),
				monthlyHeader.getDivisionid(), monthlyHeader.getStationcode(), monthlyHeader.getValuetype());

		List<StationEarningDetail> nextMonthlyDetails = nextMonthlyHeader.getDetails();
		List<StationEarningDetail> nextP1Details = nextP1Header != null ? nextP1Header.getDetails() : new ArrayList<>();
		List<StationEarningDetail> nextP2Details = nextP2Header != null ? nextP2Header.getDetails() : new ArrayList<>();

		System.out.println("Recomputing Period III for next month: " + nextMonth);

		// ── Recompute and Save/Update Period III for next month ──────────────────
		saveOrOverwritePeriodIII(monthlyHeader, nextMonthlyDetails, nextP1Details, nextP2Details, nextMonth, nextFY);
	}
	
	
	private StationEarningDetail cloneDetailWithProjection(StationEarningDetail monthly, int daysInMonth, String period) {
		BigDecimal days = BigDecimal.valueOf(daysInMonth);
		StationEarningDetail cloned = new StationEarningDetail();

		BigDecimal mult_factor = period.equalsIgnoreCase("III")?BigDecimal.valueOf(daysInMonth).subtract(BigDecimal.valueOf(20)):BigDecimal.TEN;
		cloned.setClasscode(monthly.getClasscode());

		// ── BigDecimal fields: (value / days) * 10 ──────────────────────────────
		cloned.setEarning(AppUtil.safeDivide(monthly.getEarning(), days).multiply(mult_factor));
		cloned.setBookedamt(AppUtil.safeDivide(monthly.getBookedamt(), days).multiply(mult_factor));
		cloned.setRefundamt(AppUtil.safeDivide(monthly.getRefundamt(), days).multiply(mult_factor));
		cloned.setExcessamt(AppUtil.safeDivide(monthly.getExcessamt(), days).multiply(mult_factor));
		cloned.setWagon(AppUtil.safeDivide(monthly.getWagon(), days).multiply(mult_factor));
		cloned.setTonne(AppUtil.safeDivide(monthly.getTonne(), days).multiply(mult_factor));
		cloned.setWeight(AppUtil.safeDivide(monthly.getWeight(), days).multiply(mult_factor));
		cloned.setAmount(AppUtil.safeDivide(monthly.getAmount(), days).multiply(mult_factor));
		cloned.setRate(AppUtil.safeDivide(monthly.getRate(), days).multiply(mult_factor));
		cloned.setPmwagon(AppUtil.safeDivide(monthly.getPmwagon(), days).multiply(mult_factor));
		cloned.setPmtonne(AppUtil.safeDivide(monthly.getPmtonne(), days).multiply(mult_factor));
		cloned.setTpwagon(AppUtil.safeDivide(monthly.getTpwagon(), days).multiply(mult_factor));

		// ── Integer fields: round((value / days) * 10) ──────────────────────────
		cloned.setBookedcount(AppUtil.divideCount(monthly.getBookedcount(), daysInMonth,mult_factor.intValue()));
		cloned.setRefundcount(AppUtil.divideCount(monthly.getRefundcount(), daysInMonth,mult_factor.intValue()));
		cloned.setExcesscount(AppUtil.divideCount(monthly.getExcesscount(), daysInMonth,mult_factor.intValue()));

		return cloned;
	}
	
	

	@PostMapping("/saveEarningDetail")
	public String saveEarningDetails(@ModelAttribute StationEarningHeader header) {

		System.out.println(header.getDetails().size());

		/*
		 * for (StationEarningDetail eranDetail: header.getDetails()) {
		 * eranDetail.setHeader(header); }
		 */

		/* earnRepo.save(header); */

		return "redirect:/earning_detail";
	}

	
	
	public List<ClassItem> getClasses(String hoa) {

		List<ClassItem> classes = new ArrayList<>();
		if(hoa.equalsIgnoreCase("PNSUB") || hoa.equalsIgnoreCase("PSUB")) {
			
			classes.add(new ClassItem("EC", "Executive Class", true));
			classes.add(new ClassItem("1AC", "1st A/C", true));
			classes.add(new ClassItem("2AC", "2nd A/C", true));
			classes.add(new ClassItem("3AC", "3rd A/C", true));
			classes.add(new ClassItem("3E", "3E A/C", true));
			classes.add(new ClassItem("CC", "Chair Car", true));
			classes.add(new ClassItem("AC", "AIR CONDITION", false));
			classes.add(new ClassItem("FCSEA", "Season", true));
			classes.add(new ClassItem("FCME", "Mail & Express", true));
			classes.add(new ClassItem("FCORD", "Ordinary", true));
			classes.add(new ClassItem("FC", "FIRST CLASS", false));
			classes.add(new ClassItem("SLSEA", "Season", true));
			classes.add(new ClassItem("SLME", "Mail & Express", true));
			classes.add(new ClassItem("SLORD", "Ordinary", true));
			classes.add(new ClassItem("SLC", "SLEEPER CLASS", false));
			classes.add(new ClassItem("SCSEA", "Season", true));
			classes.add(new ClassItem("SCME", "Mail & Express", true));
			classes.add(new ClassItem("SCORD", "Ordinary", true));
			classes.add(new ClassItem("SC", "SECOND CLASS", false));
			classes.add(new ClassItem("TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("OTHC")) {
			
			// Other Coaching
			classes.add(new ClassItem("OTHC_LIMB", "Luggage including military baggage", true));
			classes.add(new ClassItem("OTHC_CHDMT", "Carriage, Horses and Dogs & Motor traffic", true));
			classes.add(new ClassItem("OTHC_PCFIT", "Penalties collected for irregular travelling", true));
			classes.add(new ClassItem("OTHC_PTPT", "Parcel to-pay traffic", true));
			classes.add(new ClassItem("OTHC_PPT", "Parcel paid traffic", true));
			classes.add(new ClassItem("OTHC_STARC", "Special trains & reserved carriages", true));
			classes.add(new ClassItem("OTHC_LLDWSCP", "Left luggage, demurrage,wharfage, storeage collected parcels", true));
			classes.add(new ClassItem("OTHC_MOCE", "Misc. other coach earnings", true));
			classes.add(new ClassItem("OTHC_UDBS", "Undercharges detected by the station at the time of delivery of parcels etc.", true));
			classes.add(new ClassItem("OTHC_OABS","Overcharges allowed by the station at the time of deleivery of parcels etc.(Less)", true));
			classes.add(new ClassItem("OTHC_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("OTHG")) {
			
			// Other Goods 
			classes.add(new ClassItem("OTHG_DWSC", "Demurrage,Wharfage & Storages Collected", true));
			classes.add(new ClassItem("OTHG_OMGE", "Other Miscellaneous Goods Earnings", true));
			classes.add(new ClassItem("OTHG_UDBS", "Undercharges Detected by the station at the time of Delivery", true));
			classes.add(new ClassItem("OTHG_OABS", "Overcharges allowed by the station at the time of deleivery", true));
			classes.add(new ClassItem("OTHG_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("SUN")) {
			
			// Other Goods 
			classes.add(new ClassItem("SUN_TE", "Telegraph Earning", true));
			classes.add(new ClassItem("SUN_SEBBO", "All Other Sundry Earnings Collected in Booking Office", true));
			classes.add(new ClassItem("SUN_SEBGO", "All Other Sundry Earnings Collected in Goods Office", true));
			classes.add(new ClassItem("SUN_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("ORGG")) {
			
			// Originating Goods 
			classes.add(new ClassItem("ORGG_OG", "Originating Goods", true));
			classes.add(new ClassItem("ORGG_TOT", "TOTAL", false));	
			
		}
		
		return classes;
	}

}
