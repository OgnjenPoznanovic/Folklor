package com.eproject.folklor.markovic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Proba;
import com.eproject.folklor.markovic.repository.ProbaRepository;

@Service
public class ProbaService {

	private ProbaRepository probaRepository;
	
	public ProbaService(ProbaRepository theProbaRepository) {
		
		probaRepository = theProbaRepository;
	
	}
	
	public void saveProba(Proba theProba) {
		
		probaRepository.save(theProba);
	}
	
	public List<Proba> findAll(){
		return probaRepository.findAll();
	}
	
	public Proba findById(int theId) {
		Optional<Proba> result = probaRepository.findById(theId);
		
		Proba theProba = null;
		
		if(result.isPresent()) {
			theProba = result.get();
		}
		else {
			throw new RuntimeException("Nema te probe");
		}
		
		
		return theProba;
	}
	
	public List<Proba> findByAnsamblNaziv(String naziv) {
		List<Proba> result = probaRepository.findAll();
		
		List<Proba> theProba = new ArrayList<>();
		
		if(naziv.equalsIgnoreCase(" ")) {
			theProba = result;
		}else {
			for(Proba tempProba: result) {
				if(tempProba.getAnsambl_id().getNaziv().equalsIgnoreCase(naziv)) {
					
					System.out.println(tempProba.getAnsambl_id().getNaziv());
					theProba.add(tempProba);
				}
			}
		}
		
		
		return theProba;
	}
	
	
	
	public void deleteById(int theId) {
		probaRepository.deleteById(theId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
