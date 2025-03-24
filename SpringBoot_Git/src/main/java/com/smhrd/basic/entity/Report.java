package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;

@Entity
public class Report {
	
	// 신고 식별자 
    private int rpIdx;

    // 신고자 아이디 
    private String userEmail;

    // 신고 대상자 
    private String rpEmail;

    // 신고 사유 
    private String rpReason;

    // 신고 날짜 
    private Timestamp reportedAt;

    // 처리 여부 
    private String isHandled;

    // 회신 내용 
    private String handledContent;

    // 회신 날짜 
    private Timestamp handledAt;

    public int getRpIdx() {
        return rpIdx;
    }

    public void setRpIdx(int rpIdx) {
        this.rpIdx = rpIdx;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRpEmail() {
        return rpEmail;
    }

    public void setRpEmail(String rpEmail) {
        this.rpEmail = rpEmail;
    }

    public String getRpReason() {
        return rpReason;
    }

    public void setRpReason(String rpReason) {
        this.rpReason = rpReason;
    }

    public Timestamp getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Timestamp reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getIsHandled() {
        return isHandled;
    }

    public void setIsHandled(String isHandled) {
        this.isHandled = isHandled;
    }

    public String getHandledContent() {
        return handledContent;
    }

    public void setHandledContent(String handledContent) {
        this.handledContent = handledContent;
    }

    public Timestamp getHandledAt() {
        return handledAt;
    }

    public void setHandledAt(Timestamp handledAt) {
        this.handledAt = handledAt;
    }

    // tbReport 모델 복사
    public void CopyData(Report param)
    {
        this.rpIdx = param.getRpIdx();
        this.userEmail = param.getUserEmail();
        this.rpEmail = param.getRpEmail();
        this.rpReason = param.getRpReason();
        this.reportedAt = param.getReportedAt();
        this.isHandled = param.getIsHandled();
        this.handledContent = param.getHandledContent();
        this.handledAt = param.getHandledAt();
    }

}
