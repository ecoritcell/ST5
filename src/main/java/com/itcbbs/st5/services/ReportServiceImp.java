package com.itcbbs.st5.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itcbbs.st5.dao.NoOfTonnesLoadedDao;
import com.itcbbs.st5.dao.NoOfTonnesLoadedProjection;
import com.itcbbs.st5.dao.NoOfWagonLoadedDao;
import com.itcbbs.st5.dao.NoOfWagonLoadedProjection;
import com.itcbbs.st5.dao.OrggOthgReportDao;
import com.itcbbs.st5.dao.OriginatingRevenueReportDao;
import com.itcbbs.st5.dao.OriginatingRevenueReportProjection;
import com.itcbbs.st5.dao.OtherCoachingReportDao;
import com.itcbbs.st5.dao.OtherCoachingReportProjection;
import com.itcbbs.st5.dao.PassengerReportDao;
import com.itcbbs.st5.dao.PassengerReportProjection;
import com.itcbbs.st5.dao.ReportProjection;
import com.itcbbs.st5.repository.ReportsRepo;
import com.itcbbs.st5.util.AppUtil;
@Service
public class ReportServiceImp implements ReportService{

	@Autowired
	ReportsRepo rr;
	
	@Transactional
	public List<PassengerReportDao> getOriginating_Rev_No_Div_wise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
	
		try {
			
			List<PassengerReportDao> totalList = new ArrayList<>();
			
			List<PassengerReportProjection> report = rr.getOriginating_Rev_No_Div_wise(prev_fy, curr_fy, from_month,to_month, period,is_cumm);
			List<PassengerReportDao> rows =  report.stream()
		            .map(p -> new PassengerReportDao(
		                    p.getDivcode(),
		                    p.getSystemtype(),
		                    convertRupeesToUnit(p.getCurrfycount(), unit) ,
		                    convertRupeesToUnit(p.getCurrfyamt(), unit) ,
		                    convertRupeesToUnit(p.getPrevfycount(), unit) ,
		                    convertRupeesToUnit(p.getPrevfyamt(), unit) ,
		                    convertBudgetUnit_ToUnit(p.getBudunit(), unit),
		                    convertBudgetAmt_ToUnit(p.getBudamt(),unit)
		            ))
		            .toList();
			
			
						
			
			//Calculate KUR total
			List<PassengerReportDao> kurdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("KUR")).collect(Collectors.toList());		
			totalList.addAll(kurdata);
			totalList.add(AppUtil.sumRows(kurdata, "KUR-TOT", "Total"));
			
