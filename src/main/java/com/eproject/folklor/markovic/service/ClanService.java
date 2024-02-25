package com.eproject.folklor.markovic.service;

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
	

}
