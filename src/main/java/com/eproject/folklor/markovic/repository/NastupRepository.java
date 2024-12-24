package com.eproject.folklor.markovic.repository;

import java.util.List;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Clan;
import com.eproject.folklor.markovic.entity.Nastup;

public interface NastupRepository extends JpaRepository<Nastup, Integer>{

	public List<Nastup> findAll();
	
	@Query(value="SELECT n.* FROM nastup n JOIN putuje p ON n.nastup_id = p.nastup_id WHERE p.igrac_id=:igrac_id ORDER BY n.nastup_id DESC LIMIT 3 ", nativeQuery = true)
	public List<Nastup> findLast3(@Param("igrac_id") Integer igrac_id);
	
	@Query(value="SELECT n.* FROM nastup n WHERE n.mesto=:place", nativeQuery = true)
	public Nastup findByPlace(@Param("place") String place);

	@Query(value="SELECT p.igrac_id FROM putuje p JOIN nastup n ON p.nastup_id = n.nastup_id WHERE p.nastup_id =:nastupId", nativeQuery = true)
	public List<Integer> findAllClanInfoByNastupId(@Param("nastupId") Integer nastupId);

	
}
