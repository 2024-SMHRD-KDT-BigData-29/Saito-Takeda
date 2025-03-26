package com.smhrd.basic.service;


import java.util.List;
import java.util.Optional;
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

    // 회원가입
    @Transactional
    public void save(UserDTO userDTO) {
        UserEntity userEntity = dtoToEntity(userDTO);
        userRepository.save(userEntity);
    }

    // 로그인
    public UserDTO login(UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            if (userEntity.getUserPw().equals(userDTO.getUserPw())) {
                return entityToDto(userEntity);
            }
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
    public UserDTO findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .map(this::entityToDto)
                .orElse(null);
    }

    // DTO -> Entity 변환
    private UserEntity dtoToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserIdx(dto.getUserIdx());
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserPw(dto.getUserPw());
        entity.setUserName(dto.getUserName());
        entity.setUserGender(dto.getUserGender());
        entity.setUserRegnum(dto.getUserRegnum());
        entity.setUserAddr(dto.getUserAddr());
        entity.setUserNickname(dto.getUserNickname());
        entity.setUserRole("u"); // 기본값 설정
        return entity;
    }

    // Entity -> DTO 변환
    private UserDTO entityToDto(UserEntity entity) {
        return new UserDTO(
                entity.getUserEmail(),
                entity.getUserIdx(),
                entity.getUserPw(),
                entity.getUserName(),
                entity.getUserGender(),
                entity.getUserRegnum(),
                entity.getUserAddr(),
                entity.getUserNickname()
        );
    }
}






