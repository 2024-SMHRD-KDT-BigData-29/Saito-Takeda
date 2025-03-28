package com.smhrd.basic.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_idx") // bidx → board_idx로 수정
    private int bidx;

    @Column(name = "b_title", length = 255)
    private String btitle;

    @Column(name = "b_content", length = 4000)
    private String bcontent;

    @Column(name = "b_type", length = 50)
    private String btype;

    @Column(name = "b_views")
    private int bviews;

    @Column(name = "b_likes")
    private int blikes;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "user_email", length = 50)
    private String userEmail;

    @Column(name = "user_photo", length = 255)
    private String userPhoto;

    @Column(name = "b_file", length = 255)
    private String bfile;

    @Column(name = "house_type", length = 50)
    private String houseType;

    @Column(name = "monthly_rent")
    private Integer monthlyRent;

    @Column(name = "management_fee")
    private Integer managementFee;

    @Column(name = "budget")
    private Integer budget;

    //

}
