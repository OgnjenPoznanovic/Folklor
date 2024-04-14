package com.eproject.folklor.markovic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Nosnja;
import com.eproject.folklor.markovic.repository.NosnjaRepository;



@Service
public class NosnjaService {

	private NosnjaRepository nosnjaRepository;
	
	public NosnjaService(NosnjaRepository thenosnjaRepository) {
		
		nosnjaRepository = thenosnjaRepository;
	}
	
	
	public List<Nosnja> findAll(){
		
		return nosnjaRepository.findAll();
	}
	
	public Nosnja findById(int theId) {
		
		Optional<Nosnja> result = nosnjaRepository.findById(theId);
		
		Nosnja theNosnja = null;
		
		if(result .isPresent()) {
			
			theNosnja = result.get();
			
		}else {
			
			throw new RuntimeException("Nema te nosnje");
		}
		
		
		return theNosnja;
	}
	
	
	public void saveNosnju(Nosnja theNosnja) {
		
		nosnjaRepository.save(theNosnja);
		
	}
	
	public void deleteById(int theId) {
		
		nosnjaRepository.deleteById(theId);
		
	}
	
	
	
	
}
