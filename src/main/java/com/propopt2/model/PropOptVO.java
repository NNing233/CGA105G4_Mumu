package com.propopt2.model;

import com.prop2.model.PropService;
import com.prop2.model.PropVO;

public class PropOptVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer proposalOptionId;
	private Integer proposalId;
	private String proposalOptionName;
	private Integer proposalOptionPrice;
	private byte[] proposalOptionPicture;
	private Integer reviewProductId;
	
	public Integer getReviewProductId() {
		return reviewProductId;
	}
	public void setReviewProductId(Integer reviewProductId) {
		this.reviewProductId = reviewProductId;
	}
	public Integer getProposalOptionId() {
		return proposalOptionId;
	}
	public void setProposalOptionId(Integer proposalOptionId) {
		this.proposalOptionId = proposalOptionId;
	}
	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	public String getProposalOptionName() {
		return proposalOptionName;
	}
	public void setProposalOptionName(String proposalOptionName) {
		this.proposalOptionName = proposalOptionName;
	}
	public Integer getProposalOptionPrice() {
		return proposalOptionPrice;
	}
	public void setProposalOptionPrice(Integer proposalOptionPrice) {
		this.proposalOptionPrice = proposalOptionPrice;
	}
	public byte[] getProposalOptionPicture() {
		return proposalOptionPicture;
	}
	public void setProposalOptionPicture(byte[] proposalOptionPicture) {
		this.proposalOptionPicture = proposalOptionPicture;
	}
	
	

}
