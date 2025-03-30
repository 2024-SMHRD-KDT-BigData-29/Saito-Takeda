package com.smhrd.basic.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.ProfileRepository;
import com.smhrd.basic.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileRepository profileRepository;
    
    

 // 회원가입 DTO -> Entity 변환
    @Transactional
    public void save(UserDTO userDTO) {
        System.out.println("회원가입 시작: userEmail = " + userDTO.getUserEmail());
        // tb_user에 사용자 정보 저장
        UserEntity entity = new UserEntity();
        entity.setUserEmail(userDTO.getUserEmail());
        entity.setUserPw(userDTO.getUserPw());
        entity.setUserName(userDTO.getUserName());
        entity.setUserGender(userDTO.getUserGender());
        entity.setUserRegnum(userDTO.getUserRegnum());
        entity.setUserAddr(userDTO.getUserAddr());
        entity.setUserNickname(userDTO.getUserNickname());
        entity.setUserAge(userDTO.getUserAge());
        userRepository.save(entity);
        System.out.println("tb_user 저장 성공: userEmail = " + userDTO.getUserEmail());

        // tb_profile에 기본 프로필 생성
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setUserEmail(userDTO.getUserEmail());
        profileEntity.setUserMbti("");
        profileEntity.setProfileImg("");
        profileEntity.setUserIntroduction("");
        profileEntity.setLifestyle("");
        profileEntity.setPartnerMbti("");
        if (profileRepository == null) {
            throw new IllegalStateException("ProfileRepository is not initialized!");
        }
        profileRepository.save(profileEntity);
        System.out.println("tb_profile 저장 성공: userEmail = " + userDTO.getUserEmail());
    }

 // 로그인
 // 로그인
    @Transactional
    public UserDTO login(UserDTO userDTO) {
        System.out.println("로그인 요청: userEmail = " + userDTO.getUserEmail());
        UserEntity entity = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (entity != null && entity.getUserPw().equals(userDTO.getUserPw())) {
            System.out.println("로그인 성공: userEmail = " + userDTO.getUserEmail());
            return new UserDTO(
                    entity.getUserEmail(),
                    entity.getUserPw(),
                    entity.getUserName(),
                    entity.getUserGender(),
                    entity.getUserRegnum(),
                    entity.getUserAddr(),
                    entity.getUserNickname(),
                    entity.getUserRole(), 
                    entity.getUserAge()
            );
        }
        System.out.println("로그인 실패: userEmail = " + userDTO.getUserEmail());
        return null;
    }

    // 전체 유저 조회
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

 // 이메일로 유저 조회
    @Transactional
    public UserDTO findByUserEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPw(userEntity.getUserPw());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserGender(userEntity.getUserGender());
        userDTO.setUserRegnum(userEntity.getUserRegnum());
        userDTO.setUserAddr(userEntity.getUserAddr());
        userDTO.setUserRole(userEntity.getUserRole());
        userDTO.setUserNickname(userEntity.getUserNickname());
        userDTO.setUserAge(userEntity.getUserAge());
        return userDTO;
    }


    // Entity -> DTO 변환
    private UserDTO entityToDto(UserEntity entity) {
        return new UserDTO(
                entity.getUserEmail(),
//                entity.getUserIdx(),
                entity.getUserPw(),
                entity.getUserName(),
                entity.getUserGender(),
                entity.getUserRegnum(),
                entity.getUserAddr(),
                entity.getUserNickname(),
                entity.getUserRole(),
                entity.getUserAge()
        );
    }
    //

}






