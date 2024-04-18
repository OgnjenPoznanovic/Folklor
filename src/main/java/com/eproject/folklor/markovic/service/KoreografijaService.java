package com.eproject.folklor.markovic.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Koreografija;
import com.eproject.folklor.markovic.repository.KoreografijaRepository;

@Service
public class KoreografijaService {
	
	private KoreografijaRepository koreografijaRepository;
	
	public KoreografijaService(KoreografijaRepository theKoreografijaRepository) {
	
		koreografijaRepository = theKoreografijaRepository;
	}
	
	public void save(Koreografija theKoreografija) {
		
		koreografijaRepository.save(theKoreografija);
	}
	
	public void deleteById(int theId) {
		koreografijaRepository.deleteById(theId);
	}
	
	
	public List<Koreografija> findAll(){
		
		return koreografijaRepository.findAll();
	}
	
	
	public Koreografija findById(int theId) {
		Optional<Koreografija> result = koreografijaRepository.findById(theId);
		
		Koreografija theKoreografija = null;
		
		if(result.isPresent()) {
			
			theKoreografija = result.get();
		}
		else {
			throw new RuntimeException("Nema te koreografija");
		}
		
		return theKoreografija;
	}
	
	public Koreografija findByNaziv(String naziv) {
		Optional<Koreografija> result = koreografijaRepository.findByNaziv(naziv);
		
		Koreografija theKoreografija = null;
		
		if(result.isPresent()) {
			
			theKoreografija = result.get();
		}
		else {
			throw new RuntimeException("Nema te koreografija");
		}
		
		return theKoreografija;
	}
	
	public List<Koreografija> koreografijeInOneList(List<Koreografija> allKoreografije, List<Koreografija> Koreografije) {
	    Set<Koreografija> set1 = new HashSet<>(allKoreografije);
	    Set<Koreografija> set2 = new HashSet<>(Koreografije);
	
	    Set<Koreografija> symmetricDifference = new HashSet<>(set1);
	    symmetricDifference.addAll(set2); 
	    Set<Koreografija> intersection = new HashSet<>(set1);
	    intersection.retainAll(set2); 
	    symmetricDifference.removeAll(intersection);
	
	    return new ArrayList<>(symmetricDifference);
	}
	
	
	

}
