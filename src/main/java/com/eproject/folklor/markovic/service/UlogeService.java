package com.eproject.folklor.markovic.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Uloge;
import com.eproject.folklor.markovic.repository.UlogeRepository;

@Service
public class UlogeService {

	public UlogeRepository ulogeRepository;
	
	public UlogeService(UlogeRepository theUlogeRepository) {
		ulogeRepository = theUlogeRepository;
	}
	
	public void save(Uloge theUloge) {
		ulogeRepository.save(theUloge);
	}
	
	public void delete(Uloge theUloga) {
		
		ulogeRepository.delete(theUloga);
	}
	
	public Uloge findByUsername(String username) {
		
		Optional<Uloge> result = ulogeRepository.findByUsername(username);
		
		Uloge theUloga = null;
		
		if(result.isPresent()) {
			
			theUloga = result.get();
		}else {
			
		}
		
		return theUloga;
	}
	
}
