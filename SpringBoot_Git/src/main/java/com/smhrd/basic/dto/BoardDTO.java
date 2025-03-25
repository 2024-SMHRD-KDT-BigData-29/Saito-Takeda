package com.smhrd.basic.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
    // 글 식별자 
    private int bIdx;

    // 글 유형 
    private String bType;

    // 글 제목 
    private String bTitle;

    // 글 내용 
    private String bContent;

    // 글 첨부파일 
    private String bFile;

    // 글 작성일자 
    private Timestamp createdAt;

    // 글 조회수 
    private Integer bViews;

    // 글 좋아요수 
    private Integer bLikes;

    // 글 작성자 
    private String userEmail;


}
