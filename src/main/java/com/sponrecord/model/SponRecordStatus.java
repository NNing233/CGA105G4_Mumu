package com.sponrecord.model;

public enum SponRecordStatus {
	未達標(1), 待出貨(2), 已出貨(3), 已到貨(4),訂單完成(5),換貨(6),退費(7),已取消(8),製作中(9);
	
	private int value;
	
	SponRecordStatus(int value){
		this.value = value;
	}
	
	public static SponRecordStatus from(int value) {
		for(SponRecordStatus sponRecordStatus : SponRecordStatus.values()) {
			if(sponRecordStatus.getValue()==value) {
				return sponRecordStatus;
			}
		}
		throw new IllegalArgumentException("Invalid ProposalStatus values: " + value);
	}
	
	 public static SponRecordStatus fromString(String value) {
		 return valueOf(value);
	 }
	 
	public int getValue() {
		return this.value;
	}
}
