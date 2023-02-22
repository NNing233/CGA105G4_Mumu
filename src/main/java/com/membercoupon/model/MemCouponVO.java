package com.membercoupon.model;

public class MemCouponVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String couponCodeNumber;
	private Integer memberID;
	private Integer couponID;
	private String couponName;
	private Byte couponStatus;

	public String getCouponCodeNumber() {
		return couponCodeNumber;
	}

	public void setCouponCodeNumber(String couponCodeNumber) {
		this.couponCodeNumber = couponCodeNumber;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

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

	public Byte getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(Byte couponStatus) {
		this.couponStatus = couponStatus;
	}

}
