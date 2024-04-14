package com.eproject.folklor.markovic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Koreografija;
import com.eproject.folklor.markovic.entity.Nosnja;
import com.eproject.folklor.markovic.service.KoreografijaService;
import com.eproject.folklor.markovic.service.NosnjaService;

import entity_dto.KoreografijaNosnjaDTO;

@Controller
@RequestMapping("/nosnja")
public class NosnjaController {
	
	private NosnjaService nosnjaService;
	
	private KoreografijaService koreografijaService;
	
	public NosnjaController (NosnjaService theNosnjaService, KoreografijaService theKoreografijaService ) {
		
		nosnjaService = theNosnjaService;
		koreografijaService = theKoreografijaService;
		
	}
	
	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
	}
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		
		List<Nosnja> theNosnje = nosnjaService.findAll();
		
		theModel.addAttribute("nosnje", theNosnje);
		
		return "nosnje.html";
	}
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		List<Koreografija> koreografijaList = koreografijaService.findAll();
		
		KoreografijaNosnjaDTO theKoreografijaNosnjaDTO = new KoreografijaNosnjaDTO(); 
		
		
		theModel.addAttribute("add", true);
		theModel.addAttribute("update", false);
		theModel.addAttribute("koreografijaList", koreografijaList);
		theModel.addAttribute("nosnja", theKoreografijaNosnjaDTO);
		
		return "koreografijaNosnjaForm.html";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("nosnja_id") int theId, Model theModel) {
		
		Nosnja theNosnja = nosnjaService.findById(theId);
		Koreografija theKoreografija = theNosnja.getKoreografija_id();
		
		
		KoreografijaNosnjaDTO theKoreografijaNosnjaDTO = new KoreografijaNosnjaDTO(); 
		
		theKoreografijaNosnjaDTO.setDeo_tela(theNosnja.getDeo_tela());
		theKoreografijaNosnjaDTO.setPol(theNosnja.getPol());
		theKoreografijaNosnjaDTO.setOblast(theNosnja.getOblast());
		theKoreografijaNosnjaDTO.setVelicina(theNosnja.getVelicina());
		theKoreografijaNosnjaDTO.setOznaka(theNosnja.getOznaka());
		theKoreografijaNosnjaDTO.setNosnja_id(theId);
		theKoreografijaNosnjaDTO.setKoreografija_naziv(theKoreografija.getNaziv());
		
		
		theModel.addAttribute("add", false);
		theModel.addAttribute("update", true);
		theModel.addAttribute("nosnja", theKoreografijaNosnjaDTO);
		
		return "koreografijaNosnjaForm.html";
	}
	
	
	@PostMapping("/saveNosnju")
	public String save(@ModelAttribute("nosnja") KoreografijaNosnjaDTO theKoreografijaNosnjaDTO) {
		
		Nosnja theNosnja = new Nosnja();
		Koreografija theKoreografija= koreografijaService.findByNaziv(theKoreografijaNosnjaDTO.getKoreografija_naziv());
		
		if(theKoreografijaNosnjaDTO.getNosnja_id() != 0) {
			theNosnja = nosnjaService.findById(theKoreografijaNosnjaDTO.getNosnja_id());
			theNosnja.setPol(theKoreografijaNosnjaDTO.getPol());
			theNosnja.setOblast(theKoreografijaNosnjaDTO.getOblast());
			theNosnja.setDeo_tela(theKoreografijaNosnjaDTO.getDeo_tela());
			theNosnja.setVelicina(theKoreografijaNosnjaDTO.getVelicina());
			theNosnja.setOznaka(theKoreografijaNosnjaDTO.getOznaka());
			theNosnja.setKoreografija_id(theKoreografija);
			
			nosnjaService.saveNosnju(theNosnja);
			return "redirect:/nosnja/list";
			
		}

		theNosnja.setPol(theKoreografijaNosnjaDTO.getPol());
		theNosnja.setOblast(theKoreografijaNosnjaDTO.getOblast());
		theNosnja.setDeo_tela(theKoreografijaNosnjaDTO.getDeo_tela());
		theNosnja.setVelicina(theKoreografijaNosnjaDTO.getVelicina());
		theNosnja.setOznaka(theKoreografijaNosnjaDTO.getOznaka());
		theNosnja.setKoreografija_id(theKoreografija);
		
		
		nosnjaService.saveNosnju(theNosnja);
		
		return "redirect:/nosnja/list";
		
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("nosnja_id") int theId){
		
		nosnjaService.deleteById(theId);
		
		return "redirect:/nosnja/list";
	}
	
	
	
	
	
	
}
