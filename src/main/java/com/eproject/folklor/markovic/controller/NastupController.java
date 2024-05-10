package com.eproject.folklor.markovic.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import com.eproject.folklor.markovic.entity.Nastup;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.KoreografijaService;
import com.eproject.folklor.markovic.service.NastupService;

import entity_dto.NastupKoreografijeDTO;

@Controller
@RequestMapping("nastupi")
public class NastupController {

	private NastupService nastupService;
	private KoreografijaService koreografijaService;
	private ClanService clanService;
	
	public NastupController(NastupService theNastupService, KoreografijaService theKoreografijaService, ClanService theClanService) {
		
		nastupService = theNastupService;
		
		koreografijaService= theKoreografijaService;
		
		clanService = theClanService;
		
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
	
	@PostMapping("/save")
	public String saveNastup(@ModelAttribute("nastup") Nastup theNastup) {
		
		nastupService.save(theNastup);
		
		return "redirect:/nastupi/list";
	}
	
	@GetMapping("/list")
	public String showAll(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> uloga = auth.getAuthorities();
		String korisnicko_ime = auth.getName();
		
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		List<String> uloge = new ArrayList<>();
		String theUloga = "ROLE_ADMIN";
		List<Nastup> allTheNastupi = nastupService.findAll();
		
		for (GrantedAuthority authority : uloga) {
				uloge.add(authority.getAuthority());
		}
		

		if(theUloga.equals(uloge.get(0))){				
			
			theModel.addAttribute("nastupi", allTheNastupi);
			theModel.addAttribute("pregled", true);
			
		}else{
			
			//!naci sve koreografije za koje igrac nije prijavljen!
				//svi nastupi (1)
				
				//naci sve za koje je prijavlje (1)
				List<Nastup> prijavljeni = theClan.getNastupi();
				
				//uzmi razliku
				List<Nastup> izbor = nastupService.nastupInOneList(allTheNastupi, prijavljeni);
			
				theModel.addAttribute("nastupi", izbor);
				theModel.addAttribute("pregled", true);
			
		}
		
		return "nastup.html";
	}
	
	@GetMapping("/sviNastupi")
	public String sviNastupi(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();		
		Clan theClan = clanService.findByUsername(korisnicko_ime);		
		List<Nastup> sviNastupi = theClan.getNastupi();
		
		theModel.addAttribute("nastupi", sviNastupi);
		theModel.addAttribute("pregled", false);
		theModel.addAttribute("profil", true);
		
		return "nastup.html";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("nastup_id") int theId) {
		
		nastupService.deleteById(theId);
		
		return "redirect:/nastupi/list";
	}
	
	
	@GetMapping("/showForm")
    public String showForm(Model theModel) {
		
		
		Nastup theNastup = new Nastup();
		
		theModel.addAttribute("nastup", theNastup);
		
        return "nastupForm.html";
    }
	
	@GetMapping("/showFormForUpdate")
    public String showForm(@RequestParam("nastup_id") int theId, Model theModel) {
		
		
		Nastup theNastup = nastupService.findById(theId);
		
		theModel.addAttribute("nastup", theNastup);
		
        return "nastupForm.html";
    }
	
	
	@GetMapping("/showAddNastupKoreografijeForm")
    public String showFormForAddKoreografije(@RequestParam("nastup_id") int theId, Model theModel) {
		
		
		Nastup theNastup = nastupService.findById(theId);
		List<Koreografija> allKoreografije = koreografijaService.findAll();
		
		
		List<Koreografija> koreografije = theNastup.getKoreografije();
		List<Koreografija> finalKoreografije =  new ArrayList<>();
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempId = new ArrayList<>();
		
		
		
		NastupKoreografijeDTO theNastupKoreografijeDTO = new NastupKoreografijeDTO();

		if(koreografije == null) {
			
			finalKoreografije = allKoreografije;
			
		}else {
			
			finalKoreografije = koreografijaService.koreografijeInOneList(allKoreografije, koreografije);
			
		}
		
		
		
		int size = finalKoreografije.size();
		
		for(int i=0; i< size; i++) {
			
			tempList.add(false);
			tempId.add(theId);	
		}
		
		Koreografija validationKoreografija = new Koreografija();
		validationKoreografija.setNaziv("		VALIDACIJA");
		finalKoreografije.add(validationKoreografija);
		tempList.add(false);
		tempId.add(theId);
		
		
		theNastupKoreografijeDTO.setKoreografije(finalKoreografije);
		theNastupKoreografijeDTO.setRepertoar(tempList);
		theNastupKoreografijeDTO.setNastup_id(tempId);
		
		theModel.addAttribute("delete", false);
		theModel.addAttribute("add", true);
		theModel.addAttribute("lista", theNastupKoreografijeDTO);
		
        return "nastupKoreografijeAddForm.html";
    }
	
	@GetMapping("/showDeleteNastupKoreografijeForm")
    public String showDeleteFormForKoreografije(@RequestParam("nastup_id") int theId, Model theModel) {
		
		
		Nastup theNastup = nastupService.findById(theId);
		
		List<Koreografija> koreografije = theNastup.getKoreografije();
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempId = new ArrayList<>();
		
		
		
		NastupKoreografijeDTO theNastupKoreografijeDTO = new NastupKoreografijeDTO();
		
		
		
		int size = koreografije.size();
		
		for(int i=0; i< size; i++) {
			
			tempList.add(false);
			tempId.add(theId);	
		}
		
		Koreografija validationKoreografija = new Koreografija();
		validationKoreografija.setNaziv("		VALIDACIJA");
		koreografije.add(validationKoreografija);
		tempList.add(false);
		tempId.add(theId);
		
		
		theNastupKoreografijeDTO.setKoreografije(koreografije);
		theNastupKoreografijeDTO.setRepertoar(tempList);
		theNastupKoreografijeDTO.setNastup_id(tempId);
		
		theModel.addAttribute("lista", theNastupKoreografijeDTO);
		theModel.addAttribute("delete", true);
		theModel.addAttribute("add", false);
		
        return "nastupKoreografijeAddForm.html";
    }

	
	@PostMapping("/addKoreografijeToNastup")
    public String addKoreografije(@ModelAttribute("lista") NastupKoreografijeDTO theNastupKoreografijeDTO) {
		
		List<Koreografija> koreografije = theNastupKoreografijeDTO.getKoreografije();
		List<Boolean> tempList = theNastupKoreografijeDTO.getRepertoar();
		List<Integer> tempId = theNastupKoreografijeDTO.getNastup_id();
		
		int theId = tempId.get(0);
		Nastup theNastup = nastupService.findById(theId);
		System.out.println(theNastup.getMesto());
		
		int[] boolToInt = new int[tempList.size()];
		List<Koreografija> secondList = new ArrayList<>();
		
		
		int j=0;
		for(Boolean temp: tempList) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			
			j++;
		}
		
		int end = koreografije.size()-1;
		int i=0;
		
		
		for(Koreografija test: koreografije) {
			
			if(i<end) {
				
				if(boolToInt[i] == 1) {
					
					Koreografija temp = koreografijaService.findByNaziv(test.getNaziv());
					secondList.add(temp);
				}
				
				
			}
			
			i++;
		}

		
		
		List<Koreografija> trenutne = theNastup.getKoreografije();
		trenutne.addAll(secondList);
		
		
		nastupService.save(theNastup);
		
		
		return "redirect:/nastupi/list";
    }

	
	
	
	@PostMapping("/removeKoreografijeToNastup")
    public String removeKoreografije(@ModelAttribute("lista") NastupKoreografijeDTO theNastupKoreografijeDTO) {
		
		List<Koreografija> koreografije = theNastupKoreografijeDTO.getKoreografije();
		List<Boolean> tempList = theNastupKoreografijeDTO.getRepertoar();
		List<Integer> tempId = theNastupKoreografijeDTO.getNastup_id();
		
		int theId = tempId.get(0);
		Nastup theNastup = nastupService.findById(theId);
		
		
		int[] boolToInt = new int[tempList.size()];
		List<Koreografija> secondList = new ArrayList<>();
		
		
		int j=0;
		for(Boolean temp: tempList) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			
			j++;
		}
		
