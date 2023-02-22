package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.memcoupon.model.CouponStatusStatus;
import com.orderlist.model.OrderListVO;

public class OrderListJDBCDAO implements OrderListDAO_interface {
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT = "INSERT INTO `order_list` (`ORDER_ID`, `PRODUCT_OPTION_ID`, `PRODUCT_OPTION_QUANTITY`, `ORDER_EXTENDED_LIST_PRICE`) VALUES ( ? , ? , ? , ? )";
	private static final String DELETE = "DELETE FROM `order_list` WHERE order_id = ? ";
	private static final String UPDATE = "UPDATE `order_list` SET `PRODUCT_OPTION_QUANTITY` = ? ,`ORDER_EXTENDED_LIST_PRICE` = ? WHERE `ORDER_ID` = ? AND `PRODUCT_OPTION_ID` = ? ";
	private static final String GET_ONE = "SELECT `ORDER_ID`, `PRODUCT_OPTION_ID`, `PRODUCT_OPTION_QUANTITY`, `ORDER_EXTENDED_LIST_PRICE` FROM `order_list` WHERE order_id = ? ";
	private static final String GET_ALL = "SELECT `order_list`.`ORDER_ID`, `order_list`.`PRODUCT_OPTION_ID`, `order_list`.`PRODUCT_OPTION_QUANTITY`, `order_list`.`ORDER_EXTENDED_LIST_PRICE` FROM `order_list` order by `order_id`";

	
	
	public static void main(String[] args) {

//		OrderListJDBCDAO dao = new OrderListJDBCDAO();
//

//		OrderListVO testInsert = new OrderListVO();
//		testInsert.setOrderId(2);
//		testInsert.setProdOptId(2);
//		testInsert.setProdOptQuantity(1111);
//		testInsert.setOrderExtendedListPrice(1111);
//		dao.insert(testInsert);

	
//		dao.delete(2);


//		OrderListVO testSelect = dao.findByPrimaryKey(2);
//		System.out.print(testSelect.getOrderId() + " ");
//		System.out.print(testSelect.getProdOptId() + " ");
//		System.out.print(testSelect.getProdOptQuantity() + " ");
//		System.out.print(testSelect.getOrderExtendedListPrice() + " ");

//		List<OrderListVO> list = dao.getAll();
//		for(OrderListVO element : list) {
//			System.out.print(element.getOrderId() + " ");
//			System.out.print(element.getProdOptId() + " ");
//			System.out.print(element.getProdOptQuantity() + " ");
//			System.out.print(element.getOrderExtendedListPrice() + " ");
//			System.out.println();
//		}

//		OrderListVO testUpdate = new OrderListVO();
//		testUpdate.setOrderId(2);
//		testUpdate.setProdOptId(3);
//		testUpdate.setProdOptQuantity(1111);
//		testUpdate.setOrderExtendedListPrice(111);
//		dao.update(testUpdate);

	}

	// ==============


	@Override
	public void insert(OrderListVO orderListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, orderListVO.getOrderId());
			pstmt.setInt(2, orderListVO.getProdOptId());
			pstmt.setInt(3, orderListVO.getProdOptQuantity());
			pstmt.setInt(4, orderListVO.getOrderExtendedListPrice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException( e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// ==============



	@Override
	public void delete(Integer orderId) {
		PreparedStatement pstmt = null;

		try {

			pstmt = ds.getConnection().prepareStatement(DELETE);

			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException( e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// ==============

	@Override
	public void update(OrderListVO orderListVO) {
		PreparedStatement pstmt = null;

		try {
			pstmt = ds.getConnection().prepareStatement(UPDATE);

			pstmt.setInt(1, orderListVO.getProdOptQuantity());
			pstmt.setInt(2, orderListVO.getOrderExtendedListPrice());
			pstmt.setInt(3, orderListVO.getOrderId());
			pstmt.setInt(4, orderListVO.getProdOptId());
			pstmt.executeUpdate();
			System.out.println();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// ==============


	@Override
	public OrderListVO findByPrimaryKey(Integer order_id) {
		OrderListVO orderListVO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = ds.getConnection().prepareStatement(GET_ONE);
			pstmt.setInt(1, order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderListVO = new OrderListVO();
				orderListVO.setOrderId(rs.getInt("order_id"));
				orderListVO.setProdOptId(rs.getInt("product_option_id"));
				orderListVO.setProdOptQuantity(rs.getInt("product_option_quantity"));
				orderListVO.setOrderExtendedListPrice(rs.getInt("order_extended_list_price"));
			}

		} catch (SQLException e) {
			throw new RuntimeException( e.getMessage());
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
		}

		return orderListVO;
	}

	// ==============


	@Override
	public List<OrderListVO> getAll() {
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		OrderListVO orderListVO = null;
		ResultSet rs = null;

		try {

			rs = ds.getConnection().prepareStatement(GET_ALL).executeQuery();

			while (rs.next()) {
				orderListVO = new OrderListVO();
				orderListVO.setOrderId(rs.getInt("order_id"));
				orderListVO.setProdOptId(rs.getInt("product_option_id"));
				orderListVO.setProdOptQuantity(rs.getInt("product_option_quantity"));
				orderListVO.setOrderExtendedListPrice(rs.getInt("order_extended_list_price"));
				list.add(orderListVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}



}
