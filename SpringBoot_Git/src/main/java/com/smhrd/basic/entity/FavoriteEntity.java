package com.smhrd.basic.entity;

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
    @Column(name = "favorite_id")
    private Long favoriteId;

    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "bidx", nullable = false)
    private Integer bidx;
}