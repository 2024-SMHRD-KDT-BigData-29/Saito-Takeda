// MessageRepository.java
package com.smhrd.basic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByReceiverId(String receiverId);
    List<MessageEntity> findBySenderId(String senderId);
}