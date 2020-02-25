package com.badri.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controller class, for the home path
@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
	  
		return "home";
	
	}
	
	//mapping for the leaders
	
	@GetMapping("/leaders")
	public String showLeaders() {
	  
		return "leaders";
	
	}
	
	//mapping for the systems / admins
	
		@GetMapping("/systems")
		public String showSystems() {
		  
			return "systems";
		
		}
}
