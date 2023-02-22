package com.shoppingcartlist.model;

import java.util.List;

public class ShoppingCartListService {

	private ShoppingCartListDAO_interface dao;

	public ShoppingCartListService() {
		dao = new ShoppingCartListJDBCDAO();// 我們只有做這個JDBCDAO()網站所以用這個 不然一班是叫DAO()
	}

	public ShoppingCartListVO addProdOpt(Integer productOptionId, Integer memberId, Integer productOptionQuantity) {

		ShoppingCartListVO shoppingCartVO = new ShoppingCartListVO();

		shoppingCartVO.setProductOptionId(productOptionId);
		shoppingCartVO.setMemberId(memberId);
		shoppingCartVO.setProductOptionQuantity(productOptionQuantity);
		dao.insert(shoppingCartVO);

		return shoppingCartVO;
	}

	public ShoppingCartListVO updateProdOpt(Integer shoppingCartListId, Integer productOptionId, Integer memberId,
			Integer productOptionQuantity) {
		ShoppingCartListVO shoppingCartVO = new ShoppingCartListVO();

		shoppingCartVO.setShoppingCartListId(shoppingCartListId);
		shoppingCartVO.setProductOptionId(productOptionId);
		shoppingCartVO.setMemberId(memberId);
		shoppingCartVO.setProductOptionQuantity(productOptionQuantity);

		dao.update(shoppingCartVO);

		return shoppingCartVO;
	}

	public void deleteProdOpt(Integer productOptionId) {
		dao.delete(productOptionId);
	}

	public ShoppingCartListVO getOneProdOpt(Integer shoppingCartListId) {
		return dao.findByPrimaryKey(shoppingCartListId);
	}

	public List<ShoppingCartListVO> getAll() {
		return dao.getAll();
	}

	public List<ShoppingCartListVO> getMemShopCartList(Integer memberId) {
		System.out.println("return 購物車dao.findByMemId(memberId)"); // for track
		return dao.findByMemId(memberId);
	}

}