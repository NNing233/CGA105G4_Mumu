package com.prodtype.model;

import java.io.Serializable;

public class ProdTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer productTypeId;
	private String productTypeName;

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

}
