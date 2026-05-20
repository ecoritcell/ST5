package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcbbs.st5.dao.StationEarningDetail;

public interface EarningDetailRepo extends JpaRepository<StationEarningDetail, Long>{
	
	@Override
	public List<StationEarningDetail> findAll();
}
