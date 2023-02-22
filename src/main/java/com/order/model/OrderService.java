package com.order.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderService {

	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderJDBCDAO();
	}

//型態改OrderStatusStatus
	public OrderVO addOrder(Integer memberId, String orderAddress, String couponCodeNumber,
			OrderStatusStatus orderStatus, OrderPaymentStatus orderPayment, OrderDeliveryStatus orderDelivery,
			Integer orderTotalAmount, Integer orderDiscount, Integer orderAfterDiscount) {

		OrderVO orderVO = new OrderVO();

//		orderVO.setOrderId(orderId); // orderId為自增主鍵, 移除該值
		orderVO.setMemberId(memberId);
		orderVO.setOrderAddress(orderAddress);
		orderVO.setCouponCodeNumber(couponCodeNumber);
		orderVO.setOrderStatus(orderStatus);
		orderVO.setOrderPayment(orderPayment);
		orderVO.setOrderDelivery(orderDelivery);
		orderVO.setOrderTotalAmount(orderTotalAmount);
		orderVO.setOrderDiscount(orderDiscount);
		orderVO.setOrderAfterDiscount(orderAfterDiscount);
		dao.insert(orderVO);

		return orderVO;
	}

//型態改OrderStatusStatus
	public OrderVO updateOrder(Integer orderId, Integer memberId, String orderAddress, String couponCodeNumber,
			OrderStatusStatus orderStatus, OrderPaymentStatus orderPayment, OrderDeliveryStatus orderDelivery,
			Integer orderTotalAmount, Integer orderDiscount, Integer orderAfterDiscount, Timestamp orderTime) {

		OrderVO orderVO = new OrderVO();

		orderVO.setOrderId(orderId);
		orderVO.setMemberId(memberId);
		orderVO.setOrderAddress(orderAddress);
		orderVO.setCouponCodeNumber(couponCodeNumber);
		orderVO.setOrderStatus(orderStatus);
		orderVO.setOrderPayment(orderPayment);
		orderVO.setOrderDelivery(orderDelivery);
		orderVO.setOrderTotalAmount(orderTotalAmount);
		orderVO.setOrderDiscount(orderDiscount);
		orderVO.setOrderAfterDiscount(orderAfterDiscount);
		dao.update(orderVO);

		return orderVO;
	}

	public void deleteOrder(Integer orderId) {
		dao.delete(orderId);
	}

	public OrderVO getOneOrder(Integer orderId) {
		return dao.findByPrimaryKey(orderId);
	}

	public List<OrderVO> getAll() {
		return dao.getAll();
	}

//==orderStatus更新狀態資料按鈕=========================================================================================//		
	public OrderVO updateOrderStatus(OrderStatusStatus orderStatus, Integer orderId) {
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderId(orderId);
		orderVO.setOrderStatus(orderStatus);
		dao.updateStatus(orderVO);

		return orderVO;
	}

//********************列出會員自己的訂單*************************//		
	public List<OrderVO> getMmeOrder(Integer memberId) {

		OrderVO orderVO = new OrderVO();
		orderVO.setMemberId(memberId);
		return dao.getMmeOrder(memberId);
	}

	// *********************修改訂單內容(只有地址)***************************//
	// 型態改OrderStatusStatus
	public OrderVO updateOrderAddress(String orderAddress, Integer orderId) {

		OrderVO couponVO = new OrderVO();

		couponVO.setOrderAddress(orderAddress);
		couponVO.setOrderId(orderId);
		dao.updateAddress(couponVO);

		return couponVO;
	}

}
