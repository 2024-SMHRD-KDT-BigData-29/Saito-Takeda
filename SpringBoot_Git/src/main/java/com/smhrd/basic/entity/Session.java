package com.smhrd.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;

@Entity
public class Session {
	
	 // 세션 식별자 
    private int sessionIdx;

    // 유저 이메일 
    private String userEmail;

    // 로그 유형 
    private String logType;

    // 로그 시간 
    private Timestamp logTime;

    // IP 주소 
    private String ipAddr;

    // 기기 정보 
    private String macAddr;

    public int getSessionIdx() {
        return sessionIdx;
    }

    public void setSessionIdx(int sessionIdx) {
        this.sessionIdx = sessionIdx;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    // Session 모델 복사
    public void CopyData(Session param)
    {
        this.sessionIdx = param.getSessionIdx();
        this.userEmail = param.getUserEmail();
        this.logType = param.getLogType();
        this.logTime = param.getLogTime();
        this.ipAddr = param.getIpAddr();
        this.macAddr = param.getMacAddr();
    }

}
