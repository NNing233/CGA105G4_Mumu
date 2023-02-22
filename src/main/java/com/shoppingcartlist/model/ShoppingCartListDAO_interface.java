package com.shoppingcartlist.model;

import java.util.List;

public interface ShoppingCartListDAO_interface {

	    public void insert(ShoppingCartListVO shoppingCartVO);
	    public void update(ShoppingCartListVO shoppingCartVO);
	    public void delete(Integer shoppingCartListId); //有PK都要放
	    public ShoppingCartListVO findByPrimaryKey(Integer shoppingCartListId); //有PK都要放
	    public List<ShoppingCartListVO> getAll();
	    //萬用複合查詢(傳入參數型態Map)(回傳 List)
	//  public List<EmpVO> getAll(Map<String, String[]> map);
	    public List<ShoppingCartListVO> findByMemId(Integer memberId);
	}


