package com.order.model;

import java.util.List;


public interface OrderDAO_interface {
	    public void insert(OrderVO orderVO);
	    public void update(OrderVO orderVO);
	    public void updateStatus(OrderVO orderVO);
	    public void delete(Integer orderId); //有PK都要放
	    public OrderVO findByPrimaryKey(Integer orderId);  //有PK都要放
	    public List<OrderVO> getAll();
	    //萬用複合查詢(傳入參數型態Map)(回傳 List)
	//  public List<EmpVO> getAll(Map<String, String[]> map);
	  //********************列出會員自己的訂單(類似getall)*************************//		    
		List<OrderVO> getMmeOrder(Integer memberId);
		public void updateAddress(OrderVO orderVO);
	}
