package com.shoppingcartlist.model;

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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ShoppingCartListJDBCDAO implements ShoppingCartListDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO `shopping_cart_list` (`PRODUCT_OPTION_ID`, `MEMBER_ID`, `PRODUCT_OPTION_QUANTITY`) VALUES ( ? , ? , ? )";
	private static final String GET_ALL_STMT = "SELECT*FROM shopping_cart_list order by shopping_cart_list_ID";
	private static final String GET_ONE_STMT = "SELECT shopping_Cart_List_Id,product_Option_Id,member_Id,product_Option_Quantity FROM shopping_cart_list where shopping_Cart_List_Id = ?";
	private static final String DELETE = "DELETE FROM shopping_cart_list where PRODUCT_OPTION_ID = ?";
	private static final String UPDATE = "UPDATE shopping_cart_list set product_Option_Id=?, member_Id=?, product_Option_Quantity=? where shopping_Cart_List_Id = ?";

//	@Override
	public void insert(ShoppingCartListVO shoppingCartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, shoppingCartVO.getShoppingCartListId());
			pstmt.setInt(1, shoppingCartVO.getProductOptionId());
			pstmt.setInt(2, shoppingCartVO.getMemberId());
			pstmt.setInt(3, shoppingCartVO.getProductOptionQuantity());

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

	public void update(ShoppingCartListVO shoppingCartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shoppingCartVO.getProductOptionId());
			pstmt.setInt(2, shoppingCartVO.getMemberId());
			pstmt.setInt(3, shoppingCartVO.getProductOptionQuantity());
			pstmt.setInt(4, shoppingCartVO.getShoppingCartListId());

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
	public ShoppingCartListVO findByPrimaryKey(Integer shoppingCartListId) {

		ShoppingCartListVO shoppingCartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shoppingCartListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// shoppingCartVO �]�٬� Domain objects
				shoppingCartVO = new ShoppingCartListVO();
				shoppingCartVO.setShoppingCartListId(rs.getInt("shopping_Cart_List_Id"));
				shoppingCartVO.setProductOptionId(rs.getInt("product_Option_Id"));
				shoppingCartVO.setMemberId(rs.getInt("member_Id"));
				shoppingCartVO.setProductOptionQuantity(rs.getInt("product_Option_Quantity"));
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
		return shoppingCartVO;
	}

	@Override
	public List<ShoppingCartListVO> getAll() {
		List<ShoppingCartListVO> list = new ArrayList<ShoppingCartListVO>();
		ShoppingCartListVO shoppingCartVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// shoppingCartVO �]�٬� Domain objects
				shoppingCartVO = new ShoppingCartListVO();
				shoppingCartVO.setShoppingCartListId(rs.getInt("shopping_Cart_List_Id"));
				shoppingCartVO.setProductOptionId(rs.getInt("product_Option_Id"));
				shoppingCartVO.setMemberId(rs.getInt("member_Id"));
				shoppingCartVO.setProductOptionQuantity(rs.getInt("product_Option_Quantity"));
				list.add(shoppingCartVO); // Store the row in the list
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

	private static final String GET_ONE_MEM_SHOP_CART_LIST = ""
			+ "SELECT	scl.MEMBER_ID , mem.MEMBER_NAME , scl.PRODUCT_OPTION_QUANTITY , "
			+ "			sp.PRODUCT_ID , sp.PRODUCT_NAME , "
			+ "			po.PRODUCT_OPTION_ID , po.PRODUCT_OPTION_NAME , po.PRODUCT_OPTION_PICTURE , "
			+ "			po.PRODUCT_OPTION_PRICE , po.PRODUCT_OPTION_INVENTORY " + "FROM 	shopping_cart_list scl "
			+ "JOIN		product_option po on scl.PRODUCT_OPTION_ID=po.PRODUCT_OPTION_ID "
			+ "JOIN		member mem on scl.MEMBER_ID=mem.MEMBER_ID "
			+ "JOIN		shop_product sp on po.PRODUCT_ID=sp.PRODUCT_ID " + "WHERE	scl.MEMBER_ID = ? "
			+ "group by scl.PRODUCT_OPTION_ID " + "order by scl.SHOPPING_CART_LIST_ID";

	@Override
	public JSONArray findByMemId(Integer memberId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		JSONObject obj = null;
		JSONArray list = new JSONArray();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM_SHOP_CART_LIST);
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj = new JSONObject();
				obj.put("MEMBER_ID", rs.getInt("MEMBER_ID"));
				obj.put("MEMBER_NAME", rs.getString("MEMBER_NAME"));
				obj.put("PRODUCT_OPTION_QUANTITY", rs.getInt("PRODUCT_OPTION_QUANTITY"));
				obj.put("PRODUCT_ID", rs.getInt("PRODUCT_ID"));
				obj.put("PRODUCT_NAME", rs.getString("PRODUCT_NAME"));
				obj.put("PRODUCT_OPTION_ID", rs.getInt("PRODUCT_OPTION_ID"));
				obj.put("PRODUCT_OPTION_NAME", rs.getString("PRODUCT_OPTION_NAME"));
				obj.put("PRODUCT_OPTION_PICTURE_URL", "URLLLLLLLL"); // 圖片部存入json, 另外做一支api取圖片
				obj.put("PRODUCT_OPTION_PRICE", rs.getInt("PRODUCT_OPTION_PRICE"));
				obj.put("PRODUCT_OPTION_INVENTORY", rs.getInt("PRODUCT_OPTION_INVENTORY"));

				System.out.println(obj);
				list.add(obj);
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

//		System.out.println(list.toJSONString());
		list.toJSONString();
		return list;
	}

	public static void main(String[] args) {
	}
}
