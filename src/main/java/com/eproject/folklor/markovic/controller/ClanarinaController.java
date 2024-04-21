package com.eproject.folklor.markovic.controller;

import java.util.ArrayList;
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
import com.eproject.folklor.markovic.entity.Clanarina;
import com.eproject.folklor.markovic.service.AnsamblService;
import com.eproject.folklor.markovic.service.ClanService;
import com.eproject.folklor.markovic.service.ClanarinaService;

import entity_dto.AnsamblClanarinaDTO;
import entity_dto.ClanClanarinaForm;
import entity_dto.ClanProbaDTO;


@Controller
@RequestMapping("/clanarina")
public class ClanarinaController {

	private ClanarinaService clanarinaService;
	
	private AnsamblService  ansamblService;
	
	private ClanService clanService;
	
	public ClanarinaController(ClanarinaService theClanarinaService, AnsamblService  theAnsamblService, ClanService theClanService) {
		
		clanarinaService = theClanarinaService;
		
		ansamblService= theAnsamblService;
		
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
	
	@GetMapping("/stats")
	public String clanStats(Model theModel) {
		
		ClanProbaDTO theClanProbaDTO = new ClanProbaDTO();
		Clan theClan = new Clan();
		Clanarina theClanarina = new Clanarina();
		
		theModel.addAttribute("clan", theClan);
		theModel.addAttribute("clanProbaDTO", theClanProbaDTO);
		theModel.addAttribute("clanarine", theClanarina);
		
		return "clanarinaClanStats.html";
		
	}
	
	@PostMapping("showAllClanarine")
	public String showAllClanarine(Model theModel, @ModelAttribute("clanProbaDTO") ClanProbaDTO theClanProbaDTO) {
		
		String tempIme = theClanProbaDTO.getIme();
		String tempPrezime = theClanProbaDTO.getPrezime();
		
		Clan theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
		List<Clanarina> clanarine = theClan.getClanarina();
		//List<Clanarina> theclanarine = new ArrayList<>();
		
		
		theModel.addAttribute("clan", theClan);
		theModel.addAttribute("clanarine", clanarine);
		
		return "clanarinaClanStats.html";
	}
	
	
	@GetMapping("/list")
	public String showList(Model theModel) {
		
		
		List<Clanarina> theClanarine = clanarinaService.findAll();
		
		theModel.addAttribute("clanarine", theClanarine);
		
		return "clanarina.html";
	}
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		List<Ansambl> ansamblList = ansamblService.findAll();
		ansamblList.remove(2);
		
		
		AnsamblClanarinaDTO theAnsamblClanarinaDTO = new AnsamblClanarinaDTO();
				
		theModel.addAttribute("addForm", true);
		theModel.addAttribute("updateForm", false);
		theModel.addAttribute("ansamblLista", ansamblList);
		theModel.addAttribute("clanarina", theAnsamblClanarinaDTO);
		
		return "clanarinaForm.html";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showForm(@RequestParam("clanarina_id")int theId, Model theModel) {
		
		
		Clanarina theClanarina = clanarinaService.findById(theId);
		Ansambl theAnsambl = theClanarina.getAnsambl_id();
		
		AnsamblClanarinaDTO theAnsamblClanarinaDTO = new AnsamblClanarinaDTO();
		theAnsamblClanarinaDTO.setClanarina_id(theClanarina.getClanarina_id());
		theAnsamblClanarinaDTO.setCena(theClanarina.getCena());
		theAnsamblClanarinaDTO.setGodina(theClanarina.getGodina());
		theAnsamblClanarinaDTO.setMesec(theClanarina.getMesec());
		theAnsamblClanarinaDTO.setNaziv(theAnsambl.getNaziv());
		
		
		theModel.addAttribute("addForm", false);
		theModel.addAttribute("updateForm", true);
		theModel.addAttribute("clanarina", theAnsamblClanarinaDTO);
		
		return "clanarinaForm.html";
	}
	
	@GetMapping("/showDeleteClanClanarinaForm")
	public String showDeleteClanClanarinaForm(@RequestParam("clanarina_id") int theId, Model theModel) {
		
		
		ClanClanarinaForm theClanClanarinaForm = new ClanClanarinaForm();
		int tempId = theId;	
		Clanarina theClanarina = clanarinaService.findById(tempId);
		
		List<Clan> tempClanovi =  theClanarina.getClanovi();
		
		for(Clan te: tempClanovi) {
		
			System.out.println(te.getPrezime());
		}
		
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempClanarina = new ArrayList<>();
		int size = tempClanovi.size();
		for(int i=0; i< size; i++) {
			
			tempClanarina.add(tempId);
			tempList.add(false);
			
		}
		
		Clan validationClan = new Clan("", "VALIDACIJA");
		tempList.add(false);
		tempClanovi.add(validationClan);
		tempClanarina.add(tempId);
		
		
		theClanClanarinaForm.setClanarina_id(tempClanarina);
		theClanClanarinaForm.setClanovi(tempClanovi);
		theClanClanarinaForm.setPlatio(tempList);
		
		theModel.addAttribute("theId", tempId);
		theModel.addAttribute("lista", theClanClanarinaForm);
		
		return "clanarinaClanDeleteForm.html";
	}
	
	
	
	@GetMapping("/showAddClanClanarinaForm")
	public String showAddClanClanarinaForm(@RequestParam("clanarina_id")int theId, Model theModel) {
		
		ClanClanarinaForm theClanClanarinaForm = new ClanClanarinaForm();
		
		int tempId = theId;
		
		Clanarina theClanarina = clanarinaService.findById(tempId);
		
		Ansambl theAnsambl = theClanarina.getAnsambl_id();
		
		List<Clan> allClanoviAnsambla = clanService.findByAnsamblId(theAnsambl.getAnsambl_id());
		
		List<Clan> platili = clanService.findByClanarinaId(tempId);
		
		List<Clan> tempClanovi =  new ArrayList<>();
		
		if(platili == null) {
			
			tempClanovi.addAll(allClanoviAnsambla);
		}else {
			
			tempClanovi=clanService.clanoviInOneList(allClanoviAnsambla, platili);
		}
		
		List<Boolean> tempList = new ArrayList<>();
		List<Integer> tempClanarine = new ArrayList<>();
		int size = tempClanovi.size();
		
		for(int i=0; i< size; i++) {
			
			tempClanarine.add(tempId);
			tempList.add(false);
			
		}
		
		Clan validationClan = new Clan("", "VALIDACIJA");
		tempList.add(false);
		tempClanovi.add(validationClan);
		tempClanarine.add(tempId);
		
		theClanClanarinaForm.setClanovi(tempClanovi);
		theClanClanarinaForm.setClanarina_id(tempClanarine);
		theClanClanarinaForm.setPlatio(tempList);
		
		theModel.addAttribute("theId", tempId);
		theModel.addAttribute("lista", theClanClanarinaForm);
		
		
		
		return "clanarinaClanAddForm.html";
	}
	
	
	@PostMapping("deleteClanoveToClanarina")
	public String deleteClanClanarinaForm(@ModelAttribute("lista") ClanClanarinaForm theClanClanarinaForm) {
	
		Clan theClan = new Clan();
		
		List<Ansambl> pripada = new ArrayList<>();
		
		List<Boolean> platio = theClanClanarinaForm.getPlatio();
		List<Integer> tempClanarine = theClanClanarinaForm.getClanarina_id();
		List<Clan> clanovi = theClanClanarinaForm.getClanovi();
		
		int theId = tempClanarine.get(0);
		Clanarina theClanarina = new Clanarina();
		theClanarina = clanarinaService.findById(theId);
		
		List<Clanarina> tempList =  new ArrayList<>();
		List<Clanarina> secondClanarina =  new ArrayList<>();
		
		String mesec = theClanarina.getMesec();
		String godina = theClanarina.getGodina();
		
		tempList = clanarinaService.findByMesecAndGodina(mesec, godina);
		
		int[] boolToInt = new int[platio.size()];
		
		int j=0;
		for(Boolean temp: platio) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			
			j++;
		}
		
		int end = clanovi.size()-1;
		int i=0;
		//int k=0;
		for(Clan test : clanovi) {
			if(i<end) {
				String tempIme = test.getIme();
				String tempPrezime = test.getPrezime();
				theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
				pripada.addAll(theClan.getAnsambli());
				if(boolToInt[i] == 1) {
					
					
					for(Ansambl temp: pripada) {
						
						if(temp.getNaziv() != theClanarina.getAnsambl_id().getNaziv()) {
							
						
							
							
							
							for(Clanarina tempClanarinaList: tempList) {
								
								
								if(tempClanarinaList.getAnsambl_id().getNaziv() == temp.getNaziv()) {
									
						
									secondClanarina.add(tempClanarinaList);
									tempClanarinaList.deleteClana(theClan);
								}
								
							}
							
							
						
						}
						
					}
					theClanarina.deleteClana(theClan);
					//k++;
				}
			}
			
			i++;
			
		}
		
		for(Clanarina tempClanarina : secondClanarina) {
			
			
			clanarinaService.saveClanarina(tempClanarina);
			
		
		}
		
		clanarinaService.saveClanarina(theClanarina);
		
		
		return "redirect:/clanarina/list";
	}
	
	
	@PostMapping("addClanoveToClanarina")
	public String addClanClanarinaForm(@ModelAttribute("lista") ClanClanarinaForm theClanClanarinaForm) {
		
		Clan theClan = new Clan();
		
		List<Ansambl> pripada = new ArrayList<>();
		
		List<Boolean> platio = theClanClanarinaForm.getPlatio();
		List<Integer> tempClanarine = theClanClanarinaForm.getClanarina_id();
		List<Clan> clanovi = theClanClanarinaForm.getClanovi();
		
		int theId = tempClanarine.get(0);
		Clanarina theClanarina = new Clanarina();
		theClanarina = clanarinaService.findById(theId);
		
		List<Clanarina> tempList =  new ArrayList<>();
		List<Clanarina> secondClanarina =  new ArrayList<>();
		
		String mesec = theClanarina.getMesec();
		String godina = theClanarina.getGodina();
		
		tempList = clanarinaService.findByMesecAndGodina(mesec, godina);
		
		
		
		int[] boolToInt = new int[platio.size()];
		
		int j=0;
		for(Boolean temp: platio) {
			
			if(temp == null) {
				boolToInt[j] = 0;
			}else {
				boolToInt[j] = 1;
			}
			
			j++;
		}
		
		int end = clanovi.size()-1;
		int i=0;
		
		for(Clan test : clanovi) {
			if(i<end) {
				
				pripada.removeAll(pripada);
				String tempIme = test.getIme();
				String tempPrezime = test.getPrezime();
				theClan = clanService.findOneByImeAndPrezime(tempIme, tempPrezime);
				pripada.addAll(theClan.getAnsambli());
				
				if(boolToInt[i] == 1) {
					for(Ansambl s: pripada) {
						System.out.println(s.getNaziv());
					}
					
					for(Ansambl temp: pripada) {
						
						if(temp.getNaziv() != theClanarina.getAnsambl_id().getNaziv()) {
							
						
							
							
							
							for(Clanarina tempClanarinaList: tempList) {
								
								
								if(tempClanarinaList.getAnsambl_id().getNaziv() == temp.getNaziv()) {
									
						
									secondClanarina.add(tempClanarinaList);
									tempClanarinaList.addClana(theClan);
								}
								
							}
							
							
						
						}
						
					}
					theClanarina.addClana(theClan);
					
				}
			}
			
			i++;
			
		}
		
		
		List<Integer> nameOfClanarina =  new ArrayList<>();
		
		for(Clanarina tempClanarina : secondClanarina) {
			
			nameOfClanarina.add(tempClanarina.getClanarina_id());
			
		}
		
		System.out.println(nameOfClanarina.size());
		
		//izbaci duplikate
		nameOfClanarina=clanarinaService.removeDuplicates(nameOfClanarina);
		
		//napravi clanarine i sacuvaj
		for(Integer clanarinaId: nameOfClanarina) {
				
			Clanarina temp = clanarinaService.findById(clanarinaId);
			clanarinaService.saveClanarina(temp);
			
		}
		
		System.out.println(nameOfClanarina.size());
		clanarinaService.saveClanarina(theClanarina);
		
		
		return "redirect:/clanarina/list";
	}
	
	
	
	
	@PostMapping("/save")
	public String saveClanarina(@ModelAttribute("clanarina") AnsamblClanarinaDTO theAnsamblClanarinaDTO) {
		
		Clanarina theClanarina = new Clanarina();
		
		if(theAnsamblClanarinaDTO.getClanarina_id() != 0) {
		
			theClanarina = clanarinaService.findById(theAnsamblClanarinaDTO.getClanarina_id());
			
			theClanarina.setCena(theAnsamblClanarinaDTO.getCena());
			theClanarina.setGodina(theAnsamblClanarinaDTO.getGodina());
			theClanarina.setMesec(theAnsamblClanarinaDTO.getMesec());
			
			Ansambl theAnsambl = ansamblService.findByNaziv(theAnsamblClanarinaDTO.getNaziv());
			
			theClanarina.setAnsambl_id(theAnsambl);
			
			clanarinaService.saveClanarina(theClanarina);
			
			return "redirect:/clanarina/list";
		}
		theClanarina.setCena(theAnsamblClanarinaDTO.getCena());
		theClanarina.setGodina(theAnsamblClanarinaDTO.getGodina());
		theClanarina.setMesec(theAnsamblClanarinaDTO.getMesec());
		
		Ansambl theAnsambl = ansamblService.findByNaziv(theAnsamblClanarinaDTO.getNaziv());
		
		theClanarina.setAnsambl_id(theAnsambl);
		
		clanarinaService.saveClanarina(theClanarina);
		
		return "redirect:/clanarina/list";
		
	}
	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("clanarina_id") int theId) {
		clanarinaService.deleteById(theId);
		
		return "redirect:/clanarina/list";
	}
	
	
	
}
