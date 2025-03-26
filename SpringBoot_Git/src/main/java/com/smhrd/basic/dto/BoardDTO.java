package com.smhrd.basic.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//트러블슈팅 bIdx 변수명 <- JPA에 안맞아서 소문자로 다른컬럼들도 bidx 와 같이 다 소문자로 변경

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
	
    // 글 식별자 
    private int bidx;

    // 글 유형 
    private String btype;

    // 글 제목 
    private String btitle;

    // 글 내용 
    private String bcontent;

    // 글 첨부파일 
    // 파일 경로를 저장 (예: "/uploads/filename.jpg")
    // Entity에는 저장 XXX
    private String bfile;
    
 // 파일 업로드를 위한 필드 추가
    private MultipartFile file; // 폼에서 파일을 받을 때 사용

    // 글 작성일자 
    private Timestamp createdAt;

    // 글 조회수 
    private Integer bviews;

    // 글 좋아요수 
    private Integer blikes;

    // 글 작성자 
    private String bwriter;


}
