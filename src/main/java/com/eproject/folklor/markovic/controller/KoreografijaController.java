package com.eproject.folklor.markovic.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Koreografija;
import com.eproject.folklor.markovic.entity.Nosnja;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.KoreografijaService;

@Controller
@RequestMapping("/koreografija")
public class KoreografijaController {
	
	private KoreografijaService koreografijaService;
	
	private ClanService clanService;
	
	public KoreografijaController(KoreografijaService theKoreografijaService, ClanService theClanService) {
		
		koreografijaService = theKoreografijaService;
		
		clanService =theClanService;
	}
	
	@GetMapping("/home")
	public String backToHome(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		System.out.println(korisnicko_ime);
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		String ime = theClan.getIme();
		String prezime = theClan.getPrezime();
			
		theModel.addAttribute("ime", ime);
		theModel.addAttribute("prezime", prezime);
		
		return "home.html";
	}
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		
		List<Koreografija> theKoreografije = koreografijaService.findAll();
		
		theModel.addAttribute("koreografije", theKoreografije);
		
		return "koreografija.html";
	}
	
	@GetMapping("showNosnje")
	public String showNosnje(@RequestParam("koreografija_id") int theId, Model theModel) {
		
		Koreografija theKoreografija = koreografijaService.findById(theId);
		
		List<Nosnja> nosnjaList = theKoreografija.getNosnje();
		
		theModel.addAttribute("nosnjaList", nosnjaList);
		
		return "koreografijaNosnje.html";
	}
	
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		Koreografija theKoreografija = new Koreografija();
		
		theModel.addAttribute("koreografija", theKoreografija);
		
		return "koreografijaForm.html";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showForm(@RequestParam("koreografija_id")int theId, Model theModel) {
		
		Koreografija theKoreografija = koreografijaService.findById(theId);
		
		
		theModel.addAttribute("koreografija", theKoreografija);
		
		return "koreografijaForm.html";
	}
	
	
	@PostMapping("/save")
	public String saveAnsambl(@ModelAttribute("koreografija") Koreografija theKoreografija) {
			
		
		koreografijaService.save(theKoreografija);
		
		return "redirect:/koreografija/list";
	}
	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("koreografija_id") int theId) {
		
		koreografijaService.deleteById(theId);
		
		return "redirect:/koreografija/list";
	}
}
