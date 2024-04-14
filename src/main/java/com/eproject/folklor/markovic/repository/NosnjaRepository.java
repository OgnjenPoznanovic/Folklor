package com.eproject.folklor.markovic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eproject.folklor.markovic.entity.Nosnja;

public interface NosnjaRepository extends JpaRepository<Nosnja, Integer>{

	public List<Nosnja> findAll();
	
}
