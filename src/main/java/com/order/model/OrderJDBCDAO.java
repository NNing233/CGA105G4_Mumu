package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderJDBCDAO implements OrderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO `order` (order_Id,member_Id,order_Address,coupon_Code_Number,order_Status,order_Payment,order_Delivery,order_Total_Amount,order_Discount,order_After_Discount,order_Time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// 有自動編碼就不用填這行
	private static final String GET_ALL_STMT = "SELECT order_Id,member_Id,order_Address,coupon_Code_Number,order_Status,order_Payment,order_Delivery,order_Total_Amount,order_Discount,order_After_Discount,order_Time FROM `order` order by order_Id";
	private static final String GET_ONE_STMT = "SELECT order_Id,member_Id,order_Address,coupon_Code_Number,order_Status,order_Payment,order_Delivery,order_Total_Amount,order_Discount,order_After_Discount,order_Time FROM `order` where order_Id = ?"; // where
	private static final String DELETE = "DELETE FROM `order` where order_Id = ?";
	private static final String UPDATE = "UPDATE `order` set member_Id=?, order_Address=?, coupon_Code_Number=?, order_Status=?, order_Payment=?, order_Delivery=?, order_Total_Amount=?, order_Discount=?, order_After_Discount=?, order_Time=? where order_Id = ?";// 前面欄位不應該在這行有pk因為pk無法改
	private static final String ORDER_UPDATE = "UPDATE `order` set order_Status=? where order_Id = ?";// 前面欄位不應該在這行有pk因為pk無法改
	private static final String GET_MEM_ORDER_BY_MEM_ID = "SELECT * FROM `order` where member_Id=?";

	private static final String UPDATEADDRESS = "UPDATE `order` set order_Address=? where order_Id = ?";
	
	
	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getOrderId());
			pstmt.setInt(2, orderVO.getMemberId());
			pstmt.setString(3, orderVO.getOrderAddress());
			pstmt.setString(4, orderVO.getCouponCodeNumber());
			pstmt.setInt(5, orderVO.getOrderStatus().getValue());// 以上型別改成OrderPaymentStatus，所以加上getValue())
			pstmt.setInt(6, orderVO.getOrderPayment().getValue());// 以上型別改成OrderPaymentStatus，所以加上getValue())
			pstmt.setInt(7, orderVO.getOrderDelivery().getValue());// 以上型別改成OrderPaymentStatus，所以加上getValue())
			pstmt.setInt(8, orderVO.getOrderTotalAmount());
			pstmt.setInt(9, orderVO.getOrderDiscount());
			pstmt.setInt(10, orderVO.getOrderAfterDiscount());
			pstmt.setTimestamp(11, orderVO.getOrderTime());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderVO.getMemberId());
			pstmt.setString(2, orderVO.getOrderAddress());
			pstmt.setString(3, orderVO.getCouponCodeNumber());
//			pstmt.setInt(4, orderVO.getOrderStatus());			
			pstmt.setInt(4, orderVO.getOrderStatus().getValue());// 修改
			pstmt.setInt(5, orderVO.getOrderPayment().getValue());// 修改
			pstmt.setInt(6, orderVO.getOrderDelivery().getValue());// 修改
			pstmt.setInt(7, orderVO.getOrderTotalAmount());
			pstmt.setInt(8, orderVO.getOrderDiscount());
			pstmt.setInt(9, orderVO.getOrderAfterDiscount());
			pstmt.setInt(10, orderVO.getOrderId());
			pstmt.setTimestamp(11, orderVO.getOrderTime());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	// *********************修改會員訂單資料(只有地址)*************************//
			@Override
			public void updateAddress(OrderVO orderVO) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATEADDRESS);

					pstmt.setString(1, orderVO.getOrderAddress());
					pstmt.setInt(2, orderVO.getOrderId());
					pstmt.executeUpdate();

					// Handle any SQL errors
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. " + se.getMessage());
					// Clean up JDBC resources
				} finally {
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException se) {
							se.printStackTrace(System.err);
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							e.printStackTrace(System.err);
						}
					}
				}

			}	
	

	@Override
	public void delete(Integer productOptionId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productOptionId);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public OrderVO findByPrimaryKey(Integer productOptionId) {

		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productOptionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("order_Id"));
				orderVO.setMemberId(rs.getInt("member_Id"));
				orderVO.setOrderAddress(rs.getString("order_Address"));
				orderVO.setCouponCodeNumber(rs.getString("coupon_Code_Number"));
