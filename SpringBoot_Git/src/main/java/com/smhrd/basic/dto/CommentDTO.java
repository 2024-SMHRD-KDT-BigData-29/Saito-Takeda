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
public class CommentDTO {
	
	// 댓글 식별자 
    private int cmtIdx;

    // 원글 식별자 
    private int bIdx;

    // 댓글 내용 
    private String cmtContent;

    // 댓글 작성일자 
    private Timestamp createdAt;

    // 댓글 작성자 
    private String userEmail;

    
	
}
