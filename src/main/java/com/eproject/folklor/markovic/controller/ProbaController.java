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

import com.eproject.folklor.markovic.entity.Ansambl;
import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Proba;
import com.eproject.folklor.markovic.service.AnsamblService;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.ProbaService;

import entity_dto.AnsamblClanoviDTO;
import entity_dto.AnsamblProbaDTO;
import entity_dto.ClanProbaDTO;
import entity_dto.ClanProbaForm;

@Controller
@RequestMapping("/proba")
public class ProbaController {

	private ProbaService probaService;
	
	private AnsamblService ansamblService;
	
	private ClanService clanService;
	
	public ProbaController(ProbaService theProbaService, AnsamblService theAnsamblService, ClanService theClanService){
		probaService = theProbaService;
		
		ansamblService = theAnsamblService;
		
		clanService = theClanService;
	}
	
	@GetMapping("/home")
	public String backToHome() {
		
		return "home.html";
	}
	
	@GetMapping("/stats")
	public String clanStats(Model theModel) {
		
		ClanProbaDTO theClanProbaDTO = new ClanProbaDTO();
		
		Proba theProba = new Proba();
		Clan theClan = new Clan();
		
		
		theModel.addAttribute("clan", theClan);
		theModel.addAttribute("clanProbaDTO", theClanProbaDTO);
		theModel.addAttribute("probe", theProba);
		
		return "probaClanStats.html";
	}
	
	@PostMapping("showAllProbe")
	public String showAllProbe(Model theModel, @ModelAttribute("clanProbaDTO") ClanProbaDTO theClanProbaDTO) {
		
		
		String tempIme = theClanProbaDTO.getIme();
		String tempPrezime = theClanProbaDTO.getPrezime();
		
		
		
		Clan theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
		
		List<Proba> theProba = theClan.getProbe();
		
		
		
		theModel.addAttribute("clan", theClan);
		theModel.addAttribute("probe", theProba);
		
		return "probaClanStats.html";
	}
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		
		
		List<Proba> theProbe = probaService.findAll();
		
		theModel.addAttribute("probe", theProbe);
		
