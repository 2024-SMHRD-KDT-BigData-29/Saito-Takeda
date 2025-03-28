package com.smhrd.basic.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
    public UserDTO login(UserDTO userDTO) {
        UserEntity entity = userRepository.findByUserEmailAndUserPw(userDTO.getUserEmail(), userDTO.getUserPw());
        if (entity != null) {
            System.out.println("로그인 성공 이메일 : " + userDTO.getUserEmail());
            return entityToDto(entity);
        } else {
            System.out.println("로그인 실패 이메일 : " + userDTO.getUserEmail());
            return null;
        }
    }

    // 전체 유저 조회
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // 이메일로 유저 조회
    public UserDTO findByUserEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail).orElse(null);
        UserDTO userDTO = null;
        if (userEntity != null) {
//            entityManager.refresh(userEntity);
            userDTO = entityToDto(userEntity);
        }
        System.out.println("UserService - findByUserEmail - userEmail: " + userEmail);
        System.out.println("UserService - findByUserEmail - userDTO: " + userDTO);
        if (userDTO != null) {
            System.out.println("UserService - findByUserEmail - userNickname: " + userDTO.getUserNickname());
        }
        return userDTO;
    }

//    // DTO -> Entity 변환
//    private UserEntity dtoToEntity(UserDTO dto) {
//        UserEntity entity = new UserEntity();
////        entity.setUserIdx(dto.getUserIdx());
//        entity.setUserEmail(dto.getUserEmail());
//        entity.setUserPw(dto.getUserPw());
//        entity.setUserName(dto.getUserName());
//        entity.setUserGender(dto.getUserGender());
//        entity.setUserRegnum(dto.getUserRegnum());
//        entity.setUserAddr(dto.getUserAddr());
//        entity.setUserNickname(dto.getUserNickname());
//        entity.setUserRole("u"); // 기본값 설정
//        return entity;
//    }

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