		int end = koreografije.size()-1;
		int i=0;
		
		
		for(Koreografija test: koreografije) {
			
			if(i<end) {
				
				if(boolToInt[i] == 1) {
					
					Koreografija temp = koreografijaService.findByNaziv(test.getNaziv());
					secondList.add(temp);
				}
				
				
			}
			
			i++;
		}

		
		
		List<Koreografija> trenutne = theNastup.getKoreografije();
		trenutne.removeAll(secondList);
		
		
		nastupService.save(theNastup);
		
		
		return "redirect:/nastupi/list";
    }
	
	@GetMapping("/prijava")
	public String prijava(@RequestParam("nastup_id") int theId) {
		
		Nastup theNastup = nastupService.findById(theId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		
		
		//List<Clan> temp = theNastup.getClanovi();
		
		Boolean result;
		Integer igrac_id = theClan.getClan_id();
		
		//i=funkcija koja prima id igraca i id nastupa vraca 1 ako je clan putuje, a 0 ako ne putuje
		result = clanService.putuje(igrac_id, theId);
		
		if(result == true){
		  
			//poruka da je clan vec prijavljen
			System.out.println("Vec si upisan");
		  
		}else{
			
			//poruka da je clan prijavljen
			
			theNastup.addClana(theClan);
			
			nastupService.save(theNastup);
			
			System.out.println("Uspesno upisan");
		}
	
		return "redirect:/nastupi/list";
		
	}
	
	@GetMapping("/odustajanje")
	public String odjava(@RequestParam("nastup_id") int theId) {
		
		Nastup theNastup = nastupService.findById(theId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnicko_ime = auth.getName();
		
		Clan theClan = clanService.findByUsername(korisnicko_ime);
		
		theNastup.removeClana(theClan);
		
		nastupService.save(theNastup);
		
		return "redirect:/clanovi/profil";
	}
	
	@GetMapping("/spisak")
	public String spisak(@RequestParam("nastup_id") int theId, Model theModel) {
		
		Nastup theNastup = nastupService.findById(theId);
		
		List<Clan> clanovi = theNastup.getClanovi();
		
		theModel.addAttribute("clanovi", clanovi);
		theModel.addAttribute("nastup", false);
		
		return "clan.html";
		
	}
	
}

