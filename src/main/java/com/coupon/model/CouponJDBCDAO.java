package com.coupon.model;

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

public class CouponJDBCDAO implements CouponDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO coupon (COUPON_NAME, DISCOUNT_PERCENTAGE, DISCOUNT_AMOUNT, COUPON_START_DATETIME, COUPON_END_DATETIME) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT COUPON_ID, COUPON_NAME, DISCOUNT_PERCENTAGE, DISCOUNT_AMOUNT, COUPON_START_DATETIME, COUPON_END_DATETIME FROM coupon order by COUPON_ID";
	//**********Join方法:membercoupon表格 join coupon表格資訊*****************//
	private static final String GET_ALLCoupon_STMT = 
			"SELECT COUPON_ID, COUPON_NAME, DISCOUNT_PERCENTAGE, DISCOUNT_AMOUNT, COUPON_START_DATETIME, COUPON_END_DATETIME FROM coupon where COUPON_ID=?";
	private static final String GET_ONE_STMT = 
			"SELECT COUPON_ID, COUPON_NAME, DISCOUNT_PERCENTAGE, DISCOUNT_AMOUNT, COUPON_START_DATETIME, COUPON_END_DATETIME FROM coupon where COUPON_ID = ?";
	private static final String DELETE = 
			"DELETE FROM coupon where COUPON_ID = ?";
	private static final String UPDATE = 
			"UPDATE coupon set COUPON_NAME=? , DISCOUNT_PERCENTAGE=? , DISCOUNT_AMOUNT=? , COUPON_START_DATETIME=? , COUPON_END_DATETIME=?  where COUPON_ID = ?";
	

	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponVO.getCouponName());
			pstmt.setInt(2, couponVO.getDiscountPercentage());
			pstmt.setInt(3, couponVO.getDiscountAmount());
			pstmt.setTimestamp(4, couponVO.getCouponStartDatetime());
			pstmt.setTimestamp(5, couponVO.getCouponEndDatetime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getCouponName());
			pstmt.setInt(2, couponVO.getDiscountPercentage());
			pstmt.setInt(3, couponVO.getDiscountAmount());
			pstmt.setTimestamp(4, couponVO.getCouponStartDatetime());
			pstmt.setTimestamp(5, couponVO.getCouponEndDatetime());
			pstmt.setInt(6, couponVO.getCouponID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public void delete(Integer couponID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, couponID);

			pstmt.executeUpdate();

		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public CouponVO findByPrimaryKey(Integer couponID) {
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, couponID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 嚙稽嚙誶穿蕭 Domain objects
				couponVO = new CouponVO();
				couponVO.setCouponID(rs.getInt("COUPON_ID"));
				couponVO.setCouponName(rs.getString("COUPON_NAME"));
				couponVO.setDiscountPercentage(rs.getInt("DISCOUNT_PERCENTAGE"));
				couponVO.setDiscountAmount(rs.getInt("DISCOUNT_AMOUNT"));
				couponVO.setCouponStartDatetime(rs.getTimestamp("COUPON_START_DATETIME"));
				couponVO.setCouponEndDatetime(rs.getTimestamp("COUPON_END_DATETIME"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
		return couponVO;
	}

	

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				couponVO = new CouponVO();
				couponVO.setCouponID(rs.getInt("COUPON_ID"));
				couponVO.setCouponName(rs.getString("COUPON_NAME"));
				couponVO.setDiscountPercentage(rs.getInt("DISCOUNT_PERCENTAGE"));
				couponVO.setDiscountAmount(rs.getInt("DISCOUNT_AMOUNT"));
				couponVO.setCouponStartDatetime(rs.getTimestamp("COUPON_START_DATETIME"));
				couponVO.setCouponEndDatetime(rs.getTimestamp("COUPON_END_DATETIME"));
				list.add(couponVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//**********Join方法:membercoupon表格 join coupon表格資訊*****************//
//兩個表格有相同的欄位couponID所以帶入couponID來找membercoupon其他資訊
	@Override
	public CouponVO getTheCoupon(Integer couponID) {
		CouponVO couponVO = new CouponVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLCoupon_STMT);
			pstmt.setInt(1, couponID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				couponVO.setCouponID(rs.getInt("COUPON_ID"));
				couponVO.setCouponName(rs.getString("COUPON_NAME"));
				couponVO.setDiscountPercentage(rs.getInt("DISCOUNT_PERCENTAGE"));
				couponVO.setDiscountAmount(rs.getInt("DISCOUNT_AMOUNT"));
				couponVO.setCouponStartDatetime(rs.getTimestamp("COUPON_START_DATETIME"));
				couponVO.setCouponEndDatetime(rs.getTimestamp("COUPON_END_DATETIME"));
			}
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
		return couponVO;
		
	}
	
//	public static void main(String[] args) {
//
//		CouponJDBCDAO dao = new CouponJDBCDAO();

//		// 新增
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCouponName("測試優惠券");
//		couponVO1.setDiscountPercentage(0);
//		couponVO1.setDiscountAmount(100);
//		couponVO1.setCouponStartDatetime(java.sql.Timestamp.valueOf("2022-12-30 00:00:00"));
//		couponVO1.setCouponEndDatetime(java.sql.Timestamp.valueOf("2023-01-02 00:00:00"));
//		dao.insert(couponVO1);

//		//  修改
//		CouponVO couponVO2 = new CouponVO();
//		couponVO2.setCouponName("皜祈岫?嚙踝蕭??嚙賢2");
//		couponVO2.setDiscountPercentage(10);
//		couponVO2.setDiscountAmount(0);
//		couponVO2.setCouponStartDatetime(java.sql.Timestamp.valueOf("2022-12-30 00:00:00"));
//		couponVO2.setCouponEndDatetime(java.sql.Timestamp.valueOf("2023-01-05 00:00:00"));
//		couponVO2.setCouponID(3);
//		dao.update(couponVO2);
//		
		// 刪除
//		dao.delete(4);
////		
//		// 單一查詢
//		CouponVO couponVO3 = dao.findByPrimaryKey(2);
//		System.out.print(couponVO3.getCouponID() + ",");
//		System.out.print(couponVO3.getCouponName() + ",");
//		System.out.print(couponVO3.getDiscountPercentage() + ",");
//		System.out.print(couponVO3.getDiscountAmount() + ",");
//		System.out.print(couponVO3.getCouponStartDatetime() + ",");
//		System.out.println(couponVO3.getCouponEndDatetime());
//		System.out.println("---------------------");
//		
//		
//		// 全部查詢
//		List<CouponVO> list = dao.getAll();
//		for (CouponVO aCoupon : list) {
//			System.out.print(aCoupon.getCouponID() + ",");
//			System.out.print(aCoupon.getCouponName() + ",");
//			System.out.print(aCoupon.getDiscountPercentage() + ",");
//			System.out.print(aCoupon.getDiscountAmount() + ",");
//			System.out.print(aCoupon.getCouponStartDatetime() + ",");
//			System.out.print(aCoupon.getCouponEndDatetime());
//			System.out.println();
//		}
		
//	}
}
