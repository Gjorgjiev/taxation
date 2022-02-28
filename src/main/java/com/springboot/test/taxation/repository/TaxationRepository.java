package com.springboot.test.taxation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.test.taxation.model.Taxation;

@Repository
public interface TaxationRepository extends JpaRepository<Taxation, Long>{
	

}
