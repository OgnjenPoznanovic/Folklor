package com.eproject.folklor.markovic.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;


import com.eproject.folklor.markovic.entity.Nastup;
import com.eproject.folklor.markovic.repository.NastupRepository;

@Service
public class NastupService {

	private NastupRepository nastupRepository;
	
	public NastupService(NastupRepository theNastupRepository) {
		
		nastupRepository = theNastupRepository;
	}
	
	public List<Nastup> findAll(){
		
		return nastupRepository.findAll();
	}


	public Nastup findById(int theId) {
		
		Optional<Nastup> result = nastupRepository.findById(theId);
		
		Nastup theNastup = null;
		
		if(result.isPresent()){
			
			theNastup = result.get();
		}else {
			
			throw new RuntimeException("Nema tog nastupa");
		}
		
		return theNastup;
	}

	public void deleteById(int theId) {
		
		nastupRepository.deleteById(theId);
	}

	public void save(Nastup theNastup) { 
		
		nastupRepository.save(theNastup);
		
	}





}
