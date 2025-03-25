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
@Table(name = "tb_report")
public class ReportEntity {
	
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RP_IDX", nullable = false)
    private int rpIdx;
    
    @Column(name = "user_email", nullable = true, length = 50)
    private String userEmail;
    
    @Column(name = "rp_email", nullable = true, length = 50)
    private String rpEmail;
    
    @Column(name = "rp_reason", nullable = true)
    private String rpReason;
    
    @Column(name = "reported_at", nullable = true)
    private Timestamp reportedAt;
    
    @Column(name = "is_handled", nullable = true, length = 1)
    private String isHandled;
    
    @Column(name = "handled_content", nullable = true)
    private String handledContent;
    
    @Column(name = "handled_at", nullable = true)
    private Timestamp handledAt;
}