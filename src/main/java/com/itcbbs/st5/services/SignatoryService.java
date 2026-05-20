package com.itcbbs.st5.services;

import java.util.List;

import com.itcbbs.st5.dao.SignatoryDetailDao;

public interface SignatoryService {

	public List<SignatoryDetailDao> getSignatoryDetail();
	
	public SignatoryDetailDao updateSignatoryDetail(SignatoryDetailDao obj);
	
}
