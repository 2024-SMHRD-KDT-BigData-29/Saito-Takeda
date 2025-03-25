package com.smhrd.basic.entity;

import java.sql.Timestamp;

import com.smhrd.basic.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class UserEntity {
    
    @Id
    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;
    
    @Column(name = "USER_PW", nullable = false, length = 255)
    private String userPw;
    
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;
    
    @Column(name = "USER_PHONE", nullable = true, length = 20)
    private String userPhone;
    
    @Column(name = "USER_GENDER", nullable = true, length = 1)
    private String userGender;
    
    @Column(name = "USER_REGION", nullable = true, length = 4)
    private String userRegion;
    
    @Column(name = "USER_ADDR", nullable = true, length = 500)
    private String userAddr;
    
    @Column(name = "USER_STATUS", nullable = true, length = 10)
    private String userStatus;
    
    @Column(name = "USER_ROLE", nullable = true, length = 1)
    private String userRole;
    
    @Column(name = "JOINED_AT", nullable = true)
    private Timestamp joinedAt;
    
    public static UserEntity toMemberEntity(UserDTO userDTO) {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
//    	userEntity.setUserEmail(userDTO.getUserEmail());
    	
    	
    	
    	return userEntity;
    }
    
}
