package com.smhrd.basic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

import com.smhrd.basic.dto.BoardDTO;

@Entity
@Table(name = "tb_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_idx")
    private int bidx;

    @Column(name = "b_type", length = 10, nullable = false)
    private String btype;

    @Column(name = "b_title", length = 100, nullable = false)
    private String btitle;

    @Column(name = "b_content", length = 1000)
    private String bcontent;

    @Column(name = "b_file", length = 255)
    private String bfile;

    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "b_views")
    private Integer bviews;

    @Column(name = "b_likes")
    private Integer blikes;

    @Column(name = "b_writer", length = 50, nullable = false)
    private String bwriter;

    @Column(name = "monthly_rent")
    private Integer monthlyRent;

    @Column(name = "management_fee")
    private Integer managementFee;

    @Column(name = "house_type", length = 50)
    private String houseType;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "user_photo", length = 255)
    private String userPhoto;

    @Column(name = "desired_address", length = 255)
    private String desiredAddress;
    
    @Column(name = "lease_contract", length = 255)
    private String leaseContractPath;
    
    @Column(name = "criminal_record", length = 255)
    private String criminalRecordPath;
    
    @ManyToOne
    @JoinColumn(name = "b_writer", insertable = false, updatable = false)
    private UserEntity user;

    public static BoardEntity toBoardEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBidx(boardDTO.getBidx());
        boardEntity.setBtype(boardDTO.getBtype());
        boardEntity.setBtitle(boardDTO.getBtitle());
        boardEntity.setBcontent(boardDTO.getBcontent());
        boardEntity.setBfile(boardDTO.getBfile());
        boardEntity.setCreatedAt(boardDTO.getCreatedAt());
        boardEntity.setCreatedAt(boardDTO.getCreatedAt());
        boardEntity.setBviews(boardDTO.getBviews());
        boardEntity.setBlikes(boardDTO.getBlikes());
        boardEntity.setBwriter(boardDTO.getBwriter());
        boardEntity.setMonthlyRent(boardDTO.getMonthlyRent());
        boardEntity.setManagementFee(boardDTO.getManagementFee());
        boardEntity.setHouseType(boardDTO.getHouseType());
        boardEntity.setAddress(boardDTO.getAddress());
        boardEntity.setBudget(boardDTO.getBudget());
        boardEntity.setUserPhoto(boardDTO.getUserPhoto());
        boardEntity.setDesiredAddress(boardDTO.getDesiredAddress());
        return boardEntity;
    }
}