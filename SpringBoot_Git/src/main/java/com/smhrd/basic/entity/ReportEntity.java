package com.smhrd.basic.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_report")
public class ReportEntity {
	
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int rpIdx;
    
    @Column(nullable = true, length = 50)
    private String userEmail;
    
    @Column(nullable = true, length = 50)
    private String rpEmail;
    
    @Column(nullable = true)
    private String rpReason;
    
    @Column(nullable = true)
    private Timestamp reportedAt;
    
    @Column(nullable = true, length = 1)
    private String isHandled;
    
    @Column(nullable = true)
    private String handledContent;
    
    @Column(nullable = true)
    private Timestamp handledAt;
    
    //
}