package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_board")
public class BoardEntity {
	
    // 글 식별자
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 인덱스 자동 1증가 어노테이션
    private int bIdx;

    // 글 유형
	@Column(nullable = false, length = 20)
    private String bType;

    // 글 제목 
	@Column(nullable = false, length = 1000)
    private String bTitle;

    // 글 내용 
	@Column(nullable = false)
    private String bContent;

    // 글 첨부파일 
	@Column(nullable = true, length = 1000)
    private String bFile;

    // 글 작성일자 
	@Column(nullable = false)
    private Timestamp createdAt;

    // 글 조회수 
	@Column(nullable = false)
    private Integer bViews;

    // 글 좋아요수 
	@Column(nullable = true)
    private Integer bLikes;

    // 글 작성자 
	@Column(nullable = false, length = 50)
    private String userEmail;


}
