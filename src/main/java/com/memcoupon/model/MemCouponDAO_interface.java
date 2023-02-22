package com.memcoupon.model;

import java.util.*;

public interface MemCouponDAO_interface {
	public void insert(MemCouponVO memCouponVO);

	public void update(MemCouponVO memCouponVO);

	public void delete(String couponCodeNumber);

	public MemCouponVO findByPrimaryKey(String couponCodeNumber);

	public List<MemCouponVO> getAll();
//********************列出會員自己的優惠券*************************//		
	public 	List<MemCouponVO> getMmeCoupon(Integer memberId);

	public void insertMemCouponVO(MemCouponVO memCouponVO);



}
