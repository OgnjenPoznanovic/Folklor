package com.eproject.folklor.markovic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eproject.folklor.markovic.entity.Ansambl;

public interface AnsamblRepository extends JpaRepository<Ansambl, Integer>{

	public List<Ansambl> findAll();
}
