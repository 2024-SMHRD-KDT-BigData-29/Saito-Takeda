package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{

}
