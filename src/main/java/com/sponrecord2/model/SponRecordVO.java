package com.sponrecord2.model;

import java.sql.Timestamp;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.prop2.model.PropService;
import com.prop2.model.PropVO;
import com.propopt2.model.PropOptService;
import com.propopt2.model.PropOptVO;
import com.proptype.model.PropTypeService;
import com.proptype.model.PropTypeVO;

public class SponRecordVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sponsorRecordId;
	private Integer memberId;
	private Integer sponsorRecordStatus;
	private Timestamp sponsorDatetime;
	private String sponsorRecordAddress;
	private Integer sponsorRecordPayment;
	private Integer SponsorRecordDelivery;
	private Integer sponsorTotalAmount;
	
//	後來新增的三個欄位
	private Integer proposalOptionId;
	private Integer proposalOptionQuantity;
	private Integer proposalId;
	
	//顯示會員贊助紀錄用
	public PropVO getPropVO() {
		PropService propService = new PropService();
		return propService.getOneProp(proposalId);
	}
	
	//顯示會員贊助紀錄用
	public PropOptVO getPropOptVO() {
		PropOptService propOptService = new PropOptService();
		return propOptService.getOnePropOpt(proposalOptionId);
	}
	
	//顯示會員贊助紀錄用
		public MemVO getMemVO() {
			MemService memSvc = new MemService();
			return memSvc.getOneMem(memberId);
		}
	
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
	public Integer getSponsorRecordStatus() {
		return sponsorRecordStatus;
	}
	public void setSponsorRecordStatus(Integer sponsorRecordStatus) {
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
	public Integer getSponsorRecordDelivery() {
		return SponsorRecordDelivery;
	}
	public void setSponsorRecordDelivery(Integer sponsorRecordDelivery) {
		SponsorRecordDelivery = sponsorRecordDelivery;
	}
	public Integer getSponsorTotalAmount() {
		return sponsorTotalAmount;
	}
	public void setSponsorTotalAmount(Integer sponsorTotalAmount) {
		this.sponsorTotalAmount = sponsorTotalAmount;
	}
	

}
