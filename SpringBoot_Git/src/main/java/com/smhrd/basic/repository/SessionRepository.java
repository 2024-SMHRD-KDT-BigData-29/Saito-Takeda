package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{

}
