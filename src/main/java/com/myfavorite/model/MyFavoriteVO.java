package com.myfavorite.model;

public class MyFavoriteVO implements java.io.Serializable {
	private Integer memberId;
	private Integer productId;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	// **********Join方法:myfavorite表格 join shopProd表格資訊*****************//
//		注意JSP中需使用  ${myFavoriteVO.getTheShopProd().ShopProdVO中的屬姓名}
	public com.shopprod.model.ShopProdVO getTheShopProd() {
		com.shopprod.model.ShopProdService shopProdService = new com.shopprod.model.ShopProdService();
		com.shopprod.model.ShopProdVO shopProdVO = shopProdService.getTheShopProd(productId);
		return shopProdVO;
	}

}
