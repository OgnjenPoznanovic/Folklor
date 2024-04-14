package com.eproject.folklor.markovic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Koreografija;

public interface KoreografijaRepository extends JpaRepository<Koreografija, Integer> {

	public List<Koreografija> findAll();
	
	@Query(value="SELECT * FROM koreografija WHERE naziv=:naziv", nativeQuery = true)
	public Optional<Koreografija> findByNaziv(@Param("naziv") String naziv);
}
