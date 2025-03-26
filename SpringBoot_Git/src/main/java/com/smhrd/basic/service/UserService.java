package com.smhrd.basic.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//		userDTO를 담아서 userEntity에 저장 
		UserEntity userEntity = UserEntity.toUserEntity(userDTO);
		userRepository.save(userEntity);
		// repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
		
	}

	public UserDTO login(UserDTO userDTO) {
		/*
		 * 1. 회원이 입력한 이메일로 DB에서 조회를 함
		 * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단		
		 */
		Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());
		if(byUserEmail.isPresent()) {
//			조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
			UserEntity userEntity = byUserEmail.get();
			if(userEntity.getUserPw().equals(userDTO.getUserPw())) {
//				비밀번호 일치
//				entity -> dto 변환 후 리턴
				UserDTO dto = UserDTO.toUserDTO(userEntity);
				return dto;
			}else {
//				비밀번호 불일치 ( 로그인 실패 )
//				불일치시 null을 반환해서 Controller에서 조건문에 의해 "login" 페이지 리턴
				return null;
			}
			
		}else {
//			조회 결과가 없다(해당 이메일을 가진 회원이 없다.) 
			return null;
		}
		
	}

	public List<UserDTO> findAll() {
		List<UserEntity> userEntityList = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		for (UserEntity userEntity: userEntityList) {
			userDTOList.add(UserDTO.toUserDTO(userEntity));
//			UserDTO userDTO = UserDTO.toUserDTO(userEntity);
//			userDTOList.add(userDTO);
			
		}
		
		return userDTOList;
	}

	public UserDTO findByUserIdx(Integer userIdx) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserIdx(userIdx);
		if(optionalUserEntity.isPresent()) {
			
//			UserEntity userEntity = optionalUserEntity.get();
//			UserDTO userDTO = UserDTO.toUserDTO(userEntity);
//			return userDTO
//			위의 코드 3줄을 아래 한줄로 요약한것
			return UserDTO.toUserDTO(optionalUserEntity.get());
		}else {
			return null;
		}
		
	
	}

	public UserDTO mypageUpdateForm(String myEmail) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmail(myEmail);
		if(optionalUserEntity.isPresent()) {
			return UserDTO.toUserDTO(optionalUserEntity.get());
		}else {
			return null;
		}
	
	}

	public void update(UserDTO userDTO) {
		userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
	}

	public UserDTO findByUserEmail(String userEmail) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmail(userEmail);
		return UserDTO.toUserDTO(optionalUserEntity.get());
	}

    
 
}