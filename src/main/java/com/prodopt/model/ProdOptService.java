package com.prodopt.model;

import java.io.InputStream;
import java.util.List;

public class ProdOptService {

	private ProdOptDAO_interface dao;

	public ProdOptService() {
		dao = new ProdOptJDBCDAO();
	}

	public ProdOptVO addProdOpt(Integer productId, InputStream productOptionPicture, String productOptionName, Integer productOptionPrice,
			Integer productOptionInventory) {

		ProdOptVO prodOptVO = new ProdOptVO();

		prodOptVO.setProductId(productId);
		prodOptVO.setProductOptionPicture(productOptionPicture);
		prodOptVO.setProductOptionName(productOptionName);
		prodOptVO.setProductOptionPrice(productOptionPrice);
		prodOptVO.setProductOptionInventory(productOptionInventory);	
		dao.insert(prodOptVO);

		return prodOptVO;
	}

	public ProdOptVO updateProdOpt(Integer productOptionId, Integer productId, InputStream productOptionPicture, String productOptionName, Integer productOptionPrice,
			Integer productOptionInventory) {

		ProdOptVO prodOptVO = new ProdOptVO();

		prodOptVO.setProductOptionInventory(productOptionId);
		prodOptVO.setProductId(productId);
		prodOptVO.setProductOptionPicture(productOptionPicture);
		prodOptVO.setProductOptionName(productOptionName);
		prodOptVO.setProductOptionPrice(productOptionPrice);
		prodOptVO.setProductOptionInventory(productOptionInventory);
		dao.update(prodOptVO);	

		return prodOptVO;
	}

	public void deleteProdOpt(Integer productOptionId) {
		dao.delete(productOptionId);
	}

	public ProdOptVO getOneProdOpt(Integer productOptionId) {
		return dao.findByPrimaryKey(productOptionId);
	}

	public List<ProdOptVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProdOptVO> getProdOpts(Integer productId) {
		return dao.findByProdId(productId);
	}
	
//**********Join方法:membercoupon表格 join coupon表格資訊*****************
		public ProdOptVO getTheProdOpt(Integer productId) {
			return dao.getTheProdOpt(productId);
		}

}
