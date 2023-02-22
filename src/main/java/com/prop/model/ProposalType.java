package com.prop.model;

public enum ProposalType {
	許願(0), 戶外(1), 電子(2), 手作(3);
	
	private int value;
	
	ProposalType(int value){
		this.value = value;
	}
	
	public static ProposalType from(int value) {
		for(ProposalType proposalType : ProposalType.values()) {
			if(proposalType.getValue()==value) {
				return proposalType;
			}
		}
		throw new IllegalArgumentException("Invalid ProposalStatus values: " + value);
	}
	
	public int getValue() {
		return this.value;
	}
}
