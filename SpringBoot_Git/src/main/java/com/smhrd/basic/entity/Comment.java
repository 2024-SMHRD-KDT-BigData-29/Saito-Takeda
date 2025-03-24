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
@Table(name = "TB_COMMENT")
public class Comment {
    
    @Id
    @Column(name = "CMT_IDX", nullable = false)
    private int cmtIdx;
    
    @Column(name = "B_IDX", nullable = false)
    private int bIdx;
    
    @Column(name = "CMT_CONTENT", nullable = true, length = 500)
    private String cmtContent;
    
    @Column(name = "CREATED_AT", nullable = true)
    private Timestamp createdAt;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
}