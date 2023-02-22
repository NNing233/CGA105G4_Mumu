package com.sponrecord.model;

public enum LogisticsStatus{
	黑貓宅急便(1), 新竹物流(2), 嘉里大榮物流(3);
	
	private int value;
	
	LogisticsStatus(int value){
		this.value = value;
	}
	
	public static LogisticsStatus from(int value) {
		for(LogisticsStatus logisticsStatus : LogisticsStatus.values()) {
			if(logisticsStatus.getValue()==value) {
				return logisticsStatus;
			}
		}
		throw new IllegalArgumentException("Invalid ProposalStatus values: " + value);
	}
	
	 public static LogisticsStatus fromString(String value) {
		 return valueOf(value);
	 }
	 
	public int getValue() {
		return this.value;
	}
}
