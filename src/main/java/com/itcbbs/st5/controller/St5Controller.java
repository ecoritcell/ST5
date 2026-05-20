package com.itcbbs.st5.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itcbbs.st5.dao.SignatoryDetailDao;
import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.repository.SignatoryRepo;
import com.itcbbs.st5.services.SignatoryService;

@Controller
public class St5Controller {

	@Autowired
	SignatoryService ss;
	
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
	
	@GetMapping("/signatory_detail")
	public String signatory_detail(Model model) {
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */
		
		System.out.println("signatory_detail called ");
		
		SignatoryDetailDao signatory = null;
		List<SignatoryDetailDao> dataList =  ss.getSignatoryDetail();		
		if(dataList.size()>0)
			signatory = dataList.get(0);
		else
			signatory = new SignatoryDetailDao();
		
		model.addAttribute("signatory", signatory);
		return "signatory_detail";
	}
	
	@PostMapping("/update_signatory_detail")
	public String update_signatory_detail(@ModelAttribute SignatoryDetailDao signatory, Model model,RedirectAttributes ra) {
		
		System.out.println("update_signatory_detail called ");
		System.out.println("Signatory = "+signatory.getRecordid());
		
		System.out.println("update_signatory_detail called"); 
		SignatoryDetailDao upated_Obj = ss.updateSignatoryDetail(signatory);
		if(upated_Obj != null) {
			ra.addFlashAttribute("msg", "Data updated successfully.");
			ra.addFlashAttribute("msgType","success");
		}
		else {
			ra.addFlashAttribute("msg", "Error occurred while updating.");
			ra.addFlashAttribute("msgType","error");
		}
		return "redirect:/signatory_detail";
	}

	/*
	 * @GetMapping("/annual_budget") public String annual_budget(Model model) {
	 * model.addAttribute("msg", "Welcome to Spring Boot with JSP!");
	 * 
	 * List<String> names = Arrays.asList("PASSENGER", "OTHER COACHING",
	 * "ORIGINATING GOODS", "OTHER GOODS", "SUNDRY", "TOTAL EARNING",
	 * "PASSENGER Units"); model.addAttribute("names", names);
	 * 
	 * return "st5_annual_budget"; }
	 */

	/*
	 * @GetMapping("/earning_detail") public String earning_detail(Model model) {
	 * model.addAttribute("msg", "Welcome to Spring Boot with JSP!");
	 * 
	 * 
	 * return "earning_detail"; }
	 */

	/* @GetMapping("/originating_period") */

}
