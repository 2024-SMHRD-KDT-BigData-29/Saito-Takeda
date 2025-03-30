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
    @Column(name = "fav_idx")
    private Integer favoriteIdx;

    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "b_idx", nullable = false)
    private Integer bidx;
    
    public FavoriteEntity(Integer favoriteIdx, String userEmail, Integer bidx) {
        this.favoriteIdx = favoriteIdx;
        this.userEmail = userEmail;
        this.bidx = bidx;
    }
    
 // 연관 관계 (선택 사항)
    @ManyToOne
    @JoinColumn(name = "user_email", insertable = false, updatable = false)
    private UserEntity user;

}