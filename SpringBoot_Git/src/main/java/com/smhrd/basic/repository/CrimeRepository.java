package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Long>{

}
