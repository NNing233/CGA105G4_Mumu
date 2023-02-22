package com.prop.model;



public enum Status {

	不通過(0), 通過(1), 審核中(2);

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
