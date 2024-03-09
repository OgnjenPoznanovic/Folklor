package com.eproject.folklor.markovic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Ansambl;

import com.eproject.folklor.markovic.service.AnsamblService;

@Controller
@RequestMapping("/ansambl")
public class AnsamblController {

	private AnsamblService ansamblService;
	
	public AnsamblController(AnsamblService theAnsamblService) {
		
		ansamblService = theAnsamblService;
	}
	
	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
	}
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		List<Ansambl> theAnsambli = ansamblService.findAll();
		
		theModel.addAttribute("ansambli", theAnsambli);
		
		return "ansambl.html";
	}
	
	@GetMapping("showForm")
	public String showForm(Model theModel) {
		
		Ansambl theAnsambl = new Ansambl();
		
		theModel.addAttribute("ansambl", theAnsambl);
		
		return "ansamblForm";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showForm(@RequestParam("ansambl_id")int theId, Model theModel) {
		
		Ansambl theAnsambl = ansamblService.findById(theId);
		
		
		theModel.addAttribute("ansambl", theAnsambl);
		
		return "ansamblForm.html";
	}
	
	
	@PostMapping("/save")
	public String saveAnsambl(@ModelAttribute("ansambl") Ansambl theAnsambl) {
		
		ansamblService.saveAnsambl(theAnsambl);
		
		return "redirect:/ansambl/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("ansambl_id") int theId) {
		
		ansamblService.deleteById(theId);
		
		return "redirect:/ansambl/list";
	}
}
