package com.smhrd.basic.entity;

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
@Table(name = "TB_USER_PROFILE")
public class Profile {
    
    @Id
    @Column(name = "USER_EMAIL", nullable = false, length = 50)
    private String userEmail;
    
    @Column(name = "PROFILE_IMG", nullable = true, length = 1000)
    private String profileImg;
    
    @Column(name = "USER_INTRODUCTION", nullable = true)
    private String userIntroduction;
    
    @Column(name = "USER_URL", nullable = true, length = 4)
    private String userUrl;
    
    @Column(name = "PARTNER_MBTI", nullable = true, length = 4)
    private String partnerMbti;
}