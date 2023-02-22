package com.order.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderVO {
	private Integer orderId;
	private Integer memberId;
	private String orderAddress; 
	private String couponCodeNumber;
	private OrderStatusStatus orderStatus;	//型別改成OrderStatusStatus,按新鍵的	
	private OrderPaymentStatus orderPayment;//型別改成OrderPaymentStatus,按新建的
	private OrderDeliveryStatus orderDelivery;//型別改成OrderDeliveryStatus,按新建的
	private Integer orderTotalAmount;
	private Integer orderDiscount;
	private Integer orderAfterDiscount;
	private Timestamp orderTime;
	private Integer productOptionId;

	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getCouponCodeNumber() {
		return couponCodeNumber;
	}
	public void setCouponCodeNumber(String couponCodeNumber) {
		this.couponCodeNumber = couponCodeNumber;
	}
//	以下型別改成OrderStatusStatus
	public OrderStatusStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatusStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
//	以上型別改成OrderPaymentStatus	
	public OrderPaymentStatus getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(OrderPaymentStatus orderPayment) {
		this.orderPayment = orderPayment;
	}
//**********以下型別改成OrderDeliveryStatus**********
	public OrderDeliveryStatus getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(OrderDeliveryStatus orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	public Integer getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(Integer orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public Integer getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(Integer orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public Integer getOrderAfterDiscount() {
		return orderAfterDiscount;
	}
	public void setOrderAfterDiscount(Integer orderAfterDiscount) {
		this.orderAfterDiscount = orderAfterDiscount;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}	

	//為了join新增的
	public Integer getProductOptionId() {
		return productOptionId;
	}
	public void setProductOptionId(Integer productOptionId) {
		this.productOptionId = productOptionId;
	}
	


}