//				orderVO.setOrderStatus(rs.getInt("order_Status"));				
				// 型別改成OrderStatusStatus
				orderVO.setOrderStatus(OrderStatusStatus.from(rs.getInt("order_Status")));
				orderVO.setOrderPayment(OrderPaymentStatus.from(rs.getInt("order_Payment")));
				orderVO.setOrderDelivery(OrderDeliveryStatus.from(rs.getInt("order_Delivery")));
				orderVO.setOrderTotalAmount(rs.getInt("order_Total_Amount"));
				orderVO.setOrderDiscount(rs.getInt("order_Discount"));
				orderVO.setOrderAfterDiscount(rs.getInt("order_After_Discount"));
				orderVO.setOrderTime(rs.getTimestamp("order_Time"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("order_Id"));
				orderVO.setMemberId(rs.getInt("member_Id"));
				orderVO.setOrderAddress(rs.getString("order_Address"));
				orderVO.setCouponCodeNumber(rs.getString("coupon_Code_Number"));
//				orderVO.setOrderStatus(rs.getInt("order_Status"));				
				orderVO.setOrderStatus(OrderStatusStatus.from(rs.getInt("order_Status")));// 修改
				orderVO.setOrderPayment(OrderPaymentStatus.from(rs.getInt("order_Payment")));// 修改
				orderVO.setOrderDelivery(OrderDeliveryStatus.from(rs.getInt("order_Delivery")));// 修改
//				orderVO.setOrderDelivery(rs.getInt("order_Delivery"));
				orderVO.setOrderTotalAmount(rs.getInt("order_Total_Amount"));
				orderVO.setOrderDiscount(rs.getInt("order_Discount"));
				orderVO.setOrderAfterDiscount(rs.getInt("order_After_Discount"));
				orderVO.setOrderTime(rs.getTimestamp("order_Time"));
				list.add(orderVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void updateStatus(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ORDER_UPDATE);

			pstmt.setInt(1, orderVO.getOrderStatus().getValue());
			pstmt.setInt(2, orderVO.getOrderId());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

//********************列出會員自己的訂單(類似getall)*************************//	
	@Override
	// 需先拿到會員編號才能找到會員自己的優惠券-->getMmeOrder(Integer memberID)
	public List<OrderVO> getMmeOrder(Integer memberId) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ORDER_BY_MEM_ID);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("order_Id"));
				orderVO.setMemberId(rs.getInt("member_Id"));
				orderVO.setOrderAddress(rs.getString("order_Address"));
				orderVO.setCouponCodeNumber(rs.getString("coupon_Code_Number"));
//					orderVO.setOrderStatus(rs.getInt("order_Status"));				
				orderVO.setOrderStatus(OrderStatusStatus.from(rs.getInt("order_Status")));// 修改
				orderVO.setOrderPayment(OrderPaymentStatus.from(rs.getInt("order_Payment")));// 修改
				orderVO.setOrderDelivery(OrderDeliveryStatus.from(rs.getInt("order_Delivery")));// 修改
//					orderVO.setOrderDelivery(rs.getInt("order_Delivery"));
				orderVO.setOrderTotalAmount(rs.getInt("order_Total_Amount"));
				orderVO.setOrderDiscount(rs.getInt("order_Discount"));
				orderVO.setOrderAfterDiscount(rs.getInt("order_After_Discount"));
				orderVO.setOrderTime(rs.getTimestamp("order_Time"));
				list.add(orderVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}
}
