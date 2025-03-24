package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
