package com.prodopt.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

public class ProdOptVO implements java.io.Serializable {
	private Integer productOptionId;
	private Integer productId;
	private InputStream productOptionPicture;
	private String productOptionName;
	private Integer productOptionPrice;
	private Integer productOptionInventory;

	public Integer getProductOptionId() {
		return productOptionId;
	}

	public void setProductOptionId(Integer productOptionId) {
		this.productOptionId = productOptionId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public InputStream getProductOptionPicture() {
		return productOptionPicture;
	}

	public void setProductOptionPicture(InputStream productOptionPicture) {
		this.productOptionPicture = productOptionPicture;
	}

	public String getProductOptionName() {
		return productOptionName;
	}

	public void setProductOptionName(String productOptionName) {
		this.productOptionName = productOptionName;
	}

	public Integer getProductOptionPrice() {
		return productOptionPrice;
	}

	public void setProductOptionPrice(Integer productOptionPrice) {
		this.productOptionPrice = productOptionPrice;
	}

	public Integer getProductOptionInventory() {
		return productOptionInventory;
	}

	public void setProductOptionInventory(Integer productOptionInventory) {
		this.productOptionInventory = productOptionInventory;
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
			bytes = IOUtils.toByteArray(this.productOptionPicture);
			
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
