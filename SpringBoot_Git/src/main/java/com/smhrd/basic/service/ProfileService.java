package com.smhrd.basic.service;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public ProfileDTO findByUserEmail(String userEmail) {
        System.out.println("프로필 조회 요청: userEmail = " + userEmail);
        ProfileEntity entity = profileRepository.findByUserEmail(userEmail);
        if (entity == null) {
            System.out.println("프로필 데이터 없음: userEmail = " + userEmail);
            return null;
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserEmail(entity.getUserEmail());
        profileDTO.setUserMbti(entity.getUserMbti());
        profileDTO.setProfileImg(entity.getProfileImg());
        profileDTO.setUserIntroduction(entity.getUserIntroduction());
        profileDTO.setLifestyle(entity.getLifestyle());
        profileDTO.setPartnerMbti(entity.getPartnerMbti());
        System.out.println("프로필 조회 성공: userEmail = " + userEmail + ", userMbti = " + entity.getUserMbti());
        return profileDTO;
    }

    @Transactional
    public void save(ProfileDTO profileDTO) {
        System.out.println("프로필 저장 요청: userEmail = " + profileDTO.getUserEmail());
        ProfileEntity entity = new ProfileEntity();
        entity.setUserEmail(profileDTO.getUserEmail());
        entity.setUserMbti(profileDTO.getUserMbti());
        entity.setProfileImg(profileDTO.getProfileImg());
        entity.setUserIntroduction(profileDTO.getUserIntroduction());
        entity.setLifestyle(profileDTO.getLifestyle());
        entity.setPartnerMbti(profileDTO.getPartnerMbti());
        profileRepository.save(entity);
        System.out.println("프로필 저장 성공: userEmail = " + profileDTO.getUserEmail());
    }
}