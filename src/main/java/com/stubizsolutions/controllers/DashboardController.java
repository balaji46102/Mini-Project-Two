package com.stubizsolutions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stubizsolutions.services.DashboardService;

@Controller
public class DashboardController 
{
	@Autowired
	DashboardService dashboardService;
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model) 
	{
		String quoteTxt = dashboardService.getQuote();
		
		model.addAttribute("quote", quoteTxt);
		
		return "dashboard";
	}
	

}
