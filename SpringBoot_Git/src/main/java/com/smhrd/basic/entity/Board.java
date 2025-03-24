package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "TB_BOARD")
public class Board {
	
    // 글 식별자
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bIdx;

    // 글 유형
	@Column(nullable = false)
    private String bType;

    // 글 제목 
	@Column(nullable = false)
    private String bTitle;

    // 글 내용 
	@Column(nullable = false)
    private String bContent;

    // 글 첨부파일 
	@Column(nullable = true)
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
	@Column(nullable = false)
    private String userEmail;


}
