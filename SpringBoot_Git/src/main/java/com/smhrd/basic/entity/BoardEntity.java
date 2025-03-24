package com.smhrd.basic.entity;

import java.sql.Timestamp;

import com.smhrd.basic.model.Board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

	// 해당 클래스를 DB 테이블처럼 구성하는 클래스
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bIdx;
	
	@Column(length = 20)
	private String bType;
	
	@Column(length = 1000)
	private String bTitle;
	
	@Column(columnDefinition = "TEXT")
    private String bContent;
	
	@Column(length = 1000)
    private String bFile;

	@CreationTimestamp
	@Column(name = "created_at")
    private Timestamp createdAt;

    private Integer bViews;

    private Integer bLikes;

    private String userEmail;
	
	
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
