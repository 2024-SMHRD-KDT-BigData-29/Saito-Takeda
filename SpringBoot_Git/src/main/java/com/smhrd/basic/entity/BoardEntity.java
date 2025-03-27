package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private int bidx;

    // 글 유형
	@Column(nullable = false, length = 20)
    private String btype;

    // 글 제목 
	@Column(nullable = false, length = 1000)
    private String btitle;

    // 글 내용 
	@Column(nullable = false)
    private String bcontent;

    // 글 첨부파일 
	@Column(nullable = true, length = 1000)
    private String bfile;

    // 글 작성일자 
	@Column(nullable = false)
    private Timestamp createdAt;

    // 글 조회수 
	@Column(nullable = false)
    private Integer bviews;

    // 글 좋아요수 
	@Column(nullable = true)
    private Integer blikes;

    // 글 작성자 
	@Column(nullable = false, length = 50)
    private String userEmail;
	
	// 룸메찾기용 추가 필드
    @Column(nullable = true)
    private Integer monthlyRent;

    @Column(nullable = true)
    private Integer managementFee;

    @Column(nullable = true, length = 50)
    private String houseType;

    // 방찾기용 추가 필드
    @Column(nullable = true)
    private Integer budget;

    @Column(nullable = true, length = 1000)
    private String userPhoto;


}
