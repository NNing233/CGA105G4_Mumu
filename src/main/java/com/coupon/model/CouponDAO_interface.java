package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);

	public void update(CouponVO couponVO);

	public void delete(Integer couponID);

	public CouponVO findByPrimaryKey(Integer couponID);

	public List<CouponVO> getAll();
//**********Join方法:membercoupon表格 join coupon表格資訊*****************
	public CouponVO getTheCoupon(Integer couponID);
}
