package com.springboot.test.taxation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.test.taxation.model.Incoming;

@Repository
public interface IncomingRepository extends JpaRepository<Incoming, Long>{

}
