package com.smhrd.basic.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Spring Transactional로 변경
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    // 기본키인 이메일로 유저 프로필찾기
    @Transactional
    public ProfileDTO findByUserEmail(String userEmail) {
        ProfileEntity entity = profileRepository.findByUserEmail(userEmail);
        if (entity == null) {
            return null;
        }
        return entityToDto(entity);
    }

    // 프로필저장 로직
    @Transactional
    public void save(ProfileDTO profileDTO) {
        // MBTI 결합 및 유효성 검사
        if (profileDTO.getEi() != null && profileDTO.getSn() != null &&
            profileDTO.getFt() != null && profileDTO.getJp() != null) {
            String ei = profileDTO.getEi();
            String sn = profileDTO.getSn();
            String ft = profileDTO.getFt();
            String jp = profileDTO.getJp();
            if (!ei.matches("[EI]") || !sn.matches("[SN]") ||
                !ft.matches("[FT]") || !jp.matches("[JP]")) {
                throw new IllegalArgumentException("유효하지 않은 MBTI 값입니다.");
            }
            profileDTO.setUserMbti(ei + sn + ft + jp);
        } else {
            throw new IllegalArgumentException("모든 MBTI 지표를 선택해야 합니다.");
        }

        // 생활 패턴 유효성 검사
        if (profileDTO.getLifestyle() != null &&
            !profileDTO.getLifestyle().matches("morning|night")) {
            throw new IllegalArgumentException("유효하지 않은 생활 패턴입니다.");
        }

        ProfileEntity entity = ProfileEntity.toProfileEntity(profileDTO);
        profileRepository.save(entity);
    }

    // ProfileEntity를 ProfileDTO로 변환
    private ProfileDTO entityToDto(ProfileEntity entity) {
        return new ProfileDTO(
                entity.getUserEmail(),
                entity.getProfileImg(),
                entity.getUserIntroduction(),
                entity.getUserMbti(),
                entity.getPartnerMbti(),
                entity.getLifestyle(),
                null, // profileFile
                null, null, null, null // ei, sn, ft, jp
        );
    }
    
    //
}