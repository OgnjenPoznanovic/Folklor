package com.eproject.folklor.markovic.service;

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
	
}
