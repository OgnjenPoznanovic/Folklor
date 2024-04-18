package com.eproject.folklor.markovic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eproject.folklor.markovic.entity.Koreografija;
import com.eproject.folklor.markovic.entity.Nastup;
import com.eproject.folklor.markovic.service.KoreografijaService;
import com.eproject.folklor.markovic.service.NastupService;

import entity_dto.NastupKoreografijeDTO;

@Controller
@RequestMapping("nastupi")
public class NastupController {

	private NastupService nastupService;
	private KoreografijaService koreografijaService;
	
	public NastupController(NastupService theNastupService, KoreografijaService theKoreografijaService) {
		nastupService = theNastupService;
		koreografijaService= theKoreografijaService;
	}

	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
	}
	
	@PostMapping("/save")
	public String saveNastup(@ModelAttribute("nastup") Nastup theNastup) {
		
		nastupService.save(theNastup);
		
		return "redirect:/nastupi/list";
	}
	
	@GetMapping("/list")
	public String showAll(Model theModel) {
		
		List<Nastup> theNastupi = nastupService.findAll();
		
		theModel.addAttribute("nastupi", theNastupi);
		
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
	
	
	
	
	
	
}

