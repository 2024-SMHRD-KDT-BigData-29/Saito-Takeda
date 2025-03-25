package com.smhrd.basic.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
	
	 // 세션 식별자 
    private int sessionIdx;

    // 유저 이메일 
    private String userEmail;

    // 로그 유형 
    private String logType;

    // 로그 시간 
    private Timestamp logTime;

    // IP 주소 
    private String ipAddr;

    // 기기 정보 
    private String macAddr;


}
