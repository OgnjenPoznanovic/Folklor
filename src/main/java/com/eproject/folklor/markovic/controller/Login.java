package com.eproject.folklor.markovic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login{

	@GetMapping("/")
	public String showLoginPage() {
		
		return "login";
	}
}
