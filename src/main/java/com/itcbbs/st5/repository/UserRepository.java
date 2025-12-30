package com.itcbbs.st5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.itcbbs.st5.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Override
	public List<User> findAll();	
	
	@Procedure(procedureName = "getUserByUsernameAndPassword")
	List<User> fetchUsersByNameAndPassword(String username, String password);
	    
}
