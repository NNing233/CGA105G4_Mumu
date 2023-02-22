package com.propprodreview.model;



public enum Status {

	待審核(1), 通過(2), 未通過(3);

	private int value;

	Status(int value) {
		this.value = value;
	}

	public static Status from(int value) {
		for (Status status : Status.values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid status value: " + value);
	}

	public int getValue() {
		
		return this.value;
	}

}
