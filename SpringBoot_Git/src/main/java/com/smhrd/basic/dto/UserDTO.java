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
public class UserDTO {

	// 이메일, 비밀번호, 이름, 전화번호, 성별, 주민번호
	// 주소, 계정상태, 구분, 가입일자, 닉네임

    // 사용자 이메일 
    private String userEmail;

    // 사용자 비밀번호 
    private String userPw;

    // 사용자 이름 
    private String userName;

    // 사용자 전화번호 
    private String userPhone;

    // 사용자 성별 
    private String userGender;

    // 사용자 주민번호 
    private String userRegnum;

    // 사용자 주소 
    private String userAddr;

    // 사용자 계정상태 
    private String userStatus;

    // 사용자 구분 
    private String userRole;

    // 가입 일자 
    private Timestamp joinedAt;

    // 사용자 닉네임 
    private String userNickname;


	
}
