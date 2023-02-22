package com.prop.model;

public enum ProposalStatus {
	下架(0), 未上架(1), 已上架未達標(2), 已上架達標(3),生產階段(4),產品寄送(5),違信退款(6),結案(7),商城上架(8);
	
	private int value;
	
	ProposalStatus(int value){
		this.value = value;
	}
	
	public static ProposalStatus from(int value) {
		for(ProposalStatus proposalStatus : ProposalStatus.values()) {
			if(proposalStatus.getValue()==value) {
				return proposalStatus;
			}
		}
		throw new IllegalArgumentException("Invalid ProposalStatus values: " + value);
	}
	
	 public static ProposalStatus fromString(String value) {
		 return valueOf(value);
	 }
	 
	public int getValue() {
		return this.value;
	}
}
