package com.springboot.test.taxation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.test.taxation.model.Outgoing;

@Repository
public interface OutgoingRepository extends JpaRepository<Outgoing, Long> {

}
