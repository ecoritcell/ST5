package com.itcbbs.st5.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import com.itcbbs.st5.dao.DivisionMaster;
import com.itcbbs.st5.dao.NoOfTonnesLoadedProjection;
import com.itcbbs.st5.dao.NoOfWagonLoadedProjection;
import com.itcbbs.st5.dao.OriginatingRevenueReportProjection;
import com.itcbbs.st5.dao.OtherCoachingReportProjection;
import com.itcbbs.st5.dao.PassengerReportDao;
import com.itcbbs.st5.dao.PassengerReportProjection;
import com.itcbbs.st5.dao.ReportProjection;

public interface ReportsRepo extends JpaRepository<DivisionMaster, Integer>{
	
	@Override
	public List<DivisionMaster> findAll();	
	

	//10 DAYS ADVANCE REPORT PROCEDURES
	@Procedure(procedureName = "report_originatingRevenue_New")
	public List<OriginatingRevenueReportProjection> getOriginatingRevenueReport(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);

	
	@Procedure(procedureName = "report_originatingPassengers_New")
	public List<OriginatingRevenueReportProjection> getOriginatingPassengerReport(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);
	
	@Procedure(procedureName = "report_NoOfWagonLoaded")
	public List<NoOfWagonLoadedProjection> getNumberOfWagonsLoaded(String prev_fy,String curr_fy, String month, String period);
	
	@Procedure(procedureName = "report_NoOfTonneLoaded") 
	public List<NoOfTonnesLoadedProjection> getNumberOfTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);
	
	
	
	//DIVISION WISE ORIGINATING NO & REVENUE
	@Procedure(procedureName = "report_Originating_No_Rev_Div_Wise")
	public List<PassengerReportProjection>  getOriginating_Rev_No_Div_wise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);
	
	@Procedure(procedureName = "report_othc_div_wise_new")
	public List<OtherCoachingReportProjection>  getOtherCoachingRevDivWise(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);

	@Procedure(procedureName = "report_DivWiseTonneLoaded") 
	public List<NoOfTonnesLoadedProjection> getDivWiseTonnesLoaded(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);


	
	@Procedure(procedureName = "report_divWise_orgg")
	public List<ReportProjection>  getDivWiseOriginatingGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);
	
	@Procedure(procedureName = "report_zoneWise_othg")
	public List<ReportProjection>  getZoneWiseOtherGoods(String prev_fy,String curr_fy, String from_month, String to_month, String period, boolean is_cumm);
	
}
