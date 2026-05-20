package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.itcbbs.st5.dao.StationMaster;
import com.itcbbs.st5.dao.StationProjection;

@Repository
public interface StationMasterRepo extends JpaRepository<StationMaster, Integer>{

	@Override
	public List<StationMaster> findAll();
	
	@Procedure(procedureName = "getStationsWithValueType")
	List<StationProjection> getStationsWithValueType(String fy, String formonth, String period, 
			String entrytype, String system, String hoa, int divid, String stntype);
	
	@Procedure(procedureName = "getStationsByDivByPercentage")
	List<String> getStationsByDivByPercentage(int divid, String stntype);

}
