package com.eproject.folklor.markovic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eproject.folklor.markovic.entity.Nastup;

public interface NastupRepository extends JpaRepository<Nastup, Integer>{

	public List<Nastup> findAll();
	
}
