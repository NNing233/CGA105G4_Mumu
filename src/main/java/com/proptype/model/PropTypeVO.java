package com.proptype.model;

public class PropTypeVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer proposalTypeId;
	private String proposalTypeName;
	private Integer proposalTypeStatus;
	
	public Integer getProposalTypeStatus() {
		return proposalTypeStatus;
	}
	public void setProposalTypeStatus(Integer proposalTypeStatus) {
		this.proposalTypeStatus = proposalTypeStatus;
	}
	public Integer getProposalTypeId() {
		return proposalTypeId;
	}
	public void setProposalTypeId(Integer proposalTypeId) {
		this.proposalTypeId = proposalTypeId;
	}
	public String getProposalTypeName() {
		return proposalTypeName;
	}
	public void setProposalTypeName(String proposalTypeName) {
		this.proposalTypeName = proposalTypeName;
	}


}
