package com.memcoupon.model;

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

import com.order.model.OrderDeliveryStatus;

public class MemCouponJDBCDAO implements MemCouponDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO member_coupon (COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS FROM member_coupon order by MEMBER_ID";
	//********************列出會員自己的優惠券*************************//	
	private static final String GET_MEM_COUPON_BY_MEM_ID = "SELECT * FROM member_coupon where MEMBER_ID=?";
	private static final String GET_ONE_STMT = "SELECT COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS FROM member_coupon where COUPON_CODE_NUMBER = ?";
	private static final String DELETE = "DELETE FROM member_coupon where COUPON_CODE_NUMBER = ?";
	private static final String UPDATE = "UPDATE member_coupon set MEMBER_ID=?, COUPON_ID=?, COUPON_NAME=?, COUPON_STATUS=? where COUPON_CODE_NUMBER=?";
	
	//********************insertMemCouponVO新增"全部"會員優惠券*************************//
	private static final String INSERT_MEMCOUPON_STMT = "INSERT INTO member_coupon (MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS) VALUES (?, ?, ?, ?)";
	
	

	@Override
	public void insert(MemCouponVO memCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memCouponVO.getCouponCodeNumber());
			pstmt.setInt(2, memCouponVO.getMemberID());
			pstmt.setInt(3, memCouponVO.getCouponID());
			pstmt.setString(4, memCouponVO.getCouponName());
			pstmt.setInt(5, memCouponVO.getCouponStatus().getValue());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(MemCouponVO memCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memCouponVO.getMemberID());
			pstmt.setInt(2, memCouponVO.getCouponID());
			pstmt.setString(3, memCouponVO.getCouponName());
			pstmt.setInt(4, memCouponVO.getCouponStatus().getValue());
			pstmt.setString(5, memCouponVO.getCouponCodeNumber());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void delete(String couponCodeNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, couponCodeNumber);

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public MemCouponVO findByPrimaryKey(String couponCodeNumber) {
		MemCouponVO memCouponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, couponCodeNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO �]�٬� Domain objects
				memCouponVO = new MemCouponVO();
				memCouponVO.setCouponCodeNumber(rs.getString("COUPON_CODE_NUMBER"));
				memCouponVO.setMemberID(rs.getInt("MEMBER_ID"));
				memCouponVO.setCouponID(rs.getInt("COUPON_ID"));
				memCouponVO.setCouponName(rs.getString("COUPON_NAME"));
				memCouponVO.setCouponStatus(CouponStatusStatus.from(rs.getByte("COUPON_STATUS")));
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
		return memCouponVO;
	}

	@Override
	public List<MemCouponVO> getAll() {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memCouponVO = new MemCouponVO();
				memCouponVO.setCouponCodeNumber(rs.getString("COUPON_CODE_NUMBER"));
				memCouponVO.setMemberID(rs.getInt("MEMBER_ID"));
				memCouponVO.setCouponID(rs.getInt("COUPON_ID"));
				memCouponVO.setCouponName(rs.getString("COUPON_NAME"));
				memCouponVO.setCouponStatus(CouponStatusStatus.from(rs.getByte("COUPON_STATUS")));

				list.add(memCouponVO); // Store the row in the list

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

	
//********************列出會員自己的優惠券*************************//	
	@Override
	//需先拿到會員編號才能找到會員自己的優惠券getMmeCoupon(Integer memberID)
	public List<MemCouponVO> getMmeCoupon(Integer memberID) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_COUPON_BY_MEM_ID);
			
			pstmt.setInt(1, memberID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				memCouponVO = new MemCouponVO();
				memCouponVO.setCouponCodeNumber(rs.getString("COUPON_CODE_NUMBER"));
				memCouponVO.setMemberID(rs.getInt("MEMBER_ID"));
				memCouponVO.setCouponID(rs.getInt("COUPON_ID"));
				memCouponVO.setCouponName(rs.getString("COUPON_NAME"));
				memCouponVO.setCouponStatus(CouponStatusStatus.from(rs.getByte("COUPON_STATUS")));

				list.add(memCouponVO); // Store the row in the list

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

//********************insertMemCouponVO新增"全部"會員優惠券*************************//	
	@Override
	public void insertMemCouponVO(MemCouponVO memCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEMCOUPON_STMT);

			pstmt.setInt(1, memCouponVO.getMemberID());
			pstmt.setInt(2, memCouponVO.getCouponID());
			pstmt.setString(3, memCouponVO.getCouponName());
			pstmt.setInt(4, memCouponVO.getCouponStatus().getValue());
			pstmt.executeUpdate();

			// Handle any SQL errors
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

}
