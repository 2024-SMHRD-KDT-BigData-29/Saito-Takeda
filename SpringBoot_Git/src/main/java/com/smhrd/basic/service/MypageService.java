package com.smhrd.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.MypageRepository;
import com.smhrd.basic.repository.UserRepository;

@Service
public class MypageService {
	
	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private MypageRepository mypageRepository;

    // 로그인한 사용자의 프로필 정보 조회
    public ProfileDTO findProfileByUserEmail(String userEmail) {
        ProfileEntity profileEntity = mypageRepository.findByUserEmail(userEmail);
        if (profileEntity == null) {
            return null;
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserEmail(profileEntity.getUserEmail());
        profileDTO.setUserMbti(profileEntity.getUserMbti());
//        profileDTO.setUserGender(profileEntity.getUserGender());
//        profileDTO.setUserAge(profileEntity.getUserAge());
        profileDTO.setProfileImg(profileEntity.getProfileImg());
        return profileDTO;
    }

    // 로그인한 사용자의 유저 정보 조회
    public UserDTO findUserByEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPw(userEntity.getUserPw());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserGender(userEntity.getUserGender());
        userDTO.setUserNickname(userEntity.getUserNickname());
        userDTO.setUserRegnum(userEntity.getUserRegnum());
        userDTO.setUserAddr(userEntity.getUserAddr());
//        userDTO.setUserRole(userEntity.getUserRole());
        userDTO.setUserAge(userEntity.getUserAge());
        return userDTO;
    }

    // 유저 정보 업데이트
    public void updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (userEntity != null) {
            userEntity.setUserPw(userDTO.getUserPw());
            userEntity.setUserName(userDTO.getUserName());
            userEntity.setUserGender(userDTO.getUserGender());
            userEntity.setUserNickname(userDTO.getUserNickname());
            userEntity.setUserRegnum(userDTO.getUserRegnum());
            userEntity.setUserAddr(userDTO.getUserAddr());
            userEntity.setUserAge(userDTO.getUserAge());
            userRepository.save(userEntity);
        }
    }
//
}
