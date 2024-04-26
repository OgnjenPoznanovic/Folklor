package com.eproject.folklor.markovic.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.repository.ClanRepository;

@Service
public class ClanService {
	
	private ClanRepository clanRepository;
	
	
	public ClanService(ClanRepository theClanRepository) {
		clanRepository = theClanRepository;
	}
	
	public void save(Clan theClan) { 
		
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		String password = theClan.getPassword();
		String bCryptPassword = bCryptPasswordEncoder.encode(password);
		
		String passwordType = "{bcrypt}";
		
		bCryptPassword = passwordType + bCryptPassword;
		theClan.setPassword(bCryptPassword);
		
		clanRepository.save(theClan);
		
	}
	
	public void update(Clan theClan) {
		
		clanRepository.save(theClan);
		
	}
	
	public List<Clan> findAll(){
		
		return clanRepository.findAll();
	}
	
	public List<Clan> findAllActive(){
		
		List<Clan> clanovi = clanRepository.findAllActive();
		
		return clanovi;
	}
	
	
	public Clan findByUsername(String korisnickoIme) {
		
		Optional<Clan> result  = clanRepository.findByUsername(korisnickoIme);
		
	
		
		
		Clan theClan = null;
		
		if(result.isPresent()) {
			
			theClan = result.get();
		}
		else {
			throw new RuntimeException("Nema tog Clana");
		}
		
		
		
		return theClan;
	
	}
	
	
	public Clan findById(int theId) {
		
		Optional<Clan> result = clanRepository.findById(theId);
		
		Clan theClan = null;
		
		if(result.isPresent()){
			
			theClan = result.get();
		}else {
			
			throw new RuntimeException("Nema tog clana");
		}
		
		return theClan;
	}
	
	public Clan findByUsernameAndImeAndPrezime(String korisnickoIme, String ime, String prezime) {
		
		Optional<Clan> result  = clanRepository.findByUsernameAndImeAndPrezime(korisnickoIme, ime, prezime);
		
	
		
		
		Clan theClan = null;
		
		if(result.isPresent()) {
			
			theClan = result.get();
		}
		else {
			throw new RuntimeException("Nema tog Clana");
		}
		
		
		
		return theClan;
	
	}
	
	public List<Clan>  findByIme(String ime) {
			
			List<Clan> result  = clanRepository.findByIme(ime);
			
		
			
			
			List<Clan> theClanovi = null;
			
			if(result.isEmpty()) {
				
				throw new RuntimeException("Nema clana sa tim imenom");
				
			}
			else {
				theClanovi = result;
			}
			
			
			
			return theClanovi;
		
		}
	
	

	public List<Clan>  findByPrezime(String prezime) {
		
		List<Clan> result  = clanRepository.findByPrezime(prezime);
		
		
		
		
		List<Clan>  theClanovi = null;
		
		if(result.isEmpty()) {
			
			throw new RuntimeException("Nema clana sa tim prezimenom");
		}
		else {
			
			theClanovi = result;
		}
		
		
		
		return theClanovi;
	
	}
	
	public List<Clan> findByImeAndPrezime(String ime, String prezime) {
		
		List<Clan> result  = clanRepository.findByImeAndPrezime(ime, prezime);
		
	
		
		
		List<Clan> theClanovi = null;
		
		if(result.isEmpty()) {
			
			throw new RuntimeException("Nema clana sa tim imenom i prezimenom");
			
		}
		else {
			theClanovi = result;
		}
		
		
		
		return theClanovi;
	
	}
	
	public Clan findOneByImeAndPrezime(String ime, String prezime) {
		
		Clan theClan  = clanRepository.findOneByImeAndPrezime(ime, prezime);
		
	
		
		
		if(theClan == null) {
			throw new RuntimeException("Nema clana sa tim imenom i prezimenom");
		}
		
		
		return theClan;
	
	}
	
	
	public List<Clan> findByAnsamblId(Integer theId){
		
		List<Clan> result = clanRepository.findByAnsamblId(theId);
		
		List<Clan> theClanovi = null;
		
		if(result.isEmpty()) {
			
			throw new RuntimeException("Nema igraca u tom ansamblu");
			
		}else {
			
			theClanovi = result;
		}
		
		
		
		return theClanovi;
	}
	
	
public List<Clan> findByProbaId(Integer theId){
		
		List<Clan> result = clanRepository.findByProbaId(theId);
		
		List<Clan> theClanovi = null;
		
		if(result.isEmpty()) {
			
			return theClanovi;
			
		}else {
			
			theClanovi = result;
		}
			
		return theClanovi;
	}


public List<Clan> findByClanarinaId(Integer theId){
	
	List<Clan> result = clanRepository.findByClanarinaId(theId);
	
	List<Clan> theClanovi = null;
	
	if(result.isEmpty()) {
		
		return theClanovi;
		
	}else {
		
		theClanovi = result;
	}
		
	return theClanovi;
}
	

	public List<Clan> clanoviInOneList(List<Clan> allClanoviAnsambla, List<Clan> prisutniClanovi) {
	    Set<Clan> set1 = new HashSet<>(allClanoviAnsambla);
	    Set<Clan> set2 = new HashSet<>(prisutniClanovi);
	
	    Set<Clan> symmetricDifference = new HashSet<>(set1);
	    symmetricDifference.addAll(set2); 
	    Set<Clan> intersection = new HashSet<>(set1);
	    intersection.retainAll(set2); 
	    symmetricDifference.removeAll(intersection);
	
	    return new ArrayList<>(symmetricDifference);
	}
	
	
	public void deleteById(int theId) {
		
		clanRepository.deleteById(theId);
	}
	

	public Boolean putuje(Integer igrac_id, Integer nastup_id) {
		
		Optional<Clan> result  = clanRepository.putuje(igrac_id, nastup_id);
		
		Boolean temp;
		
		if(result.isPresent()) {
			
			temp = true;
		}else {
		
			temp = false;
		}
				
		return temp;
	
	}
	
	
	public int changePassword(String oldPassword, String newPassword, String newPassword1, Clan theClan) { 
		
		
		String dbPassword = theClan.getPassword();
		String passwordType = "{bcrypt}";
		
		String newString = dbPassword.substring(8); // Extract substring starting from index 8
		
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		
		if(BCrypt.checkpw(oldPassword, newString)) {
			
			if(newPassword.equals(newPassword1)) {	
				
				String bCryptPassword = bCryptPasswordEncoder.encode(newPassword);
				bCryptPassword = passwordType + bCryptPassword;
				theClan.setPassword(bCryptPassword);
		
				update(theClan);
				
				return 1;
				
			}else {
					
			
				return 0;
			}
		}else {
				
			
			return 0;
		}
		
		
		
	}







}

