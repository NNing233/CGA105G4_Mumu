package com.shopprod.model;

import java.util.List;

public interface ShopProdDAO_interface {
	public void insert(ShopProdVO shopProdVO);
	public void update(ShopProdVO shopProdVO);
	public void delete(Integer productId);	
	public ShopProdVO findByPrimaryKey(Integer shopProdVO);
	public List<ShopProdVO> getAll();
	public List<ShopProdVO> findByType(Integer productTypeId);
	public void insertGeneralShopProduct(ShopProdVO shopProdVO);
	
//	// *******************模糊查詢(回傳 List)*******************//		
	public List<ShopProdVO> selectKeywordProductName();
	List<ShopProdVO> selectKeywordProductName(String productName);
	public ShopProdVO getTheShopProd(Integer productId);
}
