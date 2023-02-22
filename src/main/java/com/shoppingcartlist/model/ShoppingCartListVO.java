package com.shoppingcartlist.model;

public class ShoppingCartListVO  implements java.io.Serializable {
	private Integer shoppingCartListId;
	private Integer productOptionId;
	private Integer memberId;
	private Integer productOptionQuantity;
	public Integer getShoppingCartListId() {
		return shoppingCartListId;
	}
	public void setShoppingCartListId(Integer shoppingCartListId) {
		this.shoppingCartListId = shoppingCartListId;
	}
	public Integer getProductOptionId() {
		return productOptionId;
	}
	public void setProductOptionId(Integer productOptionId) {
		this.productOptionId = productOptionId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProductOptionQuantity() {
		return productOptionQuantity;
	}
	public void setProductOptionQuantity(Integer productOptionQuantity) {
		this.productOptionQuantity = productOptionQuantity;
	}

	
}
