package com.shopprod.model;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class ShopProdService {

	private ShopProdDAO_interface dao;

	public ShopProdService() {
		dao = new ShopProdJDBCDAO();
	}
	
	public static void main(String[] args) {
//		OrderListJDBCDAO dao = new OrderListJDBCDAO();
//
//		// 測試新增
//		OrderListVO testInsert = new OrderListVO();
//		testInsert.setOrderId(2);
//		testInsert.setProdOptId(2);
//		testInsert.setProdOptQuantity(1111);
//		testInsert.setOrderExtendedListPrice(1111);
//		dao.insert(testInsert);
		
	}

	public ShopProdVO addShopProd(Integer productId, Integer productTypeId, Integer proposalId, String productDescription, InputStream productPicture,
			String productName, Integer productStatus) {
		ShopProdVO shopProdVO = new ShopProdVO();
		shopProdVO.setProductId(productId);
		shopProdVO.setProductTypeId(productTypeId);
		shopProdVO.setProposalId(proposalId);
		shopProdVO.setProductDescription(productDescription);
		shopProdVO.setProductPicture(productPicture);
		shopProdVO.setProductName(productName);
		shopProdVO.setProductStatus(productStatus);
		dao.insert(shopProdVO);
		System.out.println("dao.insert(shopProdVO)"); // for track
		return shopProdVO;
	}
	
	public ShopProdVO addGeneralShopProd(Integer productTypeId, String productDescription, InputStream productPicture, String productName) {

		ShopProdVO shopProdVO = new ShopProdVO();
		
		shopProdVO.setProductTypeId(productTypeId);
		shopProdVO.setProductDescription(productDescription);
		shopProdVO.setProductPicture(productPicture);
		shopProdVO.setProductName(productName);
		shopProdVO.setProductStatus(1);
		dao.insertGeneralShopProduct(shopProdVO);
		System.out.println("dao.insert(shopProdVO)"); // for track
		return shopProdVO;
	}

	// For Struts2 or SpringMVC use
	public void addShopProd(ShopProdVO shopProdVO) {
		dao.insert(shopProdVO);
		System.out.println("dao.insert(shopProdVO)"); // for track
	}

	public ShopProdVO updateShopProd(Integer productId, Integer productTypeId, Integer proposalId, String productDescription,
			InputStream productPicture, String productName, Integer productStatus) {
		ShopProdVO shopProdVO = new ShopProdVO();
		shopProdVO.setProductId(productId);
		shopProdVO.setProductTypeId(productTypeId);
		shopProdVO.setProposalId(proposalId);
		shopProdVO.setProductDescription(productDescription);
		shopProdVO.setProductPicture(productPicture);
		shopProdVO.setProductName(productName);
		shopProdVO.setProductStatus(productStatus);
		dao.update(shopProdVO);
		System.out.println("dao.update(shopProdVO)"); // for track
		return dao.findByPrimaryKey(productId);
	}
	
	
	// **********Join方法:myfavorite表格 join shopProd表格資訊*****************//
		public ShopProdVO getTheShopProd(Integer productId) {
			return dao.getTheShopProd(productId);
		}

	// For Struts2 or SpringMVC use
	public void updateShopProd(ShopProdVO shopProdVO) {
		dao.insert(shopProdVO);
		System.out.println("dao.insert(shopProdVO)"); // for track
	}

	public void deleteShopProd(Integer productId) {
		dao.delete(productId);
		System.out.println("dao.delete(productId)"); // for track
	}

	public ShopProdVO getOneShopProd(Integer productId) {
		System.out.println("return dao.findByPrimaryKey(productId)"); // for track
		return dao.findByPrimaryKey(productId);
	}

	public List<ShopProdVO> getAll() {
//		System.out.println("return dao.getAll()"); // for track
		return dao.getAll();
	}
	
	public List<ShopProdVO> findByType(Integer productTypeId) {
		
		return dao.findByType(productTypeId);
	}
	// *******************查詢商品(模糊查詢)*******************//
	public List<ShopProdVO> selectKeywordProductName(String productName) {
		return dao.selectKeywordProductName(productName);
	}


}
