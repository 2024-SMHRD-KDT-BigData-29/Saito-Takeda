package com.smhrd.basic.entity;

import java.sql.Date;
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
@Table(name = "TB_TERMINAL_BOARD")
public class CrimeEntity {
    
    @Id
    @Column(name = "CRUX_IDX", nullable = false)
    private int cruxIdx;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "PRINTED_AT", nullable = true)
    private Date printedAt;
    
    @Column(name = "EXPIRED_AT", nullable = true)
    private Date expiredAt;
    
    @Column(name = "ISSUE_ORG", nullable = true, length = 50)
    private String issueOrg;
    
    @Column(name = "CRUX_FILE", nullable = true, length = 1000)
    private String cruxFile;
    
    @Column(name = "SUBMITTED_AT", nullable = true)
    private Timestamp submittedAt;
    
    @Column(name = "VERI_STATUS", nullable = true, length = 1)
    private String veriStatus;
    
    @Column(name = "ADMIN_EMAIL", nullable = true, length = 50)
    private String adminEmail;
    
    @Column(name = "VERIFIED_AT", nullable = true)
    private Timestamp verifiedAt;
}