package com.smhrd.basic.dto;

public class Profile {
	
	// 사용자 이메일 
    private String userEmail;

    // 프로필 이미지 
    private String profileImg;

    // 자기소개글 
    private String uSERINTRODUCTION;

    // 사용자 MBTI 
    private String userMbti;

    // 선호 MBTI 
    private String partnerMbti;

    // 생활패턴 
    private String lifePattern;

    // 흡연여부 
    private String smokeYn;

    // 반려동물 유무 
    private String petYn;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUSERINTRODUCTION() {
        return uSERINTRODUCTION;
    }

    public void setUSERINTRODUCTION(String uSERINTRODUCTION) {
        this.uSERINTRODUCTION = uSERINTRODUCTION;
    }

    public String getUserMbti() {
        return userMbti;
    }

    public void setUserMbti(String userMbti) {
        this.userMbti = userMbti;
    }

    public String getPartnerMbti() {
        return partnerMbti;
    }

    public void setPartnerMbti(String partnerMbti) {
        this.partnerMbti = partnerMbti;
    }

    public String getLifePattern() {
        return lifePattern;
    }

    public void setLifePattern(String lifePattern) {
        this.lifePattern = lifePattern;
    }

    public String getSmokeYn() {
        return smokeYn;
    }

    public void setSmokeYn(String smokeYn) {
        this.smokeYn = smokeYn;
    }

    public String getPetYn() {
        return petYn;
    }

    public void setPetYn(String petYn) {
        this.petYn = petYn;
    }

    // tbProfile 모델 복사
    public void CopyData(Profile param)
    {
        this.userEmail = param.getUserEmail();
        this.profileImg = param.getProfileImg();
        this.uSERINTRODUCTION = param.getUSERINTRODUCTION();
        this.userMbti = param.getUserMbti();
        this.partnerMbti = param.getPartnerMbti();
        this.lifePattern = param.getLifePattern();
        this.smokeYn = param.getSmokeYn();
        this.petYn = param.getPetYn();
    }

}
