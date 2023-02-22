package com.proposalcollection.model;

public class PropCollectionVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer memberID;
	private Integer proposalID;

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getProposalID() {
		return proposalID;
	}

	public void setProposalID(Integer proposalID) {
		this.proposalID = proposalID;
	}
}
