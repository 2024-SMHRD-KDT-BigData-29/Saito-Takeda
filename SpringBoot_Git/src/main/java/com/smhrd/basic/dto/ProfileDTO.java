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
    
//	    @Column(nullable = true, length = 4)
//	    private String userUrl;
    
    private String partnerMbti;
	
    private MultipartFile profileFile; // 업로드용 필드
	
}
