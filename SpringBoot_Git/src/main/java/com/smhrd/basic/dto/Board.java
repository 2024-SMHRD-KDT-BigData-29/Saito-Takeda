package com.smhrd.basic.dto;

import java.sql.Timestamp;

public class Board {
	
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

    public int getBIdx() {
        return bIdx;
    }

    public void setBIdx(int bIdx) {
        this.bIdx = bIdx;
    }

    public String getBType() {
        return bType;
    }

    public void setBType(String bType) {
        this.bType = bType;
    }

    public String getBTitle() {
        return bTitle;
    }

    public void setBTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getBContent() {
        return bContent;
    }

    public void setBContent(String bContent) {
        this.bContent = bContent;
    }

    public String getBFile() {
        return bFile;
    }

    public void setBFile(String bFile) {
        this.bFile = bFile;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getBViews() {
        return bViews;
    }

    public void setBViews(Integer bViews) {
        this.bViews = bViews;
    }

    public Integer getBLikes() {
        return bLikes;
    }

    public void setBLikes(Integer bLikes) {
        this.bLikes = bLikes;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Board 모델 복사
    public void CopyData(Board param)
    {
        this.bIdx = param.getBIdx();
        this.bType = param.getBType();
        this.bTitle = param.getBTitle();
        this.bContent = param.getBContent();
        this.bFile = param.getBFile();
        this.createdAt = param.getCreatedAt();
        this.bViews = param.getBViews();
        this.bLikes = param.getBLikes();
        this.userEmail = param.getUserEmail();
    }

}
