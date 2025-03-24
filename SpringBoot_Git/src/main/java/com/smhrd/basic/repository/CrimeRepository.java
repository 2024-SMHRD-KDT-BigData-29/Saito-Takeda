package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.CrimeEntity;

public interface CrimeRepository extends JpaRepository<CrimeEntity, Long>{

}
