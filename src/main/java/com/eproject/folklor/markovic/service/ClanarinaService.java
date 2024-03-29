package com.eproject.folklor.markovic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Ansambl;
import com.eproject.folklor.markovic.entity.Clanarina;
import com.eproject.folklor.markovic.repository.ClanarinaRepository;

@Service
public class ClanarinaService {

	private ClanarinaRepository clanarinaRepository;
	
	public ClanarinaService(ClanarinaRepository theClanarinaRepository){
		
		clanarinaRepository = theClanarinaRepository;
	}
	
	public void saveClanarina(Clanarina theClanarina) {
		
		clanarinaRepository.save(theClanarina);
	}
	
	public Clanarina findClanarina(String mesec, String godina, int cena) {
		
		Optional<Clanarina> result = clanarinaRepository.findClanarina(mesec, godina, cena);
				
		Clanarina theClanarina = null;
		
		if(result.isPresent()) {
			theClanarina = result.get();
		}
		else {
			throw new RuntimeException("Nema clanarine");
		}
	
		return theClanarina;
	}
	
	public List<Clanarina> findAll(){
		return clanarinaRepository.findAll();
	}
	
	public Clanarina findById(int theId) {
		Optional<Clanarina> result = clanarinaRepository.findById(theId);
		
		Clanarina theClanarina = null;
		
		if(result.isPresent()) {
			theClanarina = result.get();
		}
		else {
			throw new RuntimeException("Nema te clanarine");
		}
		
		
		return theClanarina;
	}
	
	public void deleteById(int theId) {
		clanarinaRepository.deleteById(theId);
	}
	
	
}
