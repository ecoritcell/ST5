package com.itcbbs.st5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcbbs.st5.dao.User;
import com.itcbbs.st5.services.UserServiceImp;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImp us;
	
	@PostMapping("/validateUser")
	public Map<String, Object>  validateUser(@RequestBody User udao,HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		  List<User> userlist = us.validateUser(udao);
		  if (userlist.isEmpty()) {
			  response.put("status", "error");
		      response.put("message", "Invalid username or password");
		  } else {		  
			  
			  session.setAttribute("loggedInUser", userlist.get(0));
			  response.put("status", "success");
			  response.put("user", userlist.get(0).getUsername());
			  
		  }		
		return response;
	}
}
