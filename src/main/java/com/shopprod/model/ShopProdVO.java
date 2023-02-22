package com.shopprod.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.prodopt.model.ProdOptVO;



public class ShopProdVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer productTypeId;
	private Integer proposalId;
	private String productDescription;
	private InputStream productPicture;
	private String productName;
	private Integer productStatus;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public InputStream getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(InputStream productPicture) {
		this.productPicture = productPicture;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	
    // join shop_product PRODUCT_TYPE_ID - product_type PRODUCT_TYPE_ID
	public com.prodtype.model.ProdTypeVO getProdTypeVO() {
		com.prodtype.model.ProdTypeService prodTypeSvc = new com.prodtype.model.ProdTypeService();
		com.prodtype.model.ProdTypeVO prodTypeVO = prodTypeSvc.getOneProdType(productTypeId);
		return prodTypeVO;
	}
	
    // join shop_product PRODUCT_TYPE_ID - product_option PRODUCT_ID
	public List<com.prodopt.model.ProdOptVO> getProdOptVO() {
		com.prodopt.model.ProdOptService prodOptSvc = new com.prodopt.model.ProdOptService();
		List<com.prodopt.model.ProdOptVO> prodOptVO = prodOptSvc.getProdOpts(productId);
		System.out.println("--------------------");
		System.out.println(productId);
		System.out.println(prodOptVO.size());
		System.out.println("--------------------");
		return prodOptVO;
	}

	public String getProductPictureString() {
	    System.out.println("調用:" + this.getClass().getName());

		// 拿圖片
	    byte[] bytes = null;
	    InputStream replaceImg = null;
	    
		try {
			// 設定資料庫沒圖片時, 從網路上拿404圖片代替
			replaceImg = new URL("https://i.imgur.com/AKraVVn.png").openStream();
			
			// 用org.apache.commons.io.IOUtils的方法去拿InputStream資料
			bytes = IOUtils.toByteArray(this.productPicture);
			
		} catch (Exception e) {
			System.out.println("DB裡的圖片為空值 : " + e.getMessage());
			
			if(bytes == null) {
				try {
					bytes = IOUtils.toByteArray(replaceImg);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	    String encoded = Base64.getEncoder().encodeToString(bytes); // 轉成base64字串
//		System.out.println(encoded);
	    return encoded;  // 當jsp從list集合拿base64字串時, 呼叫此方法並回傳給jsp

	}

}
