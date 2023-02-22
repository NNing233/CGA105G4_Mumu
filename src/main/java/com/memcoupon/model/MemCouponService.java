package com.memcoupon.model;

import java.util.List;

public class MemCouponService {
	private MemCouponDAO_interface dao;

	public MemCouponService() {
		dao = new MemCouponJDBCDAO();
	}

	public MemCouponVO addMemCoupon(Integer memberID, Integer couponID,
			String couponName, CouponStatusStatus couponStatus) {

		MemCouponVO memCouponVO = new MemCouponVO();

		memCouponVO.setMemberID(memberID);
		memCouponVO.setCouponID(couponID);
		memCouponVO.setCouponName(couponName);
		memCouponVO.setCouponStatus(couponStatus);
		dao.insertMemCouponVO(memCouponVO);

		return memCouponVO;
	}


	public MemCouponVO updateMemCoupon(String couponCodeNumber, Integer memberID, Integer couponID,
			String couponName, CouponStatusStatus couponStatus) {

		MemCouponVO memCouponVO = new MemCouponVO();
		
		memCouponVO.setCouponCodeNumber(couponCodeNumber);
		memCouponVO.setMemberID(memberID);
		memCouponVO.setCouponID(couponID);
		memCouponVO.setCouponName(couponName);
		memCouponVO.setCouponStatus(couponStatus);
		dao.update(memCouponVO);

		return memCouponVO;
	}

	public void deleteMemCoupon(String couponCodeNumber) {
		dao.delete(couponCodeNumber);
	}

	public MemCouponVO getOneMemCoupon(String couponCodeNumber) {
		return dao.findByPrimaryKey(couponCodeNumber);
	}

	public List<MemCouponVO> getAll() {
		return dao.getAll();
	}

	
//********************列出會員自己的優惠券*************************//		
	public List<MemCouponVO> getMmeCoupon(Integer memberID) {
		
		MemCouponVO memCouponVO = new MemCouponVO();
		memCouponVO.setMemberID(memberID);
		return dao.getMmeCoupon(memberID);
	}	
	
	
}
