package com.order.model;



public enum OrderDeliveryStatus {

	黑貓宅急便(1), 新竹物流(2), 嘉里大榮物流(3);

	private int value;
	OrderDeliveryStatus(int value) {
		this.value = value;
	}

	public static OrderDeliveryStatus from(int value) {
		for (OrderDeliveryStatus orderDeliveryStatus : OrderDeliveryStatus.values()) {
			if (orderDeliveryStatus.getValue() == value) {
				System.out.println("orderDeliveryStatus:" + orderDeliveryStatus);
				return orderDeliveryStatus;
			}
		}
		throw new IllegalArgumentException("Invalid status value: " + value);
	}

	public int getValue() {
		
		return this.value;
	}

}