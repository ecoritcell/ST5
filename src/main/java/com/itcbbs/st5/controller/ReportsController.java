package com.itcbbs.st5.controller;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.itcbbs.st5.St5Application;
import com.itcbbs.st5.dao.NoOfTonnesLoadedDao;
import com.itcbbs.st5.dao.NoOfWagonLoadedDao;
import com.itcbbs.st5.dao.OrggOthgReportDao;
import com.itcbbs.st5.dao.OriginatingRevenueReportDao;
import com.itcbbs.st5.dao.OtherCoachingCombinedDao;
import com.itcbbs.st5.dao.OtherCoachingReportDao;
import com.itcbbs.st5.dao.PassengerReportDao;
import com.itcbbs.st5.services.ReportService;
import com.itcbbs.st5.util.AppUtil;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    private final St5Application st5Application;

	@Autowired
	ReportService rs;


    ReportsController(St5Application st5Application) {
        this.st5Application = st5Application;
    }
	
	
	@GetMapping("/passenger")
	public String passengeReport(Model model) {

		return "reports/passenger_report";				
	}
	
	
	@GetMapping("/orgg_othg_report")
	public String orgg_othg_report(Model model) {

		return "reports/orgg_othg_report";				
	}
	
	

	@GetMapping("/10_days_advance_report")
	public String st5Report(Model model) {

		return "reports/10_days_advance_report";				
	}
	
	
	
	@GetMapping("/getPassengerReport")
	public String getPassengerReport(@RequestParam Map<String, String> params,Model model) {

		 String curr_fy = params.get("financialyear");
		 String forMonth = params.get("formonth");
		 String period = params.get("period");
		 String unit = params.get("unit");
		 String prev_fy = getPreviousFinancialYear(curr_fy);
		 
		 System.out.println("curr_fy = " + curr_fy);
		 System.out.println("forMonth = " + forMonth);
		 System.out.println("period = " + period);
		 System.out.println("prev_fy = " + prev_fy);

		 List<PassengerReportDao> for_theperiod_datalist = null;
		 List<PassengerReportDao> upto_theperiod_datalist = null;
		 
		 List<OtherCoachingReportDao> othc_for_theperiod = null;
		 List<OtherCoachingReportDao> othc_upto_theperiod = null;
		 
		 if(curr_fy !=null && curr_fy.length() ==7 &&
				 prev_fy !=null && prev_fy.length() ==7 &&
				 forMonth !=null && forMonth.length() ==2 &&
				 period !=null && period.length()>0) {
			 
			//Periodic data
			 for_theperiod_datalist	= rs.getOriginating_Rev_No_Div_wise(prev_fy, curr_fy,forMonth,forMonth, period,false,"Thousand");
			 
			//Cummulative data
			upto_theperiod_datalist = rs.getOriginating_Rev_No_Div_wise(prev_fy, curr_fy,"04",forMonth, period,true,"Thousand");
			
			//Periodic data
			othc_for_theperiod = rs.getOtherCoachingRevDivWise(prev_fy, curr_fy, forMonth,forMonth, period,false,"Thousand");
			 
			//Cummulative data 
			othc_upto_theperiod = rs.getOtherCoachingRevDivWise(prev_fy, curr_fy,"04",forMonth, period,true,"Thousand");
			 
			 
		 }else {
			 
			 for_theperiod_datalist = new ArrayList<>();
			 upto_theperiod_datalist = new ArrayList<>();
			 
			 othc_for_theperiod = new ArrayList<>();
			 othc_upto_theperiod = new ArrayList<>();
		 }
		 
		 List<OtherCoachingCombinedDao> othc_combined = AppUtil.zipOthc(othc_for_theperiod, othc_upto_theperiod);
		 		    
		Map<String, String> fromToPeriodMap = getFromAndToPeriod(curr_fy, forMonth, period);
		
		//For cumulative Period
		Map<String, String> cum_fromToPeriodMap = getCummulativeFromAndToPeriod(curr_fy, forMonth,period);  
		
		model.addAttribute("for_theperiod",for_theperiod_datalist);
		model.addAttribute("upto_theperiod",upto_theperiod_datalist);
		
		
		/*
		 * model.addAttribute("othc_for_theperiod",othc_for_theperiod);
		 * model.addAttribute("othc_upto_theperiod",othc_upto_theperiod);
		 */		
		model.addAttribute("othc_combined",othc_combined);
		model.addAttribute("curr_fy",curr_fy);
		model.addAttribute("prev_fy",prev_fy);
		model.addAttribute("fromPeriod",fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("toPeriod",fromToPeriodMap.get("toPeriod"));
		
		model.addAttribute("cumFromPeriod",cum_fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("cumToPeriod",cum_fromToPeriodMap.get("toPeriod"));
		
		/* return "fragments/report_fragment :: PASSENGER-REPORT-DIV"; */
		 return "fragments/div_wise_passenger_no_and_rev :: PASSENGER-REPORT-DIV"; 
	}
	
	@GetMapping("/OriginatingRevenue")
	public String getST5Report(@RequestParam Map<String, String> params,Model model) {

		 String curr_fy = params.get("financialyear");
		 String forMonth      = params.get("formonth");
		 String period        = params.get("period");
		 String unit = params.get("unit");
		 String prev_fy = getPreviousFinancialYear(curr_fy);
		 
		 System.out.println("curr_fy = " + curr_fy);
		 System.out.println("forMonth = " + forMonth);
		 System.out.println("period = " + period);
		 System.out.println("prev_fy = " + prev_fy);
		 System.out.println("unit = " + unit);

		 List<OriginatingRevenueReportDao> perioddatalist = null;
		 List<OriginatingRevenueReportDao> totdatalist = null;
		 
		 List<OriginatingRevenueReportDao> periodPassdatalist = null;
		 List<OriginatingRevenueReportDao> totPassdatalist = null;
		 
		 List<NoOfWagonLoadedDao> noOfWagondatalist = null;
		 
		 List<NoOfTonnesLoadedDao> noOfTonnePeriodlist = null;
		 List<NoOfTonnesLoadedDao> noOfTonneCummlist = null;
		 
		 if(curr_fy !=null && curr_fy.length() ==7 &&
				 prev_fy !=null && prev_fy.length() ==7 &&
				 forMonth !=null && forMonth.length() ==2 &&
				 period !=null && period.length()>0) {
			 
			 //Periodic data
			 perioddatalist	= rs.getOriginatingRevenueReport(prev_fy, curr_fy,forMonth,forMonth, period,false,unit);
				
			 //Cummulative data 
			 totdatalist = rs.getOriginatingRevenueReport(prev_fy, curr_fy,"04", forMonth,period,true,unit);
				  
			//Periodic data 
			periodPassdatalist = rs.getOriginatingPassengerReport(prev_fy, curr_fy,forMonth,forMonth, period,false,"Million");
				  
			//Cummulative data 
			totPassdatalist = rs.getOriginatingPassengerReport(prev_fy, curr_fy,"04",forMonth,period,true,"Million");
				  				
			 //Number of wagons loaded 
			noOfWagondatalist = rs.getNumberOfWagonsLoaded(prev_fy, curr_fy,forMonth,period);
						
			 //Periodic data 
			 noOfTonnePeriodlist = rs.getNumberOfTonnesLoaded(prev_fy, curr_fy,forMonth,forMonth, period,false,unit);
						  
			 //Cummulative data 
			 noOfTonneCummlist = rs.getNumberOfTonnesLoaded(prev_fy, curr_fy,"04", forMonth,period,true,unit);
						 					 			 
			 
		 }else {
			 
			 perioddatalist = new ArrayList<>();
			 totdatalist = new ArrayList<>();
			 
			 periodPassdatalist = new ArrayList<>();
			 totPassdatalist = new ArrayList<>();
		 }
		 		    
		Map<String, String> fromToPeriodMap = getFromAndToPeriod(curr_fy, forMonth, period);
		
		//For cumulative Period
		Map<String, String> cum_fromToPeriodMap = getCummulativeFromAndToPeriod(curr_fy, forMonth, period);
		
		model.addAttribute("unit",unit);
		model.addAttribute("periodreport",perioddatalist);
		model.addAttribute("totreport",totdatalist);
		model.addAttribute("periodpassenger",periodPassdatalist);
		model.addAttribute("totalpassenger",totPassdatalist);
		model.addAttribute("noofwagon",noOfWagondatalist);
		model.addAttribute("tonneperiod",noOfTonnePeriodlist);
		model.addAttribute("tonnecumm",noOfTonneCummlist);
		 
		
		model.addAttribute("curr_fy",curr_fy);
		model.addAttribute("prev_fy",prev_fy);
		model.addAttribute("fromPeriod",fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("toPeriod",fromToPeriodMap.get("toPeriod"));
		model.addAttribute("cumFromPeriod",cum_fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("cumToPeriod",cum_fromToPeriodMap.get("toPeriod"));
		
		
		return "fragments/10_days_advance_report_fragment_new :: ORG-REV-REPORT-DIV";				
	}
	
	
	@GetMapping("/Orgg_Othg_Revenue")
	public String Orgg_Othg_Revenue(@RequestParam Map<String, String> params,Model model) {

		 String curr_fy = params.get("financialyear");
		 String forMonth      = params.get("formonth");
		 String period        = params.get("period");
		 String prev_fy = getPreviousFinancialYear(curr_fy);
		 
		 System.out.println("curr_fy = " + curr_fy);
		 System.out.println("forMonth = " + forMonth);
		 System.out.println("period = " + period);
		 System.out.println("prev_fy = " + prev_fy);

		 List<OrggOthgReportDao> periodDataList = null;
		 List<OrggOthgReportDao> totalDataList = null;
		 
		 List<NoOfTonnesLoadedDao> noOfTonnePeriodDataList = null;
		 List<NoOfTonnesLoadedDao> noOfTonneCummDataList = null;

		 
		 if(curr_fy !=null && curr_fy.length() ==7 &&
				 prev_fy !=null && prev_fy.length() ==7 &&
				 forMonth !=null && forMonth.length() ==2 &&
				 period !=null && period.length()>0) {
			 
			 List<OrggOthgReportDao> orggPeriodDataList = rs.getDivWiseOriginatingGoods(prev_fy, curr_fy,forMonth,forMonth, period,false,"Crore");
			 List<OrggOthgReportDao> othgPeriodDataList = rs.getZoneWiseOtherGoods(prev_fy, curr_fy,forMonth,forMonth, period,false,"Crore");
			 
			 OrggOthgReportDao orggTot = orggPeriodDataList.stream().filter(obj -> obj.getDivCode().equals("ORIGINATING GOODS")).findFirst().orElse(null);
			 OrggOthgReportDao othgTot = othgPeriodDataList.stream().filter(obj -> obj.getDivCode().equals("OTHER GOODS")).findFirst().orElse(null);
			 OrggOthgReportDao goodsPeriodTot = null;
			 if(orggTot != null && othgTot != null) {
				 
				 BigDecimal totBudgetAmt= orggTot.getBudgetAmt().add(othgTot.getBudgetAmt());
				 BigDecimal totPrevfyamt = orggTot.getPrevFyAmt().add(othgTot.getPrevFyAmt());
				 BigDecimal totCurrfyamt = orggTot.getCurrFyAmt().add(othgTot.getCurrFyAmt());

				 goodsPeriodTot = new OrggOthgReportDao("TOTAL GOODS", totBudgetAmt, totPrevfyamt, totCurrfyamt);
			 }
			 
			 periodDataList = new ArrayList<>(orggPeriodDataList);
			 periodDataList.addAll(othgPeriodDataList);
			 if(goodsPeriodTot != null)
				 periodDataList.add(goodsPeriodTot);
			 
			 
			 
			 
			 List<OrggOthgReportDao> orggCumDataList = rs.getDivWiseOriginatingGoods(prev_fy, curr_fy,"04",forMonth, period,true,"Crore");
			 List<OrggOthgReportDao> othgCumDataList = rs.getZoneWiseOtherGoods(prev_fy, curr_fy,"04",forMonth, period,true,"Crore");
			 
			 OrggOthgReportDao orggCumTot = orggCumDataList.stream().filter(obj -> obj.getDivCode().equals("ORIGINATING GOODS")).findFirst().orElse(null);
			 OrggOthgReportDao othgCumTot = othgCumDataList.stream().filter(obj -> obj.getDivCode().equals("OTHER GOODS")).findFirst().orElse(null);

			 OrggOthgReportDao goodsCumTot = null;
			 if(orggCumTot != null && othgCumTot != null) {
				 
				 BigDecimal totBudgetAmt= orggCumTot.getBudgetAmt().add(othgCumTot.getBudgetAmt());
				 BigDecimal totPrevfyamt = orggCumTot.getPrevFyAmt().add(othgCumTot.getPrevFyAmt());
				 BigDecimal totCurrfyamt = orggCumTot.getCurrFyAmt().add(othgCumTot.getCurrFyAmt());

				 goodsCumTot = new OrggOthgReportDao("TOTAL GOODS", totBudgetAmt, totPrevfyamt, totCurrfyamt);
			 }

			 
			 
			 totalDataList = new ArrayList<>(orggCumDataList);
			 totalDataList.addAll(othgCumDataList);
			 if(goodsCumTot != null)			 
				 totalDataList.add(goodsCumTot); 
			 
			 
			noOfTonnePeriodDataList = rs.getDivWiseTonnesLoaded(prev_fy, curr_fy,forMonth,forMonth, period,false,"Million");
			noOfTonneCummDataList = rs.getDivWiseTonnesLoaded(prev_fy, curr_fy,"04",forMonth, period,true,"Million");
			 
		 }else {
			 
			 periodDataList = new ArrayList<>();
			 totalDataList = new ArrayList<>();	
			 noOfTonnePeriodDataList =  new ArrayList<>();
			 noOfTonneCummDataList =  new ArrayList<>();
		 }
		 		    
		Map<String, String> fromToPeriodMap = getFromAndToPeriod(curr_fy, forMonth, period);
		
		//For cumulative Period
		Map<String, String> cum_fromToPeriodMap = getFromAndToPeriod(curr_fy, forMonth, "MON");
				
		model.addAttribute("periodreport",periodDataList);
		model.addAttribute("totreport",totalDataList);
		model.addAttribute("tonnesPeriodData",noOfTonnePeriodDataList);
		model.addAttribute("tonnesCummData",noOfTonneCummDataList);
		
		model.addAttribute("curr_fy",curr_fy);
		model.addAttribute("prev_fy",prev_fy);
		model.addAttribute("fromPeriod",fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("toPeriod",fromToPeriodMap.get("toPeriod"));
		model.addAttribute("cumFromPeriod",cum_fromToPeriodMap.get("fromPeriod"));
		model.addAttribute("cumToPeriod",cum_fromToPeriodMap.get("toPeriod"));
		
		
		return "fragments/report_orgg_othg_fragment_new :: ORGG-OTHG-REV-REPORT-DIV";				
	}
	
	public String getPreviousFinancialYear(String fy) {
	    String[] parts = fy.split("-");
	    if(parts.length ==2) {
	    	
	    	int startYear = Integer.parseInt(parts[0]);
		    int endYear   = Integer.parseInt(parts[1]);
		    return (startYear - 1) + "-" + String.format("%02d", endYear - 1);

	    }
	    
	    return "";

	}
	
	public Map<String, String>  getFromAndToPeriod(String fy,String month, String period) {
		
	    Map<String, String> fromToMap = new HashMap<>();

	    try {
			
	    	String fromPeriod = "";
			String toPeriod = "";		
			String year = "";
		    String[] parts = fy.split("-");
		    int month_int = getMonthInt(month);
		   
		    if(parts.length ==2) 
		    	year = parts[0];
		    
		    if(month_int>12)
		    	year = (Integer.parseInt(year)+1)+"";
		    
		    if(period.equalsIgnoreCase("I")) {
		    	fromPeriod = "01"+"-"+month+"-"+year;
		    	toPeriod = "10"+"-"+month+"-"+year;
		    }else if(period.equalsIgnoreCase("II")) {
		    	fromPeriod = "11"+"-"+month+"-"+year;
		    	toPeriod = "20"+"-"+month+"-"+year;
		    }else if(period.equalsIgnoreCase("III")) {
		    	fromPeriod = "21"+"-"+month+"-"+year;
		    	toPeriod = getLastDayOfMonth(year, month)+"-"+month+"-"+year;
		    }
		    else if(period.equalsIgnoreCase("MON")) {
		    	fromPeriod = "01"+"-"+month+"-"+year;
		    	toPeriod = getLastDayOfMonth(year, month)+"-"+month+"-"+year;
		    }
		    
		    fromToMap.put("fromPeriod", fromPeriod);
		    fromToMap.put("toPeriod", toPeriod);
	    	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error occured at getFromAndToPeriod");
		}
		
	    return fromToMap;

	}
	
	public Map<String, String>  getCummulativeFromAndToPeriod(String fy,String month, String period) {
		
	    Map<String, String> fromToMap = new HashMap<>();

	    try {
			
	    	String fromPeriod = "";
			String toPeriod = "";
			String year = "";
			String toyear = "";
		    String[] parts = fy.split("-");
		    int month_int = getMonthInt(month);
		    
		    if(parts.length ==2) 
		    	year = parts[0];
		    
		    
		    if(month_int>12)
		    	toyear = (Integer.parseInt(year)+1)+"";
		    else
		    	toyear = year;

	    	fromPeriod = "01"+"-"+"04"+"-"+year;

		    if(period.equalsIgnoreCase("I")) {
		    	toPeriod = "10"+"-"+month+"-"+toyear;
		    }else if(period.equalsIgnoreCase("II")) {	    	
		    	toPeriod = "20"+"-"+month+"-"+toyear;
		    }else if(period.equalsIgnoreCase("III")) {	    	
		    	toPeriod = getLastDayOfMonth(toyear, month)+"-"+month+"-"+toyear;
		    }	   
		    
		    fromToMap.put("fromPeriod", fromPeriod);
		    fromToMap.put("toPeriod", toPeriod);
	    	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error occures at getCummulativeFromAndToPeriod");
		}
		
		
	    return fromToMap;

	}
	
	public String getLastDayOfMonth(String year, String month) {
		try {
			
			int y = Integer.parseInt(year);
	        int m = Integer.parseInt(month);
	        int lentghOfMonth = YearMonth.of(y, m).lengthOfMonth(); 
	        String lastDate = Integer.toString(lentghOfMonth);
	        return lastDate;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
        
    }
	
	public int getMonthInt(String month) {
		try {
			
			if(month.equalsIgnoreCase("01"))
				return 13;
			else if (month.equalsIgnoreCase("02"))
				return 14;
			else if (month.equalsIgnoreCase("03"))
				return 15;
			else
				return Integer.parseInt(month);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
        
    }
	
}
