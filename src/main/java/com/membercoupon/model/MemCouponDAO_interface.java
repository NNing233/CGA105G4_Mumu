package com.membercoupon.model;

import java.util.*;

public interface MemCouponDAO_interface {
	public void insert(MemCouponVO memCouponVO);

	public void update(MemCouponVO memCouponVO);

	public void delete(String couponCodeNumber);

	public MemCouponVO findByPrimaryKey(String couponCodeNumber);

	public List<MemCouponVO> getAll();
}
