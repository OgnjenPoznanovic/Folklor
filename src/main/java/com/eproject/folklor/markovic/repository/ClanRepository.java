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
	
	@Query(value = "SELECT * FROM igrac WHERE enabled = true ORDER BY godiste ASC ", nativeQuery = true)
	public List<Clan> orderByGodisteASC();
	
	@Query(value = "SELECT * FROM igrac WHERE enabled = true ORDER BY godiste DESC ", nativeQuery = true)
	public List<Clan> orderByGodisteDESC();
	
	@Query(value = "SELECT * FROM igrac WHERE enabled = true ORDER BY godina_upisa ASC ", nativeQuery = true)
	public List<Clan> orderByGodinaUpisaASC();
	
	@Query(value = "SELECT * FROM igrac WHERE enabled = true ORDER BY godina_upisa DESC ", nativeQuery = true)
	public List<Clan> orderByGodinaUpisaDESC();
	
	@Query(value = "SELECT * FROM igrac WHERE prezime =:prezime", nativeQuery = true)
	public List<Clan> findByPrezime(@Param("prezime") String prezime);
	
	@Query(value = "SELECT * FROM igrac WHERE ime =:ime AND prezime =:prezime", nativeQuery = true)
	public List<Clan> findByImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
	
	@Query(value = "SELECT * FROM igrac WHERE ime =:ime AND prezime =:prezime", nativeQuery = true)
	public Clan findOneByImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
	
	@Query(value="SELECT c.* FROM igrac c JOIN pripada p ON c.igrac_id = p.igrac_id JOIN ansambl a ON p.ansambl_id = a.ansambl_id WHERE a.ansambl_id =:ansambl_id AND c.enabled = true", nativeQuery = true)
	public List<Clan> findByAnsamblId(@Param("ansambl_id") Integer ansambl_id);
	
	
	@Query(value="SELECT c.* FROM igrac c JOIN prisustvo p ON c.igrac_id = p.igrac_id JOIN proba pr ON p.proba_id = pr.proba_id WHERE pr.proba_id =:proba_id AND c.enabled = true", nativeQuery = true)
	public List<Clan> findByProbaId(@Param("proba_id") Integer proba_id);
	
	@Query(value="SELECT c.* FROM igrac c JOIN placene_obaveze po ON c.igrac_id = po.igrac_id JOIN clanarina cl ON po.clanarina_id = cl.clanarina_id WHERE cl.clanarina_id =:clanarina_id AND c.enabled = true", nativeQuery = true)
	public List<Clan> findByClanarinaId(@Param("clanarina_id") Integer clanarina_id);
	
	@Query(value="SELECT * FROM igrac WHERE enabled = true", nativeQuery = true)
	public List<Clan> findAllActive();
	
	@Query(value="SELECT c.* FROM igrac c JOIN putuje p ON c.igrac_id = p.igrac_id WHERE c.igrac_id=:clan AND p.nastup_id=:nastup AND enabled = true", nativeQuery = true)
	public Optional<Clan> putuje(@Param("clan") Integer igrac_id, @Param("nastup") Integer nastup_id);
	
	
	
}

