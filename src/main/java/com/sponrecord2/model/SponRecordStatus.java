package com.sponrecord2.model;

public enum SponRecordStatus {
	未達標(1), 待出貨(2), 已出貨(3), 已到貨(4), 訂單完成(5), 換貨(6), 退費(7), 已取消(8);
	
	private int value;
	
	SponRecordStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static String getStatusName(int value) {
		
		String statusName = "";
		
		for(SponRecordStatus sponsorRecordStatus: SponRecordStatus.values()) {
			if(sponsorRecordStatus.getValue() == value) {
				statusName = sponsorRecordStatus.name();
			}
		}
		
		return statusName;
	}
}
