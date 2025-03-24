package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
