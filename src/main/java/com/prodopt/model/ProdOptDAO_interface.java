package com.prodopt.model;

import java.util.List;


public interface ProdOptDAO_interface {
	    public void insert(ProdOptVO prodOptVO);
	    public void update(ProdOptVO prodOptVO);
	    public void delete(Integer productOptionId);
	    public ProdOptVO findByPrimaryKey(Integer productOptionId);
	    public List<ProdOptVO> getAll();
		public List<ProdOptVO> findByProdId(Integer productId);
//		public ProdOptVO findByProdId(Integer productId);
		
	//  public List<EmpVO> getAll(Map<String, String[]> map);
		
//**********Join方法:myfavorite表格 join propoption表格資訊*****************//		
		public ProdOptVO getTheProdOpt(Integer productId);		
	}
