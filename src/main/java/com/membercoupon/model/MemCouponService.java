package com.membercoupon.model;

import java.util.List;

public class MemCouponService {
	private MemCouponDAO_interface dao;

	public MemCouponService() {
		dao = new MemCouponJDBCDAO();
	}

	public MemCouponVO addEmp(String couponCodeNumber, Integer memberID, Integer couponID,
			String couponName, Byte couponStatus) {

		MemCouponVO memCouponVO = new MemCouponVO();
		
		memCouponVO.setCouponCodeNumber(couponCodeNumber);
		memCouponVO.setMemberID(memberID);
		memCouponVO.setCouponID(couponID);
		memCouponVO.setCouponName(couponName);
		memCouponVO.setCouponStatus(couponStatus);
		dao.insert(memCouponVO);

		return memCouponVO;
	}

	public MemCouponVO updateMemCoupon(String couponCodeNumber, Integer memberID, Integer couponID,
			String couponName, Byte couponStatus) {

		MemCouponVO memCouponVO = new MemCouponVO();
		
		memCouponVO.setCouponCodeNumber(couponCodeNumber);
		memCouponVO.setMemberID(memberID);
		memCouponVO.setCouponID(couponID);
		memCouponVO.setCouponName(couponName);
		memCouponVO.setCouponStatus(couponStatus);
		dao.update(memCouponVO);

		return memCouponVO;
	}

	public void deleteEmp(String couponCodeNumber) {
		dao.delete(couponCodeNumber);
	}

	public MemCouponVO getOneEmp(String couponCodeNumber) {
		return dao.findByPrimaryKey(couponCodeNumber);
	}

	public List<MemCouponVO> getAll() {
		return dao.getAll();
	}
}
