package com.smhrd.basic.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 DTO -> Entity 변환
    public void save(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setUserEmail(userDTO.getUserEmail());
//        entity.setUserIdx(userDTO.getUserIdx());
        entity.setUserPw(userDTO.getUserPw());
        entity.setUserName(userDTO.getUserName());
        entity.setUserGender(userDTO.getUserGender());
        entity.setUserRegnum(userDTO.getUserRegnum());
        entity.setUserAddr(userDTO.getUserAddr());
        entity.setUserNickname(userDTO.getUserNickname());
        userRepository.save(entity);
    }

    // 로그인
    @Transactional
    public UserDTO login(UserDTO userDTO) {
        UserEntity entity = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (entity != null && entity.getUserPw().equals(userDTO.getUserPw())) {
            return new UserDTO(
                    entity.getUserEmail(),
                    entity.getUserPw(),
                    entity.getUserName(),
                    entity.getUserGender(),
                    entity.getUserRegnum(),
                    entity.getUserAddr(),
                    entity.getUserNickname()
            );
        }
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
        UserEntity entity = userRepository.findByUserEmail(userEmail);
        if (entity == null) {
            return null;
        }
        return new UserDTO(
                entity.getUserEmail(),
                entity.getUserPw(),
                entity.getUserName(),
                entity.getUserGender(),
                entity.getUserRegnum(),
                entity.getUserAddr(),
                entity.getUserNickname()
        );
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
                entity.getUserNickname()
        );
    }
}






