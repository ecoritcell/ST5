package com.itcbbs.st5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcbbs.st5.dao.SignatoryDetailDao;
import com.itcbbs.st5.repository.SignatoryRepo;

@Service
public class SignatoryServiceImp implements SignatoryService{

	@Autowired
	SignatoryRepo sr;
	
	public	List<SignatoryDetailDao> getSignatoryDetail(){
	
		List<SignatoryDetailDao> dataList = null;
		try {
			
			dataList = sr.findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dataList;
	}
	
	public SignatoryDetailDao updateSignatoryDetail(SignatoryDetailDao obj) {
		
		SignatoryDetailDao updatedObj = null;
		try {
			if(obj.getRecordid() > 0) {
				updatedObj = sr.save(obj);
			}else {
				System.out.println("Invalid object");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return updatedObj;
	}
}
