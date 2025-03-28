package com.smhrd.basic.entity;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.UserDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_idx")
    private Integer favoriteIdx;

    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "bidx", nullable = false)
    private Integer bidx;
    
 // 연관 관계 (선택 사항)
    @ManyToOne
    @JoinColumn(name = "user_email", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bidx", insertable = false, updatable = false)
    private BoardEntity board;
}