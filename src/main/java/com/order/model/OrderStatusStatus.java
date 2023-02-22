package com.order.model;



public enum OrderStatusStatus {

	待出貨(1), 已出貨(2), 已到貨(3), 訂單完成(4), 換貨(5), 退貨(6), 已取消(7);

	private int value;
	OrderStatusStatus(int value) {
		this.value = value;
	}

	public static OrderStatusStatus from(int value) {
		for (OrderStatusStatus orderStatusStatus : OrderStatusStatus.values()) {
			if (orderStatusStatus.getValue() == value) {
				System.out.println("orderStatusStatus:" + orderStatusStatus);
				return orderStatusStatus;
			}
		}
		throw new IllegalArgumentException("Invalid status value: " + value);
	}

	public int getValue() {
		
		return this.value;
	}

}