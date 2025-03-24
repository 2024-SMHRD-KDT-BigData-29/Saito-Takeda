package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
	
}

