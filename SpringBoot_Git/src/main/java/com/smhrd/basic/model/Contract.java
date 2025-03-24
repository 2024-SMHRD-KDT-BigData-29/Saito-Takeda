package com.smhrd.basic.model;

import java.sql.Timestamp;
import java.sql.Date;

public class Contract {
	
	// 계약서 식별자 
    private int ctrtIdx;

    // 회원 이메일 
    private String userEmail;

    // 계약 시작일 
    private Date ctrtStDt;

    // 계약 종료일 
    private Date ctrtEdDt;

    // 보증금 
    private Integer deposit;

    // 임대료 
    private Integer rent;

    // 주소 
    private String addr;

    // 계약 상태 
    private String ctrtStatus;

    // 계약서 이미지 
    private String ctrtImg;

    // 제출 일시 
    private Timestamp summitedAt;

    // 관리자 아이디 
    private String adminEmail;

    // 확인 일시 
    private Timestamp confirmedAt;

    // 확인 여부 
    private String confirmYn;

    public int getCtrtIdx() {
        return ctrtIdx;
    }

    public void setCtrtIdx(int ctrtIdx) {
        this.ctrtIdx = ctrtIdx;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCtrtStDt() {
        return ctrtStDt;
    }

    public void setCtrtStDt(Date ctrtStDt) {
        this.ctrtStDt = ctrtStDt;
    }

    public Date getCtrtEdDt() {
        return ctrtEdDt;
    }

    public void setCtrtEdDt(Date ctrtEdDt) {
        this.ctrtEdDt = ctrtEdDt;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCtrtStatus() {
        return ctrtStatus;
    }

    public void setCtrtStatus(String ctrtStatus) {
        this.ctrtStatus = ctrtStatus;
    }

    public String getCtrtImg() {
        return ctrtImg;
    }

    public void setCtrtImg(String ctrtImg) {
        this.ctrtImg = ctrtImg;
    }

    public Timestamp getSummitedAt() {
        return summitedAt;
    }

    public void setSummitedAt(Timestamp summitedAt) {
        this.summitedAt = summitedAt;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public Timestamp getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Timestamp confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public String getConfirmYn() {
        return confirmYn;
    }

    public void setConfirmYn(String confirmYn) {
        this.confirmYn = confirmYn;
    }

    // tbRentContract 모델 복사
    public void CopyData(Contract param)
    {
        this.ctrtIdx = param.getCtrtIdx();
        this.userEmail = param.getUserEmail();
        this.ctrtStDt = param.getCtrtStDt();
        this.ctrtEdDt = param.getCtrtEdDt();
        this.deposit = param.getDeposit();
        this.rent = param.getRent();
        this.addr = param.getAddr();
        this.ctrtStatus = param.getCtrtStatus();
        this.ctrtImg = param.getCtrtImg();
        this.summitedAt = param.getSummitedAt();
        this.adminEmail = param.getAdminEmail();
        this.confirmedAt = param.getConfirmedAt();
        this.confirmYn = param.getConfirmYn();
    }
	
	

}
