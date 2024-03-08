package com.eproject.folklor.markovic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class Home {

	@GetMapping("")
    public String showHome() {

        return "home";
    }

	
	
	

	
	
}
