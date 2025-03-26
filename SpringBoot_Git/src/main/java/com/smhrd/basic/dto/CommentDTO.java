package com.smhrd.basic.dto;

import java.sql.Timestamp;

import com.smhrd.basic.entity.CommentEntity;

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
public class CommentDTO {
	
	// 댓글 식별자 
    private int cmtIdx;

    // 원글 식별자 
    private int bidx;

    // 댓글 내용 
    private String cmtContent;

    // 댓글 작성일자 
    private Timestamp createdAt;

    // 댓글 작성자 
    private String userEmail;
    
    
    
    public CommentDTO toDto(CommentEntity entity) {
        return new CommentDTO(
            entity.getCmtIdx(),
            entity.getBidx(),
            entity.getCmtContent(),
            entity.getCreatedAt(),
            entity.getUserEmail()
        );
    }

    
	
}
