package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_comment")
public class CommentEntity {

    // 댓글 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int cmtIdx;

    // 원글 식별자 (BoardEntity와 연관)
    @Column(nullable = false)
    private int bidx;

    // 댓글 내용
    @Column(nullable = false, length = 1000)
    private String cmtContent;

    // 댓글 작성일자
    @Column(nullable = false)
    private Timestamp createdAt;

    // 댓글 작성자 (UserEntity와 연관)
    @Column(nullable = false, length = 50)
    private String userEmail;

    // 연관 관계 설정 (선택 사항)
    // 만약 객체 지향적으로 접근하고 싶다면 아래와 같이 연관 관계를 추가할 수 있어
    // @ManyToOne
    // @JoinColumn(name = "bIdx", insertable = false, updatable = false)
    // private BoardEntity board;

    // @ManyToOne
    // @JoinColumn(name = "userEmail", insertable = false, updatable = false)
    // private UserEntity user;
    
    //
}
