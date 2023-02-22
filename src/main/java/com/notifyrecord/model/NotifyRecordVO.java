package com.notifyrecord.model;

import java.sql.Date;

public class NotifyRecordVO {
	private Integer notifyRecordId;
	private Integer memberId;
	private String notifyContent;
	private byte isRead;
	private Date notifyDatetime;
	public Integer getNotifyRecordId() {
		return notifyRecordId;
	}
	public void setNotifyRecordId(Integer notifyRecordId) {
		this.notifyRecordId = notifyRecordId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public byte getIsRead() {
		return isRead;
	}
	public void setIsRead(byte isRead) {
		this.isRead = isRead;
	}
	public Date getNotifyDatetime() {
		return notifyDatetime;
	}
	public void setNotifyDatetime(Date notifyDatetime) {
		this.notifyDatetime = notifyDatetime;
	}
	
	

}
