package com.eproject.folklor.markovic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Uloge;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.UlogeService;



@Controller
@RequestMapping("/clanovi")
public class ClanController {
	
	private ClanService clanService;
	private UlogeService ulogeService;
	
	public ClanController(ClanService theClanServicee, UlogeService theUlogeService) {
		clanService = theClanServicee;
		ulogeService = theUlogeService;
	}
	
	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
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
	
	@GetMapping("/list")
	public String showAll(Model theModel) {
		
		List<Clan> theClanovi = clanService.findAll();
		
		theModel.addAttribute("clanovi", theClanovi);
		
		return "clan.html";
	}
	
	@GetMapping("/showUpdateForm")
	public String update(@RequestParam("clan_id") int theId, Model theModel) {
		
		Clan theClan = clanService.findById(theId);
		
		theModel.addAttribute("clan", theClan);
		
		return "clanUpdateForm.html";
		
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("clan") Clan theClan) {	
		
		Clan tempClan = clanService.findById(theClan.getClan_id());
		String oldUsername = tempClan.getUsername();
		String newUsername = theClan.getUsername();
		theClan.setPassword(tempClan.getPassword());
		
		System.out.println(theClan.getPassword());
		
		if(oldUsername.equals(newUsername)) {	
			
			clanService.update(theClan);
			
		}else {
			Uloge oldUloga = ulogeService.findByUsername(oldUsername);
			Uloge newUloga = new Uloge();
			
			newUloga.setUsername(newUsername);
			newUloga.setUloga(oldUloga.getUloga());
			
			ulogeService.delete(oldUloga);
			
			clanService.update(theClan);
			ulogeService.save(newUloga);
			
			
		}
		
		
		
		return "home.html";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("clan_id") int theId) {
		
		
		
		Clan theClan = clanService.findById(theId);
		String username = theClan.getUsername();
		
		Uloge theUloga = ulogeService.findByUsername(username);
		
		
		if(theUloga == null) {
			clanService.deleteById(theId);
		}else {
			ulogeService.delete(theUloga);
			clanService.deleteById(theId);
		}
		
		
		return "redirect:/clanovi/list";
	}
	

	
	
}