			//Calculate SBP total
			List<PassengerReportDao> sbpdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("SBP")).collect(Collectors.toList());		
			totalList.addAll(sbpdata);
			totalList.add(AppUtil.sumRows(sbpdata, "SBP-TOT", "Total"));
			
			//Calculate WAT total
			List<PassengerReportDao> watdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("WAT")).collect(Collectors.toList());		
			totalList.addAll(watdata);
			totalList.add(AppUtil.sumRows(watdata, "WAT-TOT", "Total"));
			
			
			//Calculate PRS total
			List<PassengerReportDao> prsdata = rows.stream().filter(obj-> obj.getSytemType().equalsIgnoreCase("P")).collect(Collectors.toList());		
			totalList.add(AppUtil.sumRows(prsdata, "PRS-TOT", "PRS"));
			
			//Calculate NON-PRS total
			List<PassengerReportDao> nonprsdata = rows.stream().filter(obj-> obj.getSytemType().equalsIgnoreCase("M")).collect(Collectors.toList());		
			totalList.add(AppUtil.sumRows(nonprsdata, "NONPRS-TOT", "Non PRS"));

			//Calculate Grand total
			totalList.add(AppUtil.sumRows(rows, "TOTAL", "Total"));
			
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	public List<OtherCoachingReportDao> getOtherCoachingRevDivWise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
		
		try {
			
			List<OtherCoachingReportDao> totalList = new ArrayList<>();
			
			List<OtherCoachingReportProjection> report = rr.getOtherCoachingRevDivWise(prev_fy, curr_fy, from_month,to_month, period,is_cumm);
			List<OtherCoachingReportDao> rows =  report.stream()
		            .map(p -> new OtherCoachingReportDao(
		                    p.getDivcode(),
		                    p.getSystemtype(),
		                    convertRupeesToUnit(p.getCurrfyamt(), unit),
		                    convertRupeesToUnit(p.getPrevfyamt(), unit),
		                    convertBudgetAmt_ToUnit(p.getBudamt(), unit) 
		            ))
		            .toList();
			
			
			
			//Calculate KUR total
			List<OtherCoachingReportDao> kurdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("KUR")).collect(Collectors.toList());		
			totalList.addAll(kurdata);
			totalList.add(AppUtil.otherCoachingSumRows(kurdata, "KUR-TOT", "Total"));
			
			//Calculate SBP total
			List<OtherCoachingReportDao> sbpdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("SBP")).collect(Collectors.toList());		
			totalList.addAll(sbpdata);
			totalList.add(AppUtil.otherCoachingSumRows(sbpdata, "SBP-TOT", "Total"));
			
			//Calculate WAT total
			List<OtherCoachingReportDao> watdata = rows.stream().filter(obj-> obj.getDivCode().equalsIgnoreCase("WAT")).collect(Collectors.toList());		
			totalList.addAll(watdata);
			totalList.add(AppUtil.otherCoachingSumRows(watdata, "WAT-TOT", "Total"));
			
			
			//Calculate PRS total
			List<OtherCoachingReportDao> prsdata = rows.stream().filter(obj-> obj.getSytemType().equalsIgnoreCase("P")).collect(Collectors.toList());		
			totalList.add(AppUtil.otherCoachingSumRows(prsdata, "PRS-TOT", "PRS"));
			
			//Calculate NON-PRS total
			List<OtherCoachingReportDao> nonprsdata = rows.stream().filter(obj-> obj.getSytemType().equalsIgnoreCase("M")).collect(Collectors.toList());		
			totalList.add(AppUtil.otherCoachingSumRows(nonprsdata, "NONPRS-TOT", "Non PRS"));

			//Calculate Grand total
			totalList.add(AppUtil.otherCoachingSumRows(rows, "TOTAL", "Total"));
			
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Transactional
	public List<OriginatingRevenueReportDao> getOriginatingRevenueReport(String prev_fy,String curr_fy, String frommonth, String tomonth, String period, boolean is_cumm, String unit){
	
		try {
			
			/* int numberOfDays = getNumberOfDaysForMonth(curr_fy, forMonth); */
			
			List<OriginatingRevenueReportDao> totalList = new ArrayList<>();
			
			List<OriginatingRevenueReportProjection> report = rr.getOriginatingRevenueReport(prev_fy, curr_fy, frommonth,tomonth, period, is_cumm);
			List<OriginatingRevenueReportDao> rows =  report.stream()
		            .map(p -> new OriginatingRevenueReportDao(
		                    p.getRevenuehead(),
		                    p.getSystemtype(),
		                    convertBudgetAmt_ToUnit(p.getBudget(),unit),
		                    convertRupeesToUnit(p.getPfytotal(),unit),
		                    convertRupeesToUnit(p.getCfytotal(), unit)

		            ))
		            .toList();
			
			BigDecimal totBudgetAmt= BigDecimal.ZERO;
			BigDecimal totCurrfyamt = BigDecimal.ZERO;
			BigDecimal totPrevfyamt = BigDecimal.ZERO;
			
			//Calculate total for Passenger
			List<OriginatingRevenueReportDao> pnsub = rows.stream().filter(obj-> obj.getRevenuehead().equalsIgnoreCase("PNSUB")).collect(Collectors.toList());			
			for (OriginatingRevenueReportDao row : pnsub) {
				
				totalList.add(row);
				totBudgetAmt = totBudgetAmt.add(row.getBudget());
				totCurrfyamt = totCurrfyamt.add(row.getCfytotal());
				totPrevfyamt = totPrevfyamt.add(row.getPfytotal());
			}
			
			OriginatingRevenueReportDao total_pnsub = new OriginatingRevenueReportDao("TOT-PNSUB","", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			totalList.add(total_pnsub);
			
			
			//Calculate total for other coaching
			totBudgetAmt= BigDecimal.ZERO;
			totCurrfyamt = BigDecimal.ZERO;
			totPrevfyamt = BigDecimal.ZERO;
			List<OriginatingRevenueReportDao> othc = rows.stream().filter(obj-> obj.getRevenuehead().equalsIgnoreCase("OTHC")).collect(Collectors.toList());			
			for (OriginatingRevenueReportDao row : othc) {
				
				totalList.add(row);
				totBudgetAmt = totBudgetAmt.add(row.getBudget());
				totCurrfyamt = totCurrfyamt.add(row.getCfytotal());
				totPrevfyamt = totPrevfyamt.add(row.getPfytotal());
			}
			
			OriginatingRevenueReportDao total_othc = new OriginatingRevenueReportDao("TOT-OTHC","", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			totalList.add(total_othc);
			
			
			//Calculate total for Goods
			totBudgetAmt= BigDecimal.ZERO;
			totCurrfyamt = BigDecimal.ZERO;
			totPrevfyamt = BigDecimal.ZERO;
			List<OriginatingRevenueReportDao> goods = rows.stream().filter(obj-> obj.getRevenuehead().equalsIgnoreCase("ORGG") ||  obj.getRevenuehead().equalsIgnoreCase("OTHG") ).collect(Collectors.toList());			
			for (OriginatingRevenueReportDao row : goods) {
				
				totalList.add(row);
				totBudgetAmt = totBudgetAmt.add(row.getBudget());
				totCurrfyamt = totCurrfyamt.add(row.getCfytotal());
				totPrevfyamt = totPrevfyamt.add(row.getPfytotal());
			}
			
			OriginatingRevenueReportDao total_goods = new OriginatingRevenueReportDao("TOT-GOODS","", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			totalList.add(total_goods);
			
			//Add sundry
			totBudgetAmt= BigDecimal.ZERO;
			totCurrfyamt = BigDecimal.ZERO;
			totPrevfyamt = BigDecimal.ZERO;
			List<OriginatingRevenueReportDao> sun = rows.stream().filter(obj-> obj.getRevenuehead().equalsIgnoreCase("SUN")).collect(Collectors.toList());			
			for (OriginatingRevenueReportDao row : sun) {				
				totalList.add(row);
			}
			
			
			//Calculate grand total
			totBudgetAmt= BigDecimal.ZERO;
			totCurrfyamt = BigDecimal.ZERO;
			totPrevfyamt = BigDecimal.ZERO;
			for (OriginatingRevenueReportDao row : rows) {
				
				totBudgetAmt = totBudgetAmt.add(row.getBudget());
				totCurrfyamt = totCurrfyamt.add(row.getCfytotal());
				totPrevfyamt = totPrevfyamt.add(row.getPfytotal());
			}
			
			OriginatingRevenueReportDao grand_tot = new OriginatingRevenueReportDao("GRAND-TOT","", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			totalList.add(grand_tot);			
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@Transactional
	public List<OriginatingRevenueReportDao> getOriginatingPassengerReport(String prev_fy,String curr_fy, String frommonth, String tomonth, String period,boolean is_cumm, String unit){
	
		try {
			
			List<OriginatingRevenueReportProjection> report = rr.getOriginatingPassengerReport(prev_fy, curr_fy, frommonth,tomonth, period, is_cumm);
			List<OriginatingRevenueReportDao> rows =  report.stream()
		            .map(p -> new OriginatingRevenueReportDao(
		                    p.getRevenuehead(),
		                    p.getSystemtype(),
		                    convertBudgetUnit_ToUnit(p.getBudget(),unit),
		                    convertRupeesToUnit(p.getPfytotal(),unit),
		                    convertRupeesToUnit(p.getCfytotal(),unit)
		            ))
		            .toList();
			
			
			
			BigDecimal totCurrfyamt = BigDecimal.ZERO;
			BigDecimal totPrevfyamt = BigDecimal.ZERO;
			BigDecimal totBudgetAmt= BigDecimal.ZERO;
			for (OriginatingRevenueReportDao row : rows) {
				
				totCurrfyamt = totCurrfyamt.add(row.getCfytotal());
				totPrevfyamt = totPrevfyamt.add(row.getPfytotal());
				totBudgetAmt = totBudgetAmt.add(row.getBudget());
			}
			
			OriginatingRevenueReportDao total = new OriginatingRevenueReportDao("TOTAL","", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			
			List<OriginatingRevenueReportDao> totalList = new ArrayList<>(rows);
			totalList.add(total);
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<NoOfWagonLoadedDao> getNumberOfWagonsLoaded(String prev_fy,String curr_fy, String month, String period){
	
		try {
			
			 int numberOfDays = getNumberOfDaysForMonth(curr_fy, month); 			
			
			List<NoOfWagonLoadedProjection> report = rr.getNumberOfWagonsLoaded(prev_fy, curr_fy, month, period);
			List<NoOfWagonLoadedDao> rows =  report.stream()
		            .map(p -> new NoOfWagonLoadedDao(
		                    p.getHeadofaccounts(),
		                    p.getPfytotal(),
		                    p.getCfytotal(),
		                    numberOfDays,
		                    period

		            ))
		            .toList();
						
			
			List<NoOfWagonLoadedDao> totalList = new ArrayList<>(rows);
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<NoOfTonnesLoadedDao> getNumberOfTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
		
		try {
			
			List<NoOfTonnesLoadedProjection> report = rr.getNumberOfTonnesLoaded(prev_fy, curr_fy, from_month,to_month, period, is_cumm);
			List<NoOfTonnesLoadedDao> rows =  report.stream()
		            .map(p -> new NoOfTonnesLoadedDao(
		                    p.getHeadofaccounts(),
		                    p.getBudget(),
		                    convertRupeesToUnit(p.getPfytotal(),"Million"),
		                    convertRupeesToUnit(p.getCfytotal(),"Million")
		            ))
		            .toList();
			
			List<NoOfTonnesLoadedDao> totalList = new ArrayList<>(rows);
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Transactional
	public List<NoOfTonnesLoadedDao> getDivWiseTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
		
		try {
			
			List<NoOfTonnesLoadedProjection> report = rr.getDivWiseTonnesLoaded(prev_fy, curr_fy, from_month,to_month, period, is_cumm);
			List<NoOfTonnesLoadedDao> rows =  report.stream()
		            .map(p -> new NoOfTonnesLoadedDao(
		                    p.getHeadofaccounts(),
		                    p.getBudget(),
		                    convertRupeesToUnit(p.getPfytotal(),"Million"),
		                    convertRupeesToUnit(p.getCfytotal(),"Million")
		            ))
		            .toList();
			
			BigDecimal totBudgetTonne= BigDecimal.ZERO;
			BigDecimal totPrevfyTonne = BigDecimal.ZERO;
			BigDecimal totCurrfTonne = BigDecimal.ZERO;
			for (NoOfTonnesLoadedDao row : rows) {
				
				totBudgetTonne = totBudgetTonne.add(row.getBudget());
				totPrevfyTonne = totPrevfyTonne.add(row.getPfytotal());
				totCurrfTonne = totCurrfTonne.add(row.getCfytotal());
			}
			
			NoOfTonnesLoadedDao total = new NoOfTonnesLoadedDao("TOTAL", totBudgetTonne, totPrevfyTonne, totCurrfTonne);		
			
			List<NoOfTonnesLoadedDao> totalList = new ArrayList<>(rows);
			totalList.add(total);
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<OrggOthgReportDao>  getDivWiseOriginatingGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
	
		try {
			
			List<ReportProjection> report = rr.getDivWiseOriginatingGoods(prev_fy, curr_fy, from_month, to_month,period,is_cumm);
			
			List<OrggOthgReportDao> rows =  report.stream()
		            .map(p -> new OrggOthgReportDao(
		                    p.getDivcode(),
		                   convertBudgetAmt_ToUnit(p.getBudamt(), unit),
		                   convertRupeesToUnit(p.getPfyamt(), unit),
		                   convertRupeesToUnit(p.getCfyamt(), unit) 
		            ))
		            .toList();
						
			
			BigDecimal totCurrfyamt = BigDecimal.ZERO;
			BigDecimal totPrevfyamt = BigDecimal.ZERO;
			BigDecimal totBudgetAmt= BigDecimal.ZERO;
			for (OrggOthgReportDao row : rows) {
				
				totCurrfyamt = totCurrfyamt.add(row.getCurrFyAmt());
				totPrevfyamt = totPrevfyamt.add(row.getPrevFyAmt());
				totBudgetAmt = totBudgetAmt.add(row.getBudgetAmt());
			}
			
			OrggOthgReportDao total = new OrggOthgReportDao("ORIGINATING GOODS", totBudgetAmt, totPrevfyamt, totCurrfyamt);		
			
			List<OrggOthgReportDao> totalList = new ArrayList<>(rows);
			totalList.add(total);
			 return totalList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	@Transactional
	public List<OrggOthgReportDao>  getZoneWiseOtherGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit){
		
	
		try {
			
			List<ReportProjection> report = rr.getZoneWiseOtherGoods(prev_fy, curr_fy, from_month, to_month,period,is_cumm);
			
			List<OrggOthgReportDao> rows =  report.stream()
		            .map(p -> new OrggOthgReportDao(
		                    p.getDivcode(),
		                    convertBudgetAmt_ToUnit(p.getBudamt(), unit),
		                    convertRupeesToUnit(p.getPfyamt(), unit),
		                    convertRupeesToUnit(p.getCfyamt(),unit)
		            ))
		            .toList();
						
			 return rows;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public int getNumberOfDaysForMonth(String fy, String month) {
		
		int numberOfDays = 1;
		try {
			
			String year = "";
		    String[] parts = fy.split("-");
		    if(parts.length ==2) 
		    	year = parts[0];
			if(year.length()==4 && month.length() == 2) {
				
				int y = Integer.parseInt(year);
		        int m = Integer.parseInt(month);
		        numberOfDays = YearMonth.of(y, m).lengthOfMonth(); 
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		
		}
        
		return numberOfDays;
    }
	
	public BigDecimal calculateBudgetValue(BigDecimal budVal, int numberOfDays, String period,String unit) {

	    if (budVal == null || budVal.compareTo(BigDecimal.ZERO) == 0) {
	        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
	    }
	    
	    BigDecimal unitWiseBudVal = BigDecimal.ZERO;
	    if(unit.equalsIgnoreCase("Crore"))
	    	unitWiseBudVal = budVal;
	    else if (unit.equalsIgnoreCase("Million"))
	    	unitWiseBudVal = budVal.multiply(BigDecimal.TEN).setScale(2,RoundingMode.DOWN);
	    else if(unit.equalsIgnoreCase("Thousand"))
	    	unitWiseBudVal = budVal.multiply(BigDecimal.valueOf(10000)).setScale(2,RoundingMode.DOWN);
	    else
	    	unitWiseBudVal = budVal;

	    if ("ALL".equalsIgnoreCase(period)) {
	        return unitWiseBudVal.setScale(2, RoundingMode.DOWN);
	    }

	    BigDecimal perDayValue = unitWiseBudVal.divide(
	            BigDecimal.valueOf(numberOfDays),
	            3,
	            RoundingMode.DOWN
	    );

	    if ("I".equalsIgnoreCase(period) || "II".equalsIgnoreCase(period)) {
	        return perDayValue
	                .multiply(BigDecimal.TEN)
	                .setScale(2, RoundingMode.DOWN);
	    }

	    if ("III".equalsIgnoreCase(period)) {
	        return perDayValue
	                .multiply(BigDecimal.valueOf(numberOfDays - 20))
	                .setScale(2, RoundingMode.DOWN);
	    }

	    return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
	}
	
	
	//Passenger revenue and passenger number etered as Ruppes
	public BigDecimal convertRupeesToUnit(BigDecimal valInRupee,String unit) {

		try {
			
			 if (valInRupee == null || valInRupee.compareTo(BigDecimal.ZERO) == 0) {
			        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
			    }
			    
			    BigDecimal unitWiseVal = BigDecimal.ZERO;
			    if(unit.equalsIgnoreCase("Crore"))
			    	unitWiseVal = valInRupee.divide(BigDecimal.valueOf(10000000).setScale(2));
			    else if (unit.equalsIgnoreCase("Million"))
			    	unitWiseVal = valInRupee.divide(BigDecimal.valueOf(1000000).setScale(2));
			    else if(unit.equalsIgnoreCase("Thousand"))
			    	unitWiseVal = valInRupee.divide(BigDecimal.valueOf(1000).setScale(2));
			    else
			    	unitWiseVal = valInRupee;


			    return unitWiseVal;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return BigDecimal.ZERO;
			
		}
	   
	}
	
	//Budget AMT entered as Crore
	public BigDecimal convertBudgetAmt_ToUnit(BigDecimal amtVal,String unit) {

		try {
			
			 if (amtVal == null || amtVal.compareTo(BigDecimal.ZERO) == 0) {
			        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
			    }
			    
			    BigDecimal unitWiseVal = BigDecimal.ZERO;
			    if(unit.equalsIgnoreCase("Crore"))
			    	unitWiseVal = amtVal;
			    else if (unit.equalsIgnoreCase("Million"))
			    	unitWiseVal = amtVal.multiply(BigDecimal.TEN);
			    else if(unit.equalsIgnoreCase("Thousand"))
			    	unitWiseVal = amtVal.multiply(BigDecimal.valueOf(10000));
			    else
			    	unitWiseVal = amtVal;

			    System.out.println("unitWiseVal = "+unitWiseVal +" "+unit );
			    return unitWiseVal.setScale(2,RoundingMode.DOWN);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return BigDecimal.ZERO;
			
		}
	   
	}
	
	//Budget UNIT entered as Millions
	public BigDecimal convertBudgetUnit_ToUnit(BigDecimal unitVal,String unit) {

			try {
				
				 if (unitVal == null || unitVal.compareTo(BigDecimal.ZERO) == 0) {
				        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
				    }
				    
				    BigDecimal unitWiseVal = BigDecimal.ZERO;
				    if(unit.equalsIgnoreCase("Crore"))
				    	unitWiseVal = unitVal.divide(BigDecimal.TEN);
				    else if (unit.equalsIgnoreCase("Million"))
				    	unitWiseVal = unitVal;
				    else if(unit.equalsIgnoreCase("Thousand"))
				    	unitWiseVal = unitVal.multiply(BigDecimal.valueOf(1000));
				    else
				    	unitWiseVal = unitVal;

				    return unitWiseVal.setScale(2,RoundingMode.DOWN);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return BigDecimal.ZERO;
				
			}
		   
		}
	
}
