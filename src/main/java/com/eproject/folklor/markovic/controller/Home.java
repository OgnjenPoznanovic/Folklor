package com.eproject.folklor.markovic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.service.ClanService;

import entity_dto.KorisnikDTO;

@Controller
@RequestMapping("/home")
public class Home {
	
	private ClanService clanService;
	
	public Home(ClanService theClanService) {
		
		clanService = theClanService;
	}

	@GetMapping("")
    public String showHome(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		System.out.println(korisnicko_ime);
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		String ime = theClan.getIme();
		String prezime = theClan.getPrezime();
			
		theModel.addAttribute("ime", ime);
		theModel.addAttribute("prezime", prezime);
		
        return "home";
    }
	
	@GetMapping("/clan")
    public void showUser(@ModelAttribute("user") KorisnikDTO theKorisnikDTO, Model theModel) {
		
		Clan theCLan = clanService.findByUsername(theKorisnikDTO.getKorisnicko_ime());
		String korisnik = theCLan.getIme();
		
		
		theModel.addAttribute("korisnik", korisnik);
		
    }

	
	
	

	
	
}
