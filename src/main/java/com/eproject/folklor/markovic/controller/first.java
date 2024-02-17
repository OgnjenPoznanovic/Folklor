package com.eproject.folklor.markovic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class first{
		
	@GetMapping("/hi")
	public String hi() {
		return "HIII!";
	}
		
} 	