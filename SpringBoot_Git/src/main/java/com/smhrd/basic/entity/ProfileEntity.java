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
@Table(name = "tb_profile")
public class ProfileEntity {
	
    @Id
    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;
    
    @Column(name = "profile_img", nullable = true, length = 1000)
    private String profileImg;
    
    @Column(name = "user_introduction", nullable = true)
    private String userIntroduction;
    
    @Column(name = "user_url", nullable = true, length = 4)
    private String userUrl;
    
    @Column(name = "partner_mbti", nullable = true, length = 4)
    private String partnerMbti;
}