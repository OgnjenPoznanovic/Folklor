package com.eproject.folklor.markovic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eproject.folklor.markovic.entity.Uloge;

public interface UlogeRepository extends JpaRepository<Uloge, String>{

	
	@Query(value="SELECT * FROM uloge WHERE username =:username", nativeQuery = true)
	public Optional<Uloge> findByUsername(@Param("username") String username);
}
