package com.propopt.model;

import java.util.List;

import com.prop.model.PropVO;

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
	
public PropVO getPropVO() {
		
		com.prop.model.PropService propSvc = new com.prop.model.PropService();
		com.prop.model.PropVO propVO = propSvc.getOneProp(proposalId);
		
		return propVO;
		
	}

public List<com.sponrecord.model.SponRecordVO> getSponRecordVO() {
	com.sponrecord.model.SponRecordService sponRecordSvc = new com.sponrecord.model.SponRecordService();
    List<com.sponrecord.model.SponRecordVO> list  = sponRecordSvc.getTheOptionSponrecords(proposalOptionId);
	return list;
}



}
