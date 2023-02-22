package com.order.model;



public enum OrderPaymentStatus {

	貨到付款(1), 信用卡(2), 轉帳(3);

	private int value;
	OrderPaymentStatus(int value) {
		this.value = value;
	}

	public static OrderPaymentStatus from(int value) {
		for (OrderPaymentStatus orderPaymentStatus : OrderPaymentStatus.values()) {
			if (orderPaymentStatus.getValue() == value) {
				System.out.println("orderPaymentStatus:" + orderPaymentStatus);
				return orderPaymentStatus;
			}
		}
		throw new IllegalArgumentException("Invalid status value: " + value);
	}

	public int getValue() {
		
		return this.value;
	}

}