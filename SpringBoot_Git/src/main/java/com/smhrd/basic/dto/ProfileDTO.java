package com.smhrd.basic.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDTO {

    private String userEmail;
    
    private String profileImg;
    
    private String userIntroduction;
    
    private String userMbti;
    
    private String lifestyle; // 생활 패턴 추가
    
//	    @Column(nullable = true, length = 4)
//	    private String userUrl;
    
    private String partnerMbti;
	
    private MultipartFile profileFile; // 업로드용 필드
    
 // MBTI 입력을 위한 임시 필드
    private String ei; // E 또는 I
    private String sn; // S 또는 N
    private String ft; // F 또는 T
    private String jp; // J 또는 P
    
    //
    
 // userEmail만 받는 생성자
    public ProfileDTO(String userEmail) {
        this.userEmail = userEmail;
    }
	
}
