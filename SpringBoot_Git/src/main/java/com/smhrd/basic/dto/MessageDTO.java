package com.smhrd.basic.dto;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDTO {

	// 메시지 식별자
	private int msgIdx;
	
	// 발신자 이메일
	private String senderId;
	
	// 수신자 이메일
	private String receiverId;
	
	// 메시지 내용
	private String msgContent;
	
	// 전송 날짜
	//private Timestamps sendedAt;
	
	// 읽음 여부
	private String isRead;
	
	//
}
