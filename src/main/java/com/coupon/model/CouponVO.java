package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer couponID;
	private String couponName;
	private Integer discountPercentage;
	private Integer discountAmount;
	private Timestamp couponStartDatetime;
	private Timestamp couponEndDatetime;

	public Integer getCouponID() {
		return couponID;
	}

	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Timestamp getCouponStartDatetime() {
		return couponStartDatetime;
	}

	public void setCouponStartDatetime(Timestamp couponStartDatetime) {
		this.couponStartDatetime = couponStartDatetime;
	}

	public Timestamp getCouponEndDatetime() {
		return couponEndDatetime;
	}

	public void setCouponEndDatetime(Timestamp couponEndDatetime) {
		this.couponEndDatetime = couponEndDatetime;
	
	}

}
