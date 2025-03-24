package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
}

