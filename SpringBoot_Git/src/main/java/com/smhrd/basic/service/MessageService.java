// MessageService.java
package com.smhrd.basic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.MessageDTO;
import com.smhrd.basic.entity.MessageEntity;
import com.smhrd.basic.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageDTO> getMessagesByReceiver(String receiverId) {
        return messageRepository.findByReceiverId(receiverId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<MessageDTO> getMessagesBySender(String senderId) {
        return messageRepository.findBySenderId(senderId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public MessageDTO getMessage(int msgIdx) {
        MessageEntity entity = messageRepository.findById(msgIdx)
            .orElseThrow(() -> new RuntimeException("Message not found"));
        return convertToDTO(entity);
    }

    public void sendMessage(MessageDTO messageDTO) {
        MessageEntity entity = convertToEntity(messageDTO);
        messageRepository.save(entity);
    }

    public void updateMessage(MessageDTO messageDTO) {
        MessageEntity entity = convertToEntity(messageDTO);
        messageRepository.save(entity);
    }
    
    public void deleteMessage(int msgIdx, String userEmail) {
        MessageEntity entity = messageRepository.findById(msgIdx)
            .orElseThrow(() -> new RuntimeException("Message not found"));
        // 발신자 또는 수신자만 삭제 가능
        if (!entity.getSenderId().equals(userEmail) && !entity.getReceiverId().equals(userEmail)) {
            throw new RuntimeException("메시지를 삭제할 권한이 없습니다.");
        }
        messageRepository.delete(entity);
    }

    private MessageDTO convertToDTO(MessageEntity entity) {
        return new MessageDTO(
            entity.getMsgIdx(),
            entity.getSenderId(),
            entity.getReceiverId(),
            entity.getMsgContent(),
            entity.getIsRead(),
            null, // senderProfileImg은 컨트롤러에서 설정
            null  // receiverProfileImg은 컨트롤러에서 설정
        );
    }

    private MessageEntity convertToEntity(MessageDTO dto) {
        MessageEntity entity = new MessageEntity();
        entity.setMsgIdx(dto.getMsgIdx());
        entity.setSenderId(dto.getSenderId());
        entity.setReceiverId(dto.getReceiverId());
        entity.setMsgContent(dto.getMsgContent());
        entity.setIsRead(dto.getIsRead());
        return entity;
    }
    

}