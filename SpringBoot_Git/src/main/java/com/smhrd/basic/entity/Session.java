package com.smhrd.basic.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TB_SESSION")
public class Session {
    
    @Id
    @Column(name = "SESSION_IDX", nullable = false)
    private int sessionIdx;
    
    @Column(name = "USER_EMAIL", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "LOG_TYPE", nullable = true, length = 1)
    private String logType;
    
    @Column(name = "LOG_TIME", nullable = true)
    private Timestamp logTime;
    
    @Column(name = "IP_ADDR", nullable = true, length = 20)
    private String ipAddr;
    
    @Column(name = "MAC_ADDR", nullable = true, length = 20)
    private String macAddr;
}