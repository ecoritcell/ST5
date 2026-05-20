package com.itcbbs.st5.services;

import java.util.List;

import com.itcbbs.st5.dao.NoOfTonnesLoadedDao;
import com.itcbbs.st5.dao.NoOfTonnesLoadedProjection;
import com.itcbbs.st5.dao.NoOfWagonLoadedDao;
import com.itcbbs.st5.dao.OrggOthgReportDao;
import com.itcbbs.st5.dao.OriginatingRevenueReportDao;
import com.itcbbs.st5.dao.OtherCoachingReportDao;
import com.itcbbs.st5.dao.PassengerReportDao;

public interface ReportService {

	
	//10 DAYS ADVANCE REPORT METHODS
	public List<OriginatingRevenueReportDao> getOriginatingRevenueReport(String prev_fy,String curr_fy, String frommonth, String tomonth, String period,boolean is_cumm, String unit);
	
	public List<OriginatingRevenueReportDao> getOriginatingPassengerReport(String prev_fy,String curr_fy, String frommonth, String tomonth, String period,boolean is_cumm, String unit);
	
	public List<NoOfWagonLoadedDao> getNumberOfWagonsLoaded(String prev_fy,String curr_fy, String month, String period);
	
	public List<NoOfTonnesLoadedDao> getNumberOfTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);


	//DIVISION WISE ORIGINATING NO & REVENUE
	public List<PassengerReportDao> getOriginating_Rev_No_Div_wise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);	
	
	public List<OtherCoachingReportDao> getOtherCoachingRevDivWise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);	
	
	public List<NoOfTonnesLoadedDao> getDivWiseTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);
	
	
	
	public List<OrggOthgReportDao>  getDivWiseOriginatingGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);
	
	public List<OrggOthgReportDao>  getZoneWiseOtherGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm,String unit);
	
	
}
