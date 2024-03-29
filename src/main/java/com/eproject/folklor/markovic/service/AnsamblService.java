package com.eproject.folklor.markovic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Ansambl;
import com.eproject.folklor.markovic.repository.AnsamblRepository;

@Service
public class AnsamblService {
	
	private AnsamblRepository ansamblRepository;
	
	public AnsamblService(AnsamblRepository theAnsamblRepository) {
		
		ansamblRepository = theAnsamblRepository;
	}
	
	public void saveAnsambl(Ansambl theAnsambl) {
		
		ansamblRepository.save(theAnsambl);
	}
	
	
	public List<Ansambl> findAll(){
		
		return ansamblRepository.findAll();
	}
	
	public Ansambl findByNaziv(String naziv) {
		
		Optional<Ansambl> result = ansamblRepository.findByNaziv(naziv);
				
		Ansambl theAnsambl = null;
		
		if(result.isPresent()) {
			theAnsambl = result.get();
		}
		else {
			throw new RuntimeException("Nema ansambla");
		}
	
		return theAnsambl;
	}
	
	public Ansambl findById(int theId) {
		
		Optional<Ansambl> result = ansamblRepository.findById(theId);
		
		Ansambl theAnsambl = null;
		
		if(result.isPresent()) {
			theAnsambl = result.get();
		}
		else {
			throw new RuntimeException("Nema ansambla");
		}
	
		return theAnsambl;
	
	}
	
	public void deleteById(int theId) {
		ansamblRepository.deleteById(theId);
	}

}
