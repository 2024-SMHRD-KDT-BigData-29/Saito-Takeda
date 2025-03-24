package com.smhrd.basic.entity;

import java.sql.Timestamp;
import java.sql.Date;

public class CrimeEntity {
	
	// 회보서 식별자 
    private int crimIdx;

    // 회원 이메일 
    private String userEmail;

    // 발급 일자 
    private Date printedAt;

    // 유효 기한 
    private Date expiredAt;

    // 발급 기관 
    private String issueOrg;

    // 회보서 파일 
    private String crimFile;

    // 제출 일시 
    private Timestamp summitedAt;

    // 검증 상태 
    private String veriStatus;

    // 관리자 아이디 
    private String adminEmail;

    // 검증 일시 
    private Timestamp verifiedAt;

    public int getCrimIdx() {
        return crimIdx;
    }

    public void setCrimIdx(int crimIdx) {
        this.crimIdx = crimIdx;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getPrintedAt() {
        return printedAt;
    }

    public void setPrintedAt(Date printedAt) {
        this.printedAt = printedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getIssueOrg() {
        return issueOrg;
    }

    public void setIssueOrg(String issueOrg) {
        this.issueOrg = issueOrg;
    }

    public String getCrimFile() {
        return crimFile;
    }

    public void setCrimFile(String crimFile) {
        this.crimFile = crimFile;
    }

    public Timestamp getSummitedAt() {
        return summitedAt;
    }

    public void setSummitedAt(Timestamp summitedAt) {
        this.summitedAt = summitedAt;
    }

    public String getVeriStatus() {
        return veriStatus;
    }

    public void setVeriStatus(String veriStatus) {
        this.veriStatus = veriStatus;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    // tbCriminalRecord 모델 복사
    public void CopyData(CrimeEntity param)
    {
        this.crimIdx = param.getCrimIdx();
        this.userEmail = param.getUserEmail();
        this.printedAt = param.getPrintedAt();
        this.expiredAt = param.getExpiredAt();
        this.issueOrg = param.getIssueOrg();
        this.crimFile = param.getCrimFile();
        this.summitedAt = param.getSummitedAt();
        this.veriStatus = param.getVeriStatus();
        this.adminEmail = param.getAdminEmail();
        this.verifiedAt = param.getVerifiedAt();
    }

}
