package com.smhrd.basic.entity;


import com.smhrd.basic.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class UserEntity {
    
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, length = 255)
//    private Integer userIdx;
    
	@Id
    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;
    
    @Column(nullable = false, length = 255)
    private String userPw;
    
    @Column(nullable = false, length = 50)
    private String userName;
    
    @Column(nullable = false, length = 1)
    private String userGender;
    
    @Column(nullable = false, length = 14)
    private String userRegnum;
    
    @Column(nullable = false, length = 500)
    private String userAddr;
    
    @Column(nullable = false, length = 1, columnDefinition = "VARCHAR(1) DEFAULT 'u'")
    private String userRole = "u";
    
    @Column(nullable = false, length = 20)
    private String userNickname;
    
    @Column(nullable = true, length = 3)
    private int userAge;
    
    public static UserEntity toUserEntity(UserDTO userDTO) {
    	UserEntity userEntity = new UserEntity();
    	
//    	userEntity.setUserIdx(0);
    	userEntity.setUserEmail(userDTO.getUserEmail());
    	userEntity.setUserPw(userDTO.getUserPw());
    	userEntity.setUserName(userDTO.getUserName());
    	userEntity.setUserGender(userDTO.getUserGender());
    	userEntity.setUserNickname(userDTO.getUserNickname());
    	userEntity.setUserRegnum(userDTO.getUserRegnum());
    	userEntity.setUserAddr(userDTO.getUserAddr());
    	
    	return userEntity;
    }
    
//    저장된 회원의 인덱스 포함
    public static UserEntity toUpdateUserEntity(UserDTO userDTO) {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserIdx(userDTO.getUserIdx());
    	userEntity.setUserPw(userDTO.getUserPw());
    	userEntity.setUserName(userDTO.getUserName());
    	userEntity.setUserGender(userDTO.getUserGender());
    	userEntity.setUserNickname(userDTO.getUserNickname());
    	userEntity.setUserRegnum(userDTO.getUserRegnum());
    	userEntity.setUserAddr(userDTO.getUserAddr());
    	
    	return userEntity;
    }
    
    //
    
}
