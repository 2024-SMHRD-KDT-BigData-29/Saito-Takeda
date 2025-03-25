package com.smhrd.basic.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_MESSAGE")
public class Message {
    
    @Id
    @Column(name = "MSG_IDX", nullable = false)
    private int msgIdx;
    
    @Column(name = "SENDER_ID", nullable = true, length = 50)
    private String senderId;
    
    @Column(name = "RECEIVER_ID", nullable = true, length = 50)
    private String receiverId;
    
    @Column(name = "MSG_CONTENT", nullable = true, length = 1000)
    private String msgContent;
    
    @Column(name = "SENDER_AT", nullable = true)
    private Timestamp senderAt;
    
    @Column(name = "IS_READ", nullable = true, length = 1)
    private String isRead;
}