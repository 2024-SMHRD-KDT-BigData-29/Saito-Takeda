package com.smhrd.basic.service;


import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
		
	private final UserRepository userRepository;
	
	public void save(UserDTO userDTO) {
		// 1. dto -> entity 변환
		// 2. repository의 save 메서드 호출
		UserEntity userEntity = UserEntity.toMemberEntity(userDTO);
		userRepository.save(userEntity);
		// repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
		
		
		
		
	}

    
 
}