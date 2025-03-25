package com.smhrd.basic.dto;

import java.sql.Timestamp;

public class User {

	// 사용자 
	public class tbUser {

	    // 사용자 이메일 
	    private String userEmail;

	    // 사용자 비밀번호 
	    private String userPw;

	    // 사용자 이름 
	    private String userName;

	    // 사용자 전화번호 
	    private String userPhone;

	    // 사용자 성별 
	    private String userGender;

	    // 사용자 주민번호 
	    private String userRegnum;

	    // 사용자 주소 
	    private String userAddr;

	    // 사용자 계정상태 
	    private String userStatus;

	    // 사용자 구분 
	    private String userRole;

	    // 가입 일자 
	    private Timestamp joinedAt;

	    // 사용자 닉네임 
	    private String userNickname;

	    public String getUserEmail() {
	        return userEmail;
	    }

	    public void setUserEmail(String userEmail) {
	        this.userEmail = userEmail;
	    }

	    public String getUserPw() {
	        return userPw;
	    }

	    public void setUserPw(String userPw) {
	        this.userPw = userPw;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getUserPhone() {
	        return userPhone;
	    }

	    public void setUserPhone(String userPhone) {
	        this.userPhone = userPhone;
	    }

	    public String getUserGender() {
	        return userGender;
	    }

	    public void setUserGender(String userGender) {
	        this.userGender = userGender;
	    }

	    public String getUserRegnum() {
	        return userRegnum;
	    }

	    public void setUserRegnum(String userRegnum) {
	        this.userRegnum = userRegnum;
	    }

	    public String getUserAddr() {
	        return userAddr;
	    }

	    public void setUserAddr(String userAddr) {
	        this.userAddr = userAddr;
	    }

	    public String getUserStatus() {
	        return userStatus;
	    }

	    public void setUserStatus(String userStatus) {
	        this.userStatus = userStatus;
	    }

	    public String getUserRole() {
	        return userRole;
	    }

	    public void setUserRole(String userRole) {
	        this.userRole = userRole;
	    }

	    public Timestamp getJoinedAt() {
	        return joinedAt;
	    }

	    public void setJoinedAt(Timestamp joinedAt) {
	        this.joinedAt = joinedAt;
	    }

	    public String getUserNickname() {
	        return userNickname;
	    }

	    public void setUserNickname(String userNickname) {
	        this.userNickname = userNickname;
	    }

	    // tbUser 모델 복사
	    public void CopyData(tbUser param)
	    {
	        this.userEmail = param.getUserEmail();
	        this.userPw = param.getUserPw();
	        this.userName = param.getUserName();
	        this.userPhone = param.getUserPhone();
	        this.userGender = param.getUserGender();
	        this.userRegnum = param.getUserRegnum();
	        this.userAddr = param.getUserAddr();
	        this.userStatus = param.getUserStatus();
	        this.userRole = param.getUserRole();
	        this.joinedAt = param.getJoinedAt();
	        this.userNickname = param.getUserNickname();
	    }
	}
}
