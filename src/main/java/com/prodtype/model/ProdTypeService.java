package com.prodtype.model;

import java.util.List;

public class ProdTypeService {

	private ProdTypeDAO_interface dao;

	public ProdTypeService() {
		dao = new ProdTypeJDBCDAO();
	}

	public ProdTypeVO addProdType(String productTypeName) {
		ProdTypeVO ProdTypeVO = new ProdTypeVO();
		ProdTypeVO.setProductTypeName(productTypeName);
		dao.insert(ProdTypeVO);
		return ProdTypeVO;
	}

	// For Struts2 or SpringMVC use
	public void addProdType(ProdTypeVO ProdTypeVO) {
		dao.insert(ProdTypeVO);
	}

	public ProdTypeVO updateProdType(Integer productTypeId, String productTypeName) {
		ProdTypeVO ProdTypeVO = new ProdTypeVO();
		ProdTypeVO.setProductTypeId(productTypeId);
		ProdTypeVO.setProductTypeName(productTypeName);
		dao.update(ProdTypeVO);
		return dao.findByPrimaryKey(productTypeId);
	}

	// For Struts2 or SpringMVC use
	public void updateProdType(ProdTypeVO ProdTypeVO) {
		dao.insert(ProdTypeVO);
	}

	public void deleteProdType(Integer productTypeId) {
		dao.delete(productTypeId);
	}

	public ProdTypeVO getOneProdType(Integer productTypeId) {
		return dao.findByPrimaryKey(productTypeId);
	}

	public List<ProdTypeVO> getAll() {
		return dao.getAll();
	}

}
