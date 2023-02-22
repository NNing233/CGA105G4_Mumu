package com.sponrecord.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SponRecordVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sponsorRecordId;
	private Integer memberId;
	private SponRecordStatus sponsorRecordStatus;
	private Timestamp sponsorDatetime;
	private String sponsorRecordAddress;
	private Integer sponsorRecordPayment;
	private LogisticsStatus sponsorRecordDelivery;
	private Integer sponsorTotalAmount;
	
//	後來新增的三個欄位
	private Integer proposalOptionId;
	private Integer proposalOptionQuantity;
	private Integer proposalId;
	
	public Integer getProposalOptionId() {
		return proposalOptionId;
	}
	public void setProposalOptionId(Integer proposalOptionId) {
		this.proposalOptionId = proposalOptionId;
	}
	public Integer getProposalOptionQuantity() {
		return proposalOptionQuantity;
	}
	public void setProposalOptionQuantity(Integer proposalOptionQuantity) {
		this.proposalOptionQuantity = proposalOptionQuantity;
	}
	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	
	
	
	public Integer getSponsorRecordId() {
		return sponsorRecordId;
	}
	public void setSponsorRecordId(Integer sponsorRecordId) {
		this.sponsorRecordId = sponsorRecordId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public SponRecordStatus getSponsorRecordStatus() {
		return sponsorRecordStatus;
	}
	public void setSponsorRecordStatus(SponRecordStatus sponsorRecordStatus) {
		this.sponsorRecordStatus = sponsorRecordStatus;
	}
	public Timestamp getSponsorDatetime() {
		return sponsorDatetime;
	}
	public void setSponsorDatetime(Timestamp sponsorDatetime) {
		this.sponsorDatetime = sponsorDatetime;
	}
	public String getSponsorRecordAddress() {
		return sponsorRecordAddress;
	}
	public void setSponsorRecordAddress(String sponsorRecordAddress) {
		this.sponsorRecordAddress = sponsorRecordAddress;
	}
	public Integer getSponsorRecordPayment() {
		return sponsorRecordPayment;
	}
	public void setSponsorRecordPayment(Integer sponsorRecordPayment) {
		this.sponsorRecordPayment = sponsorRecordPayment;
	}
	public LogisticsStatus getSponsorRecordDelivery() {
		return sponsorRecordDelivery;
	}
	public void setSponsorRecordDelivery(LogisticsStatus sponsorRecordDelivery) {
		this.sponsorRecordDelivery = sponsorRecordDelivery;
	}
	public Integer getSponsorTotalAmount() {
		return sponsorTotalAmount;
	}
	public void setSponsorTotalAmount(Integer sponsorTotalAmount) {
		this.sponsorTotalAmount = sponsorTotalAmount;
	}
	

}
