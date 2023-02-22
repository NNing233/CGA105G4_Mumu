package com.prop2.model;

public enum PropStatus {
	下架(0), 未上架(1), 未達標(2), 已達標(3), 已達標生產階段(4), 已達標產品寄送(5), 違信退款(6), 結案(7), 商城上架(8);
	
	private int value;
	
	PropStatus(int value) {
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
		
		for(PropStatus sponsorRecordStatus: PropStatus.values()) {
			if(sponsorRecordStatus.getValue() == value) {
				statusName = sponsorRecordStatus.name();
			}
		}
		
		return statusName;
	}
}
