package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;

@Entity
public class FavoriteBoard {
	
	// 관심 식별자 
    private int favIdx;

    // 회원 이메일 
    private String userEmail;

    // 글 식별자 
    private int bIdx;

    // 등록 일자 
    private Timestamp createdAt;

    public int getFavIdx() {
        return favIdx;
    }

    public void setFavIdx(int favIdx) {
        this.favIdx = favIdx;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getBIdx() {
        return bIdx;
    }

    public void setBIdx(int bIdx) {
        this.bIdx = bIdx;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // tbFavorite 모델 복사
    public void CopyData(FavoriteBoard param)
    {
        this.favIdx = param.getFavIdx();
        this.userEmail = param.getUserEmail();
        this.bIdx = param.getBIdx();
        this.createdAt = param.getCreatedAt();
    }

}
