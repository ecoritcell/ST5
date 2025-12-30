package com.itcbbs.st5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itcbbs.st5.dao.User;
import com.itcbbs.st5.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired 
	private UserRepository ur;

	@Transactional
	public List<User> validateUser(User udao) {		
		System.out.println("Username=" +udao.getUsername()+"Password = " +udao.getPassword());
		List<User> userList = ur.fetchUsersByNameAndPassword(udao.getUsername(),udao.getPassword());
		System.out.println("findByUsernameAndUserpassword - " +userList);
		return userList;
	}

}
