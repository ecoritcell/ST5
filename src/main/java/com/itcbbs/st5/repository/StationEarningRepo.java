package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.itcbbs.st5.dao.StationEarning;

@Repository
public interface StationEarningRepo extends JpaRepository<StationEarning, Integer>{

	@Override
	public List<StationEarning> findAll();

	@Procedure(procedureName = "getStationEarning")
	public List<StationEarning> getStationEarning(String fy, String formonth, String period, 
			String entrytype,String system,String headofacct,   
			int divisionid,String stntype,String stncode, String valtype);

	
	@Procedure(procedureName = "inserOrUpdateStnEarning")
	public String inserOrUpdateStnEarning(String fy, String formonth, String period, 
			String entrytype,String system,String headofacct,   
			String divcode,String stntype,String stncode, String valtype,
			String receivedon, String remark, String earncat, String earnsubcat, double quant, double amount );
}
