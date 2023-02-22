package com.prodtype.model;

import java.util.List;

public interface ProdTypeDAO_interface {
	
	public void insert(ProdTypeVO prodTypeVO);
//	public void delete(ProdTypeVO prodTypeVO);
	public void delete(Integer productTypeId);
	public void update(ProdTypeVO prodTypeVO);
	public List<ProdTypeVO> getAll();
	public ProdTypeVO findByPrimaryKey(Integer productTypeId);
	
	
	

}
