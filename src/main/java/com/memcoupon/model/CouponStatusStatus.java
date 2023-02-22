package com.memcoupon.model;



public enum CouponStatusStatus {

	未用(1), 已用(2), 已過期(3);

	private int value;
	CouponStatusStatus(int value) {
		this.value = value;
	}

	public static CouponStatusStatus from(int value) {
		for (CouponStatusStatus couponStatusStatus : CouponStatusStatus.values()) {
			if (couponStatusStatus.getValue() == value) {
				System.out.println("couponStatusStatus:" + couponStatusStatus);
				return couponStatusStatus;
			}
		}
		throw new IllegalArgumentException("Invalid status value: " + value);
	}

	public int getValue() {
		
		return this.value;
	}

}