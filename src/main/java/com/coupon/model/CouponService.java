package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponService {
	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponJDBCDAO();
	}

	public CouponVO addCoupon(Integer couponID, String couponName, Integer discountPercentage, Integer discountAmount,
			Timestamp couponStartDatetime, Timestamp couponEndDatetime) {
		CouponVO couponVO = new CouponVO();
		
		couponVO.setCouponName(couponName);
		couponVO.setDiscountPercentage(discountPercentage);
		couponVO.setDiscountAmount(discountAmount);
		couponVO.setCouponStartDatetime(couponStartDatetime);
		couponVO.setCouponEndDatetime(couponEndDatetime);
		dao.insert(couponVO);
		
		return couponVO;
	}

	public CouponVO updateCoupon(Integer couponID, String couponName, Integer discountPercentage, Integer discountAmount,
			Timestamp couponStartDatetime, Timestamp couponEndDatetime) {
		
		

		CouponVO couponVO = new CouponVO();
		
		couponVO.setCouponName(couponName);
		couponVO.setDiscountPercentage(discountPercentage);
		couponVO.setDiscountAmount(discountAmount);
		couponVO.setCouponStartDatetime(couponStartDatetime);
		couponVO.setCouponEndDatetime(couponEndDatetime);
		couponVO.setCouponID(couponID);
		dao.update(couponVO);

		return couponVO;
	}

	public void delete(Integer couponID) {
		dao.delete(couponID);
	}

	public CouponVO getOneCoupon(Integer couponID) {
		return dao.findByPrimaryKey(couponID);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
	
//**********Join方法:membercoupon表格 join coupon表格資訊*****************
	public CouponVO getTheCoupon(Integer couponID) {
		return dao.getTheCoupon(couponID);
	}

}
