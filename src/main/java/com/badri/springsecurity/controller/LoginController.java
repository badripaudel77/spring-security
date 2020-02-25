package com.badri.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showLoginPage")
	public String showMyLoginPage() {
		
		return "custom-login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		
		return "access-denied";
	}
	
}
