package com.itcbbs.st5.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itcbbs.st5.dao.StationEarningHeader;

@Controller
public class St5Controller {

	@RequestMapping("/")
	public String index(Model model) {
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */
		return "dashboard";
	}

	@GetMapping("/annual_budget")
	public String annual_budget(Model model) {
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */

		List<String> names = Arrays.asList("PASSENGER", "OTHER COACHING", "ORIGINATING GOODS", "OTHER GOODS", "SUNDRY",
				"TOTAL EARNING", "PASSENGER Units");
		model.addAttribute("names", names);

		return "st5_annual_budget";
	}

	/*
	 * @GetMapping("/earning_detail") public String earning_detail(Model model) {
	 * model.addAttribute("msg", "Welcome to Spring Boot with JSP!");
	 * 
	 * 
	 * return "earning_detail"; }
	 */

	/* @GetMapping("/originating_period") */
	
}
