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
@Table(name = "TB_REPORT")
public class Report {
    
    @Id
    @Column(name = "RP_IDX", nullable = false)
    private int rpIdx;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "RP_EMAIL", nullable = true, length = 50)
    private String rpEmail;
    
    @Column(name = "RP_REASON", nullable = true)
    private String rpReason;
    
    @Column(name = "REPORTED_AT", nullable = true)
    private Timestamp reportedAt;
    
    @Column(name = "IS_HANDLED", nullable = true, length = 1)
    private String isHandled;
    
    @Column(name = "HANDLED_CONTENT", nullable = true)
    private String handledContent;
    
    @Column(name = "HANDLED_AT", nullable = true)
    private Timestamp handledAt;
}