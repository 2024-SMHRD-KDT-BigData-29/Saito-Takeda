package com.smhrd.basic.entity;


import com.smhrd.basic.dto.ProfileDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileEntity {

    @Id
    @Column(name = "user_email", length = 50)
    private String userEmail;

    @Column(name = "profile_img", length = 255)
    private String profileImg;

    @Column(name = "user_introduction", length = 500)
    private String userIntroduction;
    
    @Column(name = "user_mbti", length = 4)
    private String userMbti;

    @Column(name = "partner_mbti", length = 4)
    private String partnerMbti;

    @Column(name = "lifestyle", length = 10) // 아침형/올빼미형
    private String lifestyle;

    public static ProfileEntity toProfileEntity(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setUserEmail(profileDTO.getUserEmail());
        profileEntity.setProfileImg(profileDTO.getProfileImg());
        profileEntity.setUserIntroduction(profileDTO.getUserIntroduction());
        profileEntity.setUserMbti(profileDTO.getUserMbti());
        profileEntity.setPartnerMbti(profileDTO.getPartnerMbti());
        profileEntity.setLifestyle(profileDTO.getLifestyle());
        return profileEntity;
    }
    //
}