package com.smhrd.basic.model;

import java.sql.Timestamp;

public class Message {
	
	// 메시지 식별자 
    private int msgIdx;

    // 발신자 아이디 
    private String senderId;

    // 수신자 아이디 
    private String recevierId;

    // 메시지 내용 
    private String msgContent;

    // 전송 날짜 
    private Timestamp sendedAt;

    // 읽음 여부 
    private String isRead;

    public int getMsgIdx() {
        return msgIdx;
    }

    public void setMsgIdx(int msgIdx) {
        this.msgIdx = msgIdx;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecevierId() {
        return recevierId;
    }

    public void setRecevierId(String recevierId) {
        this.recevierId = recevierId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Timestamp getSendedAt() {
        return sendedAt;
    }

    public void setSendedAt(Timestamp sendedAt) {
        this.sendedAt = sendedAt;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    // Message 모델 복사
    public void CopyData(Message param)
    {
        this.msgIdx = param.getMsgIdx();
        this.senderId = param.getSenderId();
        this.recevierId = param.getRecevierId();
        this.msgContent = param.getMsgContent();
        this.sendedAt = param.getSendedAt();
        this.isRead = param.getIsRead();
    }

}
