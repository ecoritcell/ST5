package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itcbbs.st5.dao.SignatoryDetailDao;

@Repository
public interface SignatoryRepo extends JpaRepository<SignatoryDetailDao, Integer>{

	@Override
	public List<SignatoryDetailDao> findAll();
	
	
}
