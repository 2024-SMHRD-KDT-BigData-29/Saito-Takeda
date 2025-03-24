package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "TB_USER")
public class User {

	// 사용자 
	

    // 사용자 이메일
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userEmail;

    // 사용자 비밀번호
	@Column(nullable = false)
    private String userPw;

    // 사용자 이름
	@Column(nullable = false)
    private String userName;

    // 사용자 전화번호 
	@Column(nullable = false)
    private String userPhone;

    // 사용자 성별 
	@Column(nullable = false)
    private String userGender;

    // 사용자 주민번호 
	@Column(nullable = false)
    private String userRegnum;

    // 사용자 주소 
	@Column(nullable = false)
    private String userAddr;

    // 사용자 계정상태 
	@Column(nullable = false)
    private String userStatus;

    // 사용자 구분 
	@Column(nullable = false)
    private String userRole;

    // 가입 일자 
	@Column(nullable = false)
    private Timestamp joinedAt;

    // 사용자 닉네임 
	@Column(nullable = false, unique = true)
    private String userNickname;

   

	
}
