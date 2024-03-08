package com.eproject.folklor.markovic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eproject.folklor.markovic.entity.Clanarina;

public interface ClanarinaRepository extends JpaRepository<Clanarina, Integer>{
	
	public List<Clanarina> findAll();
	
	
		
	
}
