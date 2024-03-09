package com.eproject.folklor.markovic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Clanarina;
import com.eproject.folklor.markovic.service.ClanarinaService;

@Controller
@RequestMapping("/clanarina")
public class ClanarinaController {

	private ClanarinaService clanarinaService;
	
	public ClanarinaController(ClanarinaService theClanarinaService) {
		
		clanarinaService = theClanarinaService;
	}
	
	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
	}
	
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		
		
		List<Clanarina> theClanarine = clanarinaService.findAll();
		
		theModel.addAttribute("clanarine", theClanarine);
		
		return "clanarina.html";
	}
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		Clanarina theClanarina = new Clanarina();
		
		theModel.addAttribute("clanarina", theClanarina);
		
		return "clanarinaForm.html";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showForm(@RequestParam("clanarina_id")int theId, Model theModel) {
		
		Clanarina theClanarina = clanarinaService.findById(theId);
		
		
		theModel.addAttribute("clanarina", theClanarina);
		
		return "clanarinaForm.html";
	}
	
	@PostMapping("/save")
	public String saveClanarina(@ModelAttribute("clanarina") Clanarina theClanarina) {
		
		
		System.out.println(theClanarina.getClanarina_id());
		System.out.println(theClanarina.getCena());
		System.out.println(theClanarina.getGodina());
		System.out.println(theClanarina.getMesec());
		clanarinaService.saveClanarina(theClanarina);
		
		return "redirect:/clanarina/list";
		
	}
	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("clanarina_id") int theId) {
		clanarinaService.deleteById(theId);
		
		return "redirect:/clanarina/list";
	}
	
	
	
}
