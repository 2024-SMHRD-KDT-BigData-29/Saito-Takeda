package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_USER")
public class Comment {
	
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

    public int getCmtIdx() {
        return cmtIdx;
    }

    public void setCmtIdx(int cmtIdx) {
        this.cmtIdx = cmtIdx;
    }

    public int getBIdx() {
        return bIdx;
    }

    public void setBIdx(int bIdx) {
        this.bIdx = bIdx;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Comment 모델 복사
    public void CopyData(Comment param)
    {
        this.cmtIdx = param.getCmtIdx();
        this.bIdx = param.getBIdx();
        this.cmtContent = param.getCmtContent();
        this.createdAt = param.getCreatedAt();
        this.userEmail = param.getUserEmail();
    }
	
}
