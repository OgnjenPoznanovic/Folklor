package com.eproject.folklor.markovic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Clan;

public interface ClanRepository extends JpaRepository<Clan, Integer>{

	public List<Clan> findAll();
	
	@Query(value = "SELECT * FROM igrac WHERE username = :korisnicko_ime", nativeQuery=true)
	public Optional<Clan> findByUsername(@Param("korisnicko_ime") String korisnicko_ime);
	
	@Query(value = "SELECT * FROM igrac WHERE username = :korisnicko_ime AND ime=:ime AND prezime=:prezime", nativeQuery=true)
	public Optional<Clan> findByUsernameAndImeAndPrezime(@Param("korisnicko_ime") String korisnicko_ime, @Param("ime") String ime, @Param("prezime") String prezime);

	@Query(value = "SELECT * FROM igrac WHERE ime=:ime", nativeQuery = true)
	public List<Clan> findByIme(@Param("ime") String ime);
	
	@Query(value = "SELECT * FROM igrac WHERE prezime =:prezime", nativeQuery = true)
	public List<Clan> findByPrezime(@Param("prezime") String prezime);
	
	@Query(value = "SELECT * FROM igrac WHERE ime =:ime AND prezime =:prezime", nativeQuery = true)
	public List<Clan> findByImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
	
	@Query(value = "SELECT * FROM igrac WHERE ime =:ime AND prezime =:prezime", nativeQuery = true)
	public Clan findOneByImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
	
	@Query(value="SELECT c.* FROM igrac c JOIN pripada p ON c.igrac_id = p.igrac_id JOIN ansambl a ON p.ansambl_id = a.ansambl_id WHERE a.ansambl_id =:ansambl_id", nativeQuery = true)
	public List<Clan> findByAnsamblId(@Param("ansambl_id") Integer ansambl_id);
	
	
	@Query(value="SELECT c.* FROM igrac c JOIN prisustvo p ON c.igrac_id = p.igrac_id JOIN proba pr ON p.proba_id = pr.proba_id WHERE pr.proba_id =:proba_id", nativeQuery = true)
	public List<Clan> findByProbaId(@Param("proba_id") Integer proba_id);
	
	
	
	
}

