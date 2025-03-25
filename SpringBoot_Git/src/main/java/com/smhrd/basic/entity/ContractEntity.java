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
@Table(name = "TB_RENT_CONTRACT")
public class ContractEntity {
    
    @Id
    @Column(name = "CTRT_IDX", nullable = false)
    private int ctrtIdx;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "CTRT_ST_DT", nullable = true)
    private Date ctrtStDt;
    
    @Column(name = "CTRT_ED_DT", nullable = true)
    private Date ctrtEdDt;
    
    @Column(name = "DEPOSIT", nullable = true)
    private Integer deposit;
    
    @Column(name = "RENT", nullable = true)
    private Integer rent;
    
    @Column(name = "ADDR", nullable = true, length = 1000)
    private String addr;
    
    @Column(name = "CTRT_STATUS", nullable = true, length = 50)
    private String ctrtStatus;
    
    @Column(name = "CTRT_IMG", nullable = true, length = 1000)
    private String ctrtImg;
    
    @Column(name = "SUBMITTED_AT", nullable = true)
    private Timestamp submittedAt;
    
    @Column(name = "ADMIN_EMAIL", nullable = true, length = 50)
    private String adminEmail;
    
    @Column(name = "CONFIRMED_AT", nullable = true)
    private Timestamp confirmedAt;
    
    @Column(name = "CONFIRM_YN", nullable = true, length = 1)
    private String confirmYn;
}