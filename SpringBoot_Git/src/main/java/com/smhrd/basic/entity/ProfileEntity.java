package com.smhrd.basic.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_profile")
public class ProfileEntity {
	
    @Id
    @Column(nullable = false, length = 50)
    private String userEmail;
    
    @Column(nullable = true, length = 1000)
    private String profileImg;
    
    @Column(nullable = true)
    private String userIntroduction;
    
//    @Column(nullable = true, length = 4)
//    private String userUrl;
    
    @Column(nullable = true, length = 4)
    private String partnerMbti;
}