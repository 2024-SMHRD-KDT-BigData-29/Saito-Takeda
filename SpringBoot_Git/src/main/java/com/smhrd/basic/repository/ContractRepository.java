package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.ContractEntity;

public interface ContractRepository extends JpaRepository<ContractEntity, Long>{

}
