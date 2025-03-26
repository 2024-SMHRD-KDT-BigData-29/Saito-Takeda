package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_message")
public class MessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
	private int msgIdx;
	
	@Column(nullable = true, length = 50)
	private String senderId;
	
	@Column(nullable = true, length = 50)
	private String receiverId;
	
	@Column(nullable = true, length = 1000)
	private String msgContent;
	
	//@Column(nullable = false)
	//private Timestamps sendedAt;
	
	@Column(nullable = true, length = 1)
	private String isRead;
}
