package com.prodtype.model;

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

public class ProdTypeJDBCDAO implements ProdTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProdTypeJDBCDAO dao = new ProdTypeJDBCDAO();
		
		// 測試新增
//		ProdTypeVO testInsert = new ProdTypeVO();
//		testInsert.setProductTypeId(5);
//		testInsert.setProductTypeName("測試");
//		dao.insert(testInsert);
		
		// 測試刪除
//		ProdTypeVO testDelete = new ProdTypeVO();
//		testDelete.setProductTypeId(5);
//		dao.delete(testDelete);
//		dao.delete(5);
		
		// 測試更新
//		ProdTypeVO testUpdate = new ProdTypeVO();
//		testUpdate.setProductTypeId(5);
//		testUpdate.setProductTypeName("測試");
//		dao.update(testUpdate);
		
		// 測試搜尋單筆資料
//		ProdTypeVO testSelect1 = dao.findByPrimaryKey(5);
//		System.out.print(testSelect1.getProductTypeId() + " ");
//		System.out.print(testSelect1.getProductTypeName() + " ");
		
		// 測試搜尋全部
//		List<ProdTypeVO> list = dao.getAll();
//		for(ProdTypeVO element : list) {
//			System.out.print(element.getProductTypeId() + " ");
//			System.out.print(element.getProductTypeName() + " ");
//			System.out.println();
//		}
		
		
		
	}
	
	// ==============

	private static final String INSERT = "INSERT INTO `product_type` (`PRODUCT_TYPE_ID`,`PRODUCT_TYPE_NAME`) VALUES ( ? , ? )";

	@Override
	public void insert(ProdTypeVO prodTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, prodTypeVO.getProductTypeId());
			pstmt.setString(2, prodTypeVO.getProductTypeName());
			pstmt.executeUpdate();
			System.out.println("Insert完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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

	// ==============
	private static final String DELETE = "DELETE FROM `product_type` t WHERE t.PRODUCT_TYPE_ID = ?";

	@Override
	public void delete(Integer productTypeId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, prodTypeVO.getProductTypeId());
			pstmt.setInt(1, productTypeId);
			pstmt.executeUpdate();
			System.out.println("刪除完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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

	// ==============
	private static final String UPDATE = "UPDATE `product_type` t SET `PRODUCT_TYPE_NAME` = ? WHERE `PRODUCT_TYPE_ID` = ? ";

	@Override
	public void update(ProdTypeVO prodTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, prodTypeVO.getProductTypeName());
			pstmt.setInt(2, prodTypeVO.getProductTypeId());
			pstmt.executeUpdate();
			System.out.println("更新完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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

	// ==============

	private static final String GET_ALL = "SELECT `PRODUCT_TYPE_ID`, `PRODUCT_TYPE_NAME` FROM `product_type` group by PRODUCT_TYPE_ID";

	@Override
	public List<ProdTypeVO> getAll() {

		List<ProdTypeVO> list = new ArrayList<ProdTypeVO>();
		ProdTypeVO prodTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodTypeVO = new ProdTypeVO();
				prodTypeVO.setProductTypeId(rs.getInt("PRODUCT_TYPE_ID"));
				prodTypeVO.setProductTypeName(rs.getString("PRODUCT_TYPE_NAME"));
				list.add(prodTypeVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢查SQL語法 " + e.getMessage());
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

	// ==============

	private static final String GET_ONE = "SELECT `PRODUCT_TYPE_ID`, `PRODUCT_TYPE_NAME`  FROM `product_type` WHERE PRODUCT_TYPE_ID = ?";

	@Override
	public ProdTypeVO findByPrimaryKey(Integer product_type_id) {

		ProdTypeVO prodTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, product_type_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodTypeVO = new ProdTypeVO();
				prodTypeVO.setProductTypeId(rs.getInt("product_type_id"));
				prodTypeVO.setProductTypeName(rs.getString("product_type_name"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
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

		return prodTypeVO;
	}

}
