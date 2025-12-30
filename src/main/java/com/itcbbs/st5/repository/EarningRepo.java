package com.itcbbs.st5.repository;

import java.util.List;
import java.util.Optional;
import com.itcbbs.st5.enums.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.itcbbs.st5.dao.StationEarningHeader;

public interface EarningRepo extends JpaRepository<StationEarningHeader, Long>{


	@Override
	public List<StationEarningHeader> findAll();
	
	Optional<StationEarningHeader>  findByRecordid(Long recordid);
	
	@Procedure(procedureName = "getEarningHeader")
	StationEarningHeader getEarningHeader(String financialYear, int entryMonth, 
			String period, String entryType, String systemType, 
			String headOfAccount, String division, String stncode, String valtype);
	
}
