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
import com.eproject.folklor.markovic.entity.Clanarina;
import com.eproject.folklor.markovic.entity.Nastup;
import com.eproject.folklor.markovic.entity.Uloge;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.ClanarinaService;
import com.eproject.folklor.markovic.service.NastupService;
import com.eproject.folklor.markovic.service.UlogeService;

import entity_dto.SifraDTO;



@Controller
@RequestMapping("/clanovi")
public class ClanController {
	
	private ClanService clanService;
	private UlogeService ulogeService;
	private NastupService nastupService;
	private ClanarinaService clanarinaService;
	
	public ClanController(ClanService theClanServicee, UlogeService theUlogeService, NastupService theNastupService, ClanarinaService theClanarinaService) {
		clanService = theClanServicee;
		ulogeService = theUlogeService;
		nastupService = theNastupService;
		clanarinaService = theClanarinaService;
	}
	
	@GetMapping("/home")
	public String backToHome(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		String ime = theClan.getIme();
		String prezime = theClan.getPrezime();
			
		theModel.addAttribute("ime", ime);
		theModel.addAttribute("prezime", prezime);
		
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
		
		List<Clan> theClanovi = clanService.findAllActive();
		
		theModel.addAttribute("clanovi", theClanovi);
		theModel.addAttribute("nastup", true);
		
		return "clan.html";
	}
	
	@GetMapping("/showUpdateForm")
	public String update(@RequestParam("clan_id") int theId, Model theModel) {
		
		Clan theClan = clanService.findById(theId);
		
		theModel.addAttribute("clan", theClan);
		
		return "clanUpdateForm.html";
		
	}
	
	@GetMapping("updateFromProfile")
	public String updateFromProfile(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		theModel.addAttribute("clan", theClan);
		
		return "clanUpdateForm.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("clan") Clan theClan) {	
		
		Clan tempClan = clanService.findById(theClan.getClan_id());
		String oldUsername = tempClan.getUsername();
		String newUsername = theClan.getUsername();
		theClan.setPassword(tempClan.getPassword());
		
		
		
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
			theClan.setEnabled(false);
			clanService.update(theClan);
			//clanService.deleteById(theId);
		}else {
			ulogeService.delete(theUloga);
			theClan.setEnabled(false);
			clanService.update(theClan);
			//clanService.deleteById(theId);
		}
		
		
		return "redirect:/clanovi/list";
	}
	
	@GetMapping("/profil")
	public String profil(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		String ime = theClan.getIme();
		String prezime = theClan.getPrezime();
		
		List<Nastup> nastupi = nastupService.finaLast3(theClan.getClan_id());
		List<Clanarina> clanarine = clanarinaService.finaLast3(theClan.getClan_id());
		
		theModel.addAttribute("clanarine", clanarine);
		theModel.addAttribute("nastupi", nastupi);
		theModel.addAttribute("ime", ime);
		theModel.addAttribute("prezime", prezime);
		
		
		return "profil.html";
	}
	
	@GetMapping("/sifra")
	public String sifra(Model theModel) {
		
		SifraDTO thePassword = new SifraDTO();
		theModel.addAttribute("sifraDTO", thePassword);
		
		
		return "sifra.html";
	}
	
	@PostMapping("/promena_sifra")
	public String promena_sifra(@ModelAttribute("sifraDTO") SifraDTO thePassword, Model theModel) {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		
		String oldP = thePassword.getOldPassword();
		String newP = thePassword.getNewPassword();
		String newP1 = thePassword.getNewPassword1();
		
		int temp = clanService.changePassword(oldP, newP, newP1, theClan);
		
		
		if(temp == 1) {
			theModel.addAttribute("promena", true);
		}else {
			theModel.addAttribute("promena", false);
		}
		
		
		return "redirect:/clanovi/profil";
	}
	
}
