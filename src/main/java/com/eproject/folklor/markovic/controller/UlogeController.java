package com.eproject.folklor.markovic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import com.eproject.folklor.markovic.entity.Uloge;
import com.eproject.folklor.markovic.service.UlogeService;

@Controller
@RequestMapping("/uloge")
public class UlogeController {

	private UlogeService ulogeService;
	
	public UlogeController(UlogeService theUlogeService) {
		
		ulogeService = theUlogeService;
	}
	
	@PostMapping("/save")
	public String saveUloge(@ModelAttribute("uloge") Uloge theUloge) {
		ulogeService.save(theUloge);
		return "Home.html";
	}
	
	@GetMapping("/role")
	public String showSetRole(Model theModel) {
	
		Uloge theUloge = new Uloge();
		
		theModel.addAttribute("uloge", theUloge);
		return "clanRole.html";
	}
	
}
