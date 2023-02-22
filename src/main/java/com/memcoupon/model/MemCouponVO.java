package com.memcoupon.model;

import java.util.List;

public class MemCouponVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String couponCodeNumber;
	private Integer memberID;
	private Integer couponID;
	private String couponName;
	private CouponStatusStatus couponStatus;//型別改成couponStatus,按新建的

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
//**********以下型別改成CouponStatusStatus**********
	public CouponStatusStatus getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(CouponStatusStatus couponStatus) {
		this.couponStatus = couponStatus;
	}
//**********Join方法:membercoupon表格 join coupon表格資訊**********	
//	JSP中需使用  ${memCouponVO.getTheCoupon().coupon的屬姓名}
	public com.coupon.model.CouponVO getTheCoupon(){
		com.coupon.model.CouponService couponService = new com.coupon.model.CouponService();
		com.coupon.model.CouponVO couponVO = couponService.getTheCoupon(couponID);
		return couponVO;
	}

}
