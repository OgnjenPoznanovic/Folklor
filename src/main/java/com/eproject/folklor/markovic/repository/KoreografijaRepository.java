package com.eproject.folklor.markovic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eproject.folklor.markovic.entity.Koreografija;

public interface KoreografijaRepository extends JpaRepository<Koreografija, Integer> {

	public List<Koreografija> findAll();
	
	
}
