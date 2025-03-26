package com.smhrd.basic.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // 프로필 저장 또는 업데이트 
    @Transactional
    public void save(ProfileDTO profileDTO) throws IOException {
        ProfileEntity profileEntity = dtoToEntity(profileDTO);
        if (profileDTO.getProfileFile() != null && !profileDTO.getProfileFile().isEmpty()) {
            String filePath = saveFile(profileDTO.getProfileFile());
            profileEntity.setProfileImg(filePath);
        }
        profileRepository.save(profileEntity);
    }
    
    // 파일 저장 로직
    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedFileName = UUID.randomUUID().toString() + fileExtension;

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File destFile = new File(UPLOAD_DIR + savedFileName);
        file.transferTo(destFile);
        return "/uploads/" + savedFileName;
    }

    // 프로필 조회
    public ProfileDTO findByUserEmail(String userEmail) {
        return profileRepository.findById(userEmail)
                .map(this::entityToDto)
                .orElse(null);
    }

    // DTO -> Entity 변환
    private ProfileEntity dtoToEntity(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setUserEmail(dto.getUserEmail());
        entity.setProfileImg(dto.getProfileImg());
        entity.setUserIntroduction(dto.getUserIntroduction());
        entity.setPartnerMbti(dto.getPartnerMbti());
        return entity;
    }

    // Entity -> DTO 변환
    private ProfileDTO entityToDto(ProfileEntity entity) {
        return new ProfileDTO(
                entity.getUserEmail(),
                entity.getProfileImg(),
                entity.getUserIntroduction(),
                entity.getPartnerMbti(),
                null // <- profileFile 임
        );
    }
}