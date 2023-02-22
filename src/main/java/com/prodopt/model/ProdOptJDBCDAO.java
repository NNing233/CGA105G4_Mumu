package com.prodopt.model;

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

public class ProdOptJDBCDAO implements ProdOptDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product_option (PRODUCT_ID,PRODUCT_OPTION_PICTURE,PRODUCT_OPTION_NAME,PRODUCT_OPTION_PRICE,PRODUCT_OPTION_INVENTORY) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT product_Option_Id,product_Id,product_Option_Picture,product_Option_Name,product_Option_Price,product_Option_Inventory FROM product_option order by product_Option_Id";
	private static final String GET_PROD_OPT = "SELECT * FROM product_option where PRODUCT_ID = ? order by PRODUCT_OPTION_ID";
	private static final String GET_ONE_STMT = "SELECT product_Option_Id,product_Id,product_Option_Picture,product_Option_Name,product_Option_Price,product_Option_Inventory FROM product_option where product_Option_Id = ?";
	private static final String DELETE = "DELETE FROM product_option where product_Option_Id = ?";
	private static final String UPDATE = "UPDATE product_option set  product_Id=?, product_Option_Picture=?, product_Option_Name=?, product_Option_Price=?, product_Option_Inventory=? where product_Option_Id = ?";
	// **********Join方法:myfavorite表格 join propoption表格資訊*****************//
	private static final String GET_ALLPRODOPT_STMT = "SELECT * FROM product_option where product_Id = ?";

	@Override
	public void insert(ProdOptVO prodOptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prodOptVO.getProductId());
//			pstmt.setBytes(3, prodOptVO.getProductOptionPicture());
			pstmt.setBinaryStream(2, prodOptVO.getProductOptionPicture());
			pstmt.setString(3, prodOptVO.getProductOptionName());
			pstmt.setInt(4, prodOptVO.getProductOptionPrice());
			pstmt.setInt(5, prodOptVO.getProductOptionInventory());
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
	public void update(ProdOptVO prodOptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, prodOptVO.getProductId());
//			pstmt.setBytes(2, prodOptVO.getProductOptionPicture());//
			pstmt.setBinaryStream(2, prodOptVO.getProductOptionPicture());
			pstmt.setString(3, prodOptVO.getProductOptionName());
			pstmt.setInt(4, prodOptVO.getProductOptionPrice());
			pstmt.setInt(5, prodOptVO.getProductOptionInventory());
			pstmt.setInt(6, prodOptVO.getProductOptionId());

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
	public ProdOptVO findByPrimaryKey(Integer productOptionId) {

		ProdOptVO prodOptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productOptionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodOptVO Domain objects
				prodOptVO = new ProdOptVO();
				prodOptVO.setProductOptionId(rs.getInt("product_Option_Id"));
				prodOptVO.setProductId(rs.getInt("product_Id"));
				prodOptVO.setProductOptionPicture(rs.getBinaryStream("product_Option_Picture"));
				prodOptVO.setProductOptionName(rs.getString("product_Option_Name"));
				prodOptVO.setProductOptionPrice(rs.getInt("product_Option_Price"));
				prodOptVO.setProductOptionInventory(rs.getInt("product_Option_Inventory"));
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
		return prodOptVO;
	}

	@Override
	public List<ProdOptVO> getAll() {
		List<ProdOptVO> list = new ArrayList<ProdOptVO>();
		ProdOptVO prodOptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodOptVO Domain objects
				prodOptVO = new ProdOptVO();
				prodOptVO.setProductOptionId(rs.getInt("product_Option_Id"));
				prodOptVO.setProductId(rs.getInt("product_Id"));
				prodOptVO.setProductOptionPicture(rs.getBinaryStream("product_Option_Picture"));
				prodOptVO.setProductOptionName(rs.getString("product_Option_Name"));
				prodOptVO.setProductOptionPrice(rs.getInt("product_Option_Price"));
				prodOptVO.setProductOptionInventory(rs.getInt("product_Option_Inventory"));
				list.add(prodOptVO); // Store the row in the list
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
	public List<ProdOptVO> findByProdId(Integer productId) {
		List<ProdOptVO> list = new ArrayList<ProdOptVO>();
		ProdOptVO prodOptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_OPT);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodOptVO Domain objects
				prodOptVO = new ProdOptVO();
				prodOptVO.setProductOptionId(rs.getInt("product_Option_Id"));
				prodOptVO.setProductId(rs.getInt("product_Id"));
				prodOptVO.setProductOptionPicture(rs.getBinaryStream("product_Option_Picture"));
				prodOptVO.setProductOptionName(rs.getString("product_Option_Name"));
				prodOptVO.setProductOptionPrice(rs.getInt("product_Option_Price"));
				prodOptVO.setProductOptionInventory(rs.getInt("product_Option_Inventory"));
				list.add(prodOptVO); // Store the row in the list

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

//**********Join方法:myfavorite表格 join propoption表格資訊*****************//
// 兩個表格有相同的欄位couponID所以帶入couponID來找membercoupon其他資訊
	@Override
	public ProdOptVO getTheProdOpt(Integer productId) {
		ProdOptVO prodOptVO = new ProdOptVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLPRODOPT_STMT);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				prodOptVO = new ProdOptVO();
				prodOptVO.setProductOptionId(rs.getInt("product_Option_Id"));
				prodOptVO.setProductId(rs.getInt("product_Id"));
				prodOptVO.setProductOptionPicture(rs.getBinaryStream("product_Option_Picture"));
				prodOptVO.setProductOptionName(rs.getString("product_Option_Name"));
				prodOptVO.setProductOptionPrice(rs.getInt("product_Option_Price"));
				prodOptVO.setProductOptionInventory(rs.getInt("product_Option_Inventory"));
				
				
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
		return prodOptVO;

	}

//	ori
//	@Override
//	public ProdOptVO findByProdId(Integer productId) {
//		ProdOptVO prodOptVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_PROD_OPT);
//			pstmt.setInt(1, productId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// prodOptVO Domain objects
//				prodOptVO = new ProdOptVO();
//				prodOptVO.setProductOptionId(rs.getInt("product_Option_Id"));
//				prodOptVO.setProductId(rs.getInt("product_Id"));
//				prodOptVO.setProductOptionPicture(rs.getBinaryStream("product_Option_Picture"));
//				prodOptVO.setProductOptionName(rs.getString("product_Option_Name"));
//				prodOptVO.setProductOptionPrice(rs.getInt("product_Option_Price"));
//				prodOptVO.setProductOptionInventory(rs.getInt("product_Option_Inventory"));
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return prodOptVO;
//	}

}
