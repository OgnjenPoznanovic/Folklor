package com.eproject.folklor.markovic.controller;


import java.util.ArrayList;
import java.util.Collections;
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

import com.eproject.folklor.markovic.entity.Ansambl;
import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Proba;
import com.eproject.folklor.markovic.service.AnsamblService;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.ProbaService;

import entity_dto.AnsamblClanoviDTO;
import entity_dto.AnsamblDTO;






@Controller
@RequestMapping("/ansambl")
public class AnsamblController {

	private AnsamblService ansamblService;
	
	private ProbaService probaService;
	
	private ClanService clanService;
	
	
	public AnsamblController(AnsamblService theAnsamblService, ProbaService theProbaService, ClanService theClanService) {
		
		ansamblService = theAnsamblService;
		probaService = theProbaService;
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
	
	@GetMapping("probe")
	public String showProbeofAnsambl(Model theModel) {
		//izvuces sve probe i ubacis u novi entity
		List<Proba> theProbe = probaService.findAll();
		
		AnsamblDTO tempAnsamblDTO = new AnsamblDTO();
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		
		Ansambl tempAnsambl = new Ansambl();
		tempAnsambl.setNaziv(" ");
		ansamblList.add(tempAnsambl);
		
		Collections.reverse(ansamblList);
		
		//dodaj model atribut
		theModel.addAttribute("ansamblLista", ansamblList);
		theModel.addAttribute("tempAnsamblDTO", tempAnsamblDTO);
		theModel.addAttribute("probe", theProbe);	
		
		return "ansamblProbe.html";
	}
	
	@GetMapping("probeAnsambla")
	public String temp(Model theModel, @ModelAttribute("tempAnsamblDTO") AnsamblDTO tempAnsamblDTO) {
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		
		Ansambl tempAnsambl = new Ansambl();
		tempAnsambl.setNaziv(" ");
		ansamblList.add(tempAnsambl);
		Collections.reverse(ansamblList);
		
		theModel.addAttribute("ansamblLista", ansamblList);
		
		String tempNaziv = tempAnsamblDTO.getNaziv();
		
		
		List<Proba> probe = probaService.findByAnsamblNaziv(tempNaziv);
		
		theModel.addAttribute("probe", probe);
		
		
		
		return "ansamblProbe.html";
	}
	
	@GetMapping("clanovi")
	public String showClanoveofAnsambl(Model theModel) {
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		
		AnsamblClanoviDTO theAnsamblClanoviDTO = new AnsamblClanoviDTO();
		
		Clan theClan = new Clan();
		List<String> stringList = new ArrayList<>();
		
		
		ansamblList.remove(2);
		
		Ansambl tempAnsambl = new Ansambl();
		tempAnsambl.setNaziv(" ");
		ansamblList.add(tempAnsambl);
		
		Collections.reverse(ansamblList);
		
		
		
		
		theModel.addAttribute("ansamblClanoviDTO", theAnsamblClanoviDTO);
		theModel.addAttribute("ansamblLista", ansamblList);
		theModel.addAttribute("clan", theClan);
		theModel.addAttribute("ansambli", stringList);
		
		return "ansamblClanovi.html";
	}
	
	@GetMapping("showClanToAnsambl")
	public String showClanToAnsambl(Model theModel) {
		
		AnsamblClanoviDTO theAnsamblClanoviDTO = new AnsamblClanoviDTO();
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		
		theModel.addAttribute("ansamblClanoviDTO", theAnsamblClanoviDTO);
		theModel.addAttribute("ansamblLista", ansamblList);
		
		return "ansamblClanForm.html";
	}
	
	
	
	@PostMapping("addClanToAnsambl")
	public String addClanToAnsambl(Model theModel, @ModelAttribute("ansamblClanoviDTO") AnsamblClanoviDTO theAnsamblClanoviDTO) {
		
		String ime = theAnsamblClanoviDTO.getIme();
		String prezime = theAnsamblClanoviDTO.getPrezime();
		String korisnicko_ime = theAnsamblClanoviDTO.getKorisnicko_ime();
		
		List<String> tempNaziv = theAnsamblClanoviDTO.getNaziv();
		String naziv = tempNaziv.get(0);
		
		Ansambl tempAnsambl = new Ansambl();
		Clan theClan = new Clan();
		
		theClan = clanService.findByUsernameAndImeAndPrezime(korisnicko_ime, ime, prezime);
		tempAnsambl = ansamblService.findByNaziv(naziv);
		
		
		System.out.println(theClan.getEmail());
		System.out.println(tempAnsambl.getAnsambl_id());
		
		
		
		
		tempAnsambl.addClana(theClan);
		ansamblService.saveAnsambl(tempAnsambl);
		
		
		return "redirect:/ansambl/clanovi";
		
	}
	
	
	@PostMapping("removeClanToAnsambl")
	public String removeClanToAnsambl(Model theModel, @ModelAttribute("ansamblClanoviDTO") AnsamblClanoviDTO theAnsamblClanoviDTO) {
		
		String ime = theAnsamblClanoviDTO.getIme();
		String prezime = theAnsamblClanoviDTO.getPrezime();
		String korisnicko_ime = theAnsamblClanoviDTO.getKorisnicko_ime();
		
		List<String> tempNaziv = theAnsamblClanoviDTO.getNaziv();
		String naziv = tempNaziv.get(0);
		
		Ansambl tempAnsambl = new Ansambl();
		Clan theClan = new Clan();
		
		theClan = clanService.findByUsernameAndImeAndPrezime(korisnicko_ime, ime, prezime);
		tempAnsambl = ansamblService.findByNaziv(naziv);
		
		
		System.out.println(theClan.getEmail());
		System.out.println(tempAnsambl.getAnsambl_id());
		
		tempAnsambl.removeClan(theClan);
		ansamblService.saveAnsambl(tempAnsambl);
		
		
		return "redirect:/ansambl/clanovi";
	}
	
	@GetMapping("clanAnsamblDeleteForm")
	public String clanAnsamblDeleteForm(Model theModel) {
		
		AnsamblClanoviDTO theAnsamblClanoviDTO = new AnsamblClanoviDTO();
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		
		theModel.addAttribute("ansamblClanoviDTO", theAnsamblClanoviDTO);
		theModel.addAttribute("ansamblLista", ansamblList);
		
		return "clanAnsamblDeleteForm.html";
	}
	
	
	
	@PostMapping("filterClanoveOfAnsambla")
	public String filterClanoveOfAnsambla(Model theModel, @ModelAttribute("ansamblClanoviDTO") AnsamblClanoviDTO theAnsamblClanoviDTO) {
		
		
		String korisnicko_ime = theAnsamblClanoviDTO.getKorisnicko_ime();
		String ime = theAnsamblClanoviDTO.getIme();
		String prezime = theAnsamblClanoviDTO.getPrezime();
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		Ansambl tempAnsambl = new Ansambl();
		tempAnsambl.setNaziv(" ");
		ansamblList.add(tempAnsambl);
		Collections.reverse(ansamblList);
		theModel.addAttribute("ansamblLista", ansamblList);
		
		
		
		if(korisnicko_ime != "") {
			
			Clan theClan = clanService.findByUsername(korisnicko_ime);
			
			theModel.addAttribute("clan", theClan);
			
			
			
			
		}
		else{
			
			if(ime != "" & prezime != "") {
				
				//nadji clanove koji imaju ovo ime i prezime
				List<Clan> theClanovi = clanService.findByImeAndPrezime(ime, prezime);
				
				theModel.addAttribute("clan", theClanovi);	
				
			}else {
				
				if(ime != "") {
					
					//nadji ljude sa ovim imenom
					List<Clan> theClanovi = clanService.findByIme(ime);
					
					theModel.addAttribute("clan", theClanovi);
					
				}else {
					
					if(prezime != "") {
						
						//nadji ljude sa ovim prezimenom
						List<Clan> theClanovi = clanService.findByPrezime(prezime);
						
						theModel.addAttribute("clan", theClanovi);
						
					}else {
						
						//nadji sve ljude iz ovog ansambla
						
						List<String> naziv = theAnsamblClanoviDTO.getNaziv();
						String tempNaziv = naziv.get(0);
						
						Ansambl theAnsambl = ansamblService.findByNaziv(tempNaziv);
						Integer theId = theAnsambl.getAnsambl_id();
						
						List<Clan> theClanovi = clanService.findByAnsamblId(theId);
						
						theModel.addAttribute("clan", theClanovi);
						
						
						
					}
					
				}
				
			}
					
		}
		
		return "ansamblClanovi.html";
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
		
		Ansambl theAnsambl = ansamblService.findById(theId);
		Ansambl tempTheAnsambl = ansamblService.findById(13);
		
		List<Proba> probe = theAnsambl.getProbe();
		
		for(Proba tempProba : probe) {
			tempProba.setAnsambl_id(tempTheAnsambl);
		}
		
		ansamblService.deleteById(theId);
		
		return "redirect:/ansambl/list";
	}
}
