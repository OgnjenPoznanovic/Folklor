package com.eproject.folklor.markovic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Ansambl;

public interface AnsamblRepository extends JpaRepository<Ansambl, Integer>{

	public List<Ansambl> findAll();

	@Query(value = "SELECT * FROM ansambl WHERE naziv = :naziv", nativeQuery=true)
	public Optional<Ansambl> findByNaziv(@Param("naziv") String naziv);
	
	
}
