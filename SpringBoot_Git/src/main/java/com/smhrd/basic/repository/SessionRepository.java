package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Long>{

}
