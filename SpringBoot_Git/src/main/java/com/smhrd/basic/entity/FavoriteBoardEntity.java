package com.smhrd.basic.entity;

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
@Table(name = "TB_FAVORITE")
public class FavoriteBoardEntity {
    
    @Id
    @Column(name = "FAV_IDX", nullable = false)
    private int favIdx;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "B_IDX", nullable = false)
    private int bIdx;
    
    @Column(name = "CREATED_AT", nullable = true)
    private java.sql.Timestamp createdAt;
}