		return "proba.html";
	}
	
	
	@GetMapping("showFormAnsamblProbeDTO")
	public String showAnsamblProbeDTOForm(Model theModel) {
		
		List<Ansambl> ansamblList = ansamblService.findAll(); 
		ansamblList.remove(2);
		
		AnsamblProbaDTO theAnsamblProbaDTO = new AnsamblProbaDTO();
		
		theModel.addAttribute("ansamblProbaDТО", theAnsamblProbaDTO);
		theModel.addAttribute("ansamblLista", ansamblList);
		
		return "ansamblProbaForm.html";
	}
	
	
	@PostMapping("/saveProbuOfAnsambl")
	public String saveProbuOfAnsambl(@ModelAttribute("ansamblProbaDТО") AnsamblProbaDTO theAnsamblProbaDTO) {
		
		Ansambl theAnsambl = new Ansambl();
		
		theAnsambl = ansamblService.findByNaziv(theAnsamblProbaDTO.getNaziv());
		Proba theProba = new Proba();
		theProba.setDan(theAnsamblProbaDTO.getDan());
		theProba.setMesec(theAnsamblProbaDTO.getMesec());
		theProba.setGodina(theAnsamblProbaDTO.getGodina());
		theProba.setPocetak(theAnsamblProbaDTO.getPocetak());
		theProba.setAnsambl_id(theAnsambl);
		
		probaService.saveProba(theProba);
			
		return "redirect:/proba/list";
	}
	
	
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		Proba theProba = new Proba();
		
		theModel.addAttribute("proba", theProba);
		
		return "probaForm.html";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showForm(@RequestParam("proba_id")int theId, Model theModel) {
		
		Proba theProba = probaService.findById(theId);
		
		
		theModel.addAttribute("proba", theProba);
		
		return "probeForm.html";
	}
	
	@GetMapping("/showDeleteClanProbaForm")
	public String showDeleteClanProbaForm(@RequestParam("proba_id") int theId, Model theModel) {
		
		ClanProbaForm theClanProbaForm = new ClanProbaForm();
		
		int tempId = theId;	
		Proba theProba = probaService.findById(theId);
		
		Ansambl theAnsambl = theProba.getAnsambl_id();
		
		List<Clan> tempClanovi = theProba.getClanovi();
		
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempProbe = new ArrayList<>();
		int size = tempClanovi.size();
		for(int i=0; i< size; i++) {
			
			tempProbe.add(tempId);
			tempList.add(false);
			
		}
		
		Clan validationClan = new Clan("", "VALIDACIJA");
		tempList.add(false);
		tempClanovi.add(validationClan);
		tempProbe.add(tempId);
		
		theClanProbaForm.setPrisutan(tempList);
		theClanProbaForm.setProba_id(tempProbe);
		theClanProbaForm.setClanovi(tempClanovi);
		
		theModel.addAttribute("theId", tempId);
		theModel.addAttribute("lista", theClanProbaForm);
		
		return "probaClanDeleteForm.html";
	}
	
	@PostMapping("deleteClanoveToProba")
	public String deleteClanProbaForm(@ModelAttribute("lista") ClanProbaForm theClanProbaForm) {
		
		Clan theClan = new Clan();
		List<Boolean> prijava = theClanProbaForm.getPrisutan();
		List<Integer> tempProbe = theClanProbaForm.getProba_id();
		List<Clan> clanovi = theClanProbaForm.getClanovi();
		int theId = tempProbe.get(0);
		Proba theProba = new Proba();
		
		
		theProba = probaService.findById(theId);
		System.out.println(theProba.getMesec());
		
		System.out.println(theId);
		
		int[] boolToInt = new int[prijava.size()];
		
		int j=0;
		for(Boolean temp: prijava) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			System.out.println(boolToInt[j]);
			j++;
		}
		
		int end = clanovi.size()-1;
		System.out.println(end);
		int i=0;
		for(Clan test : clanovi) {
			if(i<end) {
				String tempIme = test.getIme();
				String tempPrezime = test.getPrezime();
				theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
				if(boolToInt[i] == 1) {
					theProba.deleteClana(theClan);
					
				}
			}
			
			i++;
			
		}
	
		probaService.saveProba(theProba);
		
			
		return "redirect:/proba/list";
	}
	
	
	@GetMapping("/showAddClanProbaForm")
	public String showAddClanProbaForm(@RequestParam("proba_id")int theId, Model theModel) {
		
		
		ClanProbaForm theClanProbaForm = new ClanProbaForm();
		
		int tempId = theId;
		
		
		Proba theProba = probaService.findById(theId);
		
		

		
		Ansambl theAnsambl = theProba.getAnsambl_id();
		
		
		List<Clan> allClanoviAnsambla = clanService.findByAnsamblId(theAnsambl.getAnsambl_id());
		
		
		List<Clan> prisutniClanovi = clanService.findByProbaId(theId);
		
		List<Clan> tempClanovi =  new ArrayList<>();
		
		if(prisutniClanovi == null) {
			tempClanovi.addAll(allClanoviAnsambla);
			
		}else {
			
			tempClanovi=clanService.clanoviInOneList(allClanoviAnsambla, prisutniClanovi);
		}
		
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempProbe = new ArrayList<>();
		int size = tempClanovi.size();
		for(int i=0; i< size; i++) {
			
			tempProbe.add(tempId);
			tempList.add(false);
			
		}
		
		
		
		
		Clan validationClan = new Clan("", "VALIDACIJA");
		tempList.add(false);
		tempClanovi.add(validationClan);
		tempProbe.add(tempId);
		
		theClanProbaForm.setPrisutan(tempList);
		theClanProbaForm.setProba_id(tempProbe);
		theClanProbaForm.setClanovi(tempClanovi);
		
		
		
		theModel.addAttribute("theId", tempId);
		theModel.addAttribute("lista", theClanProbaForm);
		
		return "probaClanAddForm.html";
		
		
	}
	
	@PostMapping("addClanoveToProba")
	public String addClanProbaForm(@ModelAttribute("lista") ClanProbaForm theClanProbaForm) {
		
		Clan theClan = new Clan();
		List<Boolean> prijava = theClanProbaForm.getPrisutan();
		List<Integer> tempProbe = theClanProbaForm.getProba_id();
		List<Clan> clanovi = theClanProbaForm.getClanovi();
		int theId = tempProbe.get(0);
		Proba theProba = new Proba();
		
		
		theProba = probaService.findById(theId);
		System.out.println(theProba.getMesec());
		
		System.out.println(theId);
		
		int[] boolToInt = new int[prijava.size()];
		
		int j=0;
		for(Boolean temp: prijava) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			System.out.println(boolToInt[j]);
			j++;
		}
		
		int end = clanovi.size()-1;
		System.out.println(end);
		int i=0;
		int k=0;
		for(Clan test : clanovi) {
			if(i<end) {
				String tempIme = test.getIme();
				String tempPrezime = test.getPrezime();
				theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
				if(boolToInt[i] == 1) {
					theProba.addClana(theClan);
					k++;
				}
			}
			
			i++;
			
		}
		

		
		int broj_pirustnih = theProba.getPrisustvo();
		broj_pirustnih = broj_pirustnih+k;
		theProba.setPrisustvo(broj_pirustnih);
		
		
	
		probaService.saveProba(theProba);
		
			
		return "redirect:/proba/list";
	}
	
	
	@PostMapping("/save")
	public String saveProba(@ModelAttribute("proba") Proba theProba) {
		
	
		probaService.saveProba(theProba);
		
		return "redirect:/proba/list";
		
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("proba_id") int theId) {
		probaService.deleteById(theId);
		
		return "redirect:/proba/list";
	}
}
