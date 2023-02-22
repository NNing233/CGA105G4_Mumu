package com.propprodreview.model;

import java.sql.Timestamp;

public class PropProdReviewVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer reviewProductId;
	private Integer proposalId;
	private Integer adminId;
	private Status reviewProductStatus;
	private Timestamp applyTime;
	private String reviewResult;
	public Integer getReviewProductId() {
		return reviewProductId;
	}
	public void setReviewProductId(Integer reviewProductId) {
		this.reviewProductId = reviewProductId;
	}
	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Status getReviewProductStatus() {
		return reviewProductStatus;
	}
	public void setReviewProductStatus(Status reviewProductStatus) {
		this.reviewProductStatus = reviewProductStatus;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public String getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	
}
