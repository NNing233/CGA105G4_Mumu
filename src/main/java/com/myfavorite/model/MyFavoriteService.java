package com.myfavorite.model;

import java.util.List;

import com.order.model.OrderVO;


public class MyFavoriteService {

	private MyFavoriteDAO_interface dao;

	public MyFavoriteService() {
		dao = new MyFavoriteJDBCDAO();
	}

	public MyFavoriteVO addMyFavorite(Integer productId, Integer memberId) {

		MyFavoriteVO myFavoriteVO = new MyFavoriteVO();

		myFavoriteVO.setProductId(productId);
		myFavoriteVO.setMemberId(memberId);
		dao.insert(myFavoriteVO);

		return myFavoriteVO;
	}

	public MyFavoriteVO updateMyFavorite(Integer memberId, Integer productId) {

		MyFavoriteVO myFavoriteVO = new MyFavoriteVO();

		myFavoriteVO.setProductId(memberId);
		myFavoriteVO.setProductId(productId);
		dao.update(myFavoriteVO, myFavoriteVO);

		return myFavoriteVO;
	}
//********************刪除特定會員收藏的特定商品*************************//	
	public void deleteMyFavorite(Integer memberId, Integer productId) {
		dao.delete(memberId, productId);
	}

	public MyFavoriteVO getOneMyFavorite(Integer memberId, Integer productId) {
		return dao.findByPrimaryKey(memberId, productId);
	}

	public List<MyFavoriteVO> getAll() {
		return dao.getAll();
	}

//********************列出會員收藏的商品*************************//		
			public List<MyFavoriteVO> getMmeFavorite(Integer memberId) {
				
				MyFavoriteVO myFavoriteVO = new MyFavoriteVO();
				myFavoriteVO.setMemberId(memberId);
				return dao.getMmeFavorite(memberId);
			}	
}