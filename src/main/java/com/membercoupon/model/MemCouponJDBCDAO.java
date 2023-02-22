package com.membercoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCouponJDBCDAO implements MemCouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mumu?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "p@ssw0rd";
	
	private static final String INSERT_STMT = 
			"INSERT INTO member_coupon (COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS FROM member_coupon order by MEMBER_ID";
	private static final String GET_ONE_STMT = 
			"SELECT COUPON_CODE_NUMBER, MEMBER_ID, COUPON_ID, COUPON_NAME, COUPON_STATUS FROM member_coupon where COUPON_CODE_NUMBER = ?";
//	private static final String GET_EACHMEMBER_STMT =
	private static final String DELETE = 
			"DELETE FROM member_coupon where COUPON_CODE_NUMBER = ?";
	private static final String UPDATE = 
			"UPDATE member_coupon set MEMBER_ID=?, COUPON_ID=?, COUPON_NAME=?, COUPON_STATUS=? where COUPON_CODE_NUMBER=?";
	

	@Override
	public void insert(MemCouponVO memCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memCouponVO.getCouponCodeNumber());
			pstmt.setInt(2, memCouponVO.getMemberID());
			pstmt.setInt(3, memCouponVO.getCouponID());
			pstmt.setString(4, memCouponVO.getCouponName());
			pstmt.setByte(5, memCouponVO.getCouponStatus());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace(); //�i���D��]�C�X���~���|((line numbers + call stack)) > e.toString �u���D�ߥX�F����ҥ~+���~��> e.getMessage ���~��
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setInt(1, memCouponVO.getMemberID());
			pstmt.setInt(2, memCouponVO.getCouponID());
			pstmt.setString(3, memCouponVO.getCouponName());
			pstmt.setByte(4, memCouponVO.getCouponStatus());
			pstmt.setString(5, memCouponVO.getCouponCodeNumber());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, couponCodeNumber);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memCouponVO.setCouponStatus(rs.getByte("COUPON_STATUS"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memCouponVO = new MemCouponVO();
				memCouponVO.setCouponCodeNumber(rs.getString("COUPON_CODE_NUMBER"));
				memCouponVO.setMemberID(rs.getInt("MEMBER_ID"));
				memCouponVO.setCouponID(rs.getInt("COUPON_ID"));
				memCouponVO.setCouponName(rs.getString("COUPON_NAME"));
				memCouponVO.setCouponStatus(rs.getByte("COUPON_STATUS"));
				list.add(memCouponVO); // Store the row in the list
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
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
	
	public static void main(String[] args) {

		MemCouponJDBCDAO dao = new MemCouponJDBCDAO();

		// 新增
//		MemCouponVO memCouponVO1 = new MemCouponVO();
//		memCouponVO1.setCouponCodeNumber("XYZZYXX");
//		memCouponVO1.setMemberID(4);
//		memCouponVO1.setCouponID(2);
//		memCouponVO1.setCouponName("春節發紅包");
//		memCouponVO1.setCouponStatus((byte) 1);
//		dao.insert(memCouponVO1);

		// 修改
//		MemCouponVO memCouponVO2 = new MemCouponVO();
//		memCouponVO2.setCouponCodeNumber("XYZZYXX");
//		memCouponVO2.setMemberID(2);
//		memCouponVO2.setCouponID(3);
//		memCouponVO2.setCouponName("測試修改");
//		memCouponVO2.setCouponStatus((byte) 2);
//		
//		dao.update(memCouponVO2);
		

		// 刪除
//		dao.delete("XYZZYXX");

		// 查詢
//		MemCouponVO memCouponVO3 = dao.findByPrimaryKey("XYZZYXX");
//		System.out.print(memCouponVO3.getCouponCodeNumber() + ",");
//		System.out.print(memCouponVO3.getMemberID() + ",");
//		System.out.print(memCouponVO3.getCouponID() + ",");
//		System.out.print(memCouponVO3.getCouponName() + ",");
//		System.out.println(memCouponVO3.getCouponStatus());
//		System.out.println("---------------------");
		
		
		// 查詢
		List<MemCouponVO> list = dao.getAll();
		for (MemCouponVO aMemCoupon : list) {
			System.out.print(aMemCoupon.getCouponCodeNumber() + ",");
			System.out.print(aMemCoupon.getMemberID() + ",");
			System.out.print(aMemCoupon.getCouponID() + ",");
			System.out.print(aMemCoupon.getCouponName() + ",");
			System.out.println(aMemCoupon.getCouponStatus());
			System.out.println();
		}
	}
}
