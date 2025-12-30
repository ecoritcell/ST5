package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.itcbbs.st5.dao.StationMaster;

@Repository
public interface StationMasterRepo extends JpaRepository<StationMaster, Integer>{

	@Override
	public List<StationMaster> findAll();
	
	@Procedure(procedureName = "getStationsByDivByPercentage")
	List<String> getStationsByDivByPercentage(String div, String percentage);
}
