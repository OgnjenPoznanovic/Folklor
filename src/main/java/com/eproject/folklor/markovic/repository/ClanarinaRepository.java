package com.eproject.folklor.markovic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Ansambl;
import com.eproject.folklor.markovic.entity.Clanarina;

public interface ClanarinaRepository extends JpaRepository<Clanarina, Integer>{
	
	public List<Clanarina> findAll();
	
	@Query(value = "SELECT * FROM clanarina WHERE mesec =:mesec AND godina=:godina AND cena=:cena", nativeQuery = true)
	public Optional<Clanarina> findClanarina(@Param("mesec") String mesec, @Param("godina") String godina, @Param("cena") int cena);

	
	@Query(value="SELECT * FROM clanarina c WHERE c.mesec =:mesec AND c.godina =:godina", nativeQuery = true)
	public List<Clanarina> findByMesecAndGodina(@Param("mesec") String mesec, @Param("godina") String godina);
		
	
}
