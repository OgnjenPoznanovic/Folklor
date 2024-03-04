package com.eproject.folklor.markovic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.service.ClanService;



@Controller
@RequestMapping("/clanovi")
public class ClanController {
	
	private ClanService clanService;
	
	public ClanController(ClanService theClanServicee) {
		clanService = theClanServicee;
	}

	
	@PostMapping("/save")
	public String saveClana(@ModelAttribute("clan") Clan theClan) {
		
		clanService.save(theClan);
		
		return "home.html";
	}

	
	@GetMapping("/registration")
    public String showRegistration(Model theModel) {
		
		
		Clan theClan = new Clan();
		
		theModel.addAttribute("clan", theClan);
		
        return "clanRegistration.html";
    }
	

	
	
}
