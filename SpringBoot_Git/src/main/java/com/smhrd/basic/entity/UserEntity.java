package com.smhrd.basic.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import com.smhrd.basic.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class UserEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", nullable = false, length = 255)
    private Integer userIdx;
    
    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;
    
    @Column(name = "user_pw", nullable = false, length = 255)
    private String userPw;
    
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    
//    @Column(name = "user_phone", nullable = true, length = 20)
//    private String userPhone;
    
    @Column(name = "user_gender", nullable = true, length = 1)
    private String userGender;
    
    @Column(name = "user_regnum", nullable = true, length = 14)
    private String userRegnum;
    
    @Column(name = "user_addr", nullable = true, length = 500)
    private String userAddr;
    
//    @Column(name = "user_status", nullable = true, length = 10)
//    private String userStatus;
    
    @Column(name = "user_role", nullable = true, length = 1)
    @ColumnDefault("u")
    private String userRole;
    
//    @Column(name = "joined_at", nullable = true)
//    private Timestamp joinedAt;
    
    @Column(name = "user_nickname", nullable = true)
    private String userNickname;
    
//    인덱스 알아서 생성해서 빠짐
    public static UserEntity toUserEntity(UserDTO userDTO) {
    	UserEntity userEntity = new UserEntity();
    	
    	userEntity.setUserIdx(0);
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
    	userEntity.setUserIdx(userDTO.getUserIdx());
    	userEntity.setUserPw(userDTO.getUserPw());
    	userEntity.setUserName(userDTO.getUserName());
    	userEntity.setUserGender(userDTO.getUserGender());
    	userEntity.setUserNickname(userDTO.getUserNickname());
    	userEntity.setUserRegnum(userDTO.getUserRegnum());
    	userEntity.setUserAddr(userDTO.getUserAddr());
    	
    	return userEntity;
    }
    
}
