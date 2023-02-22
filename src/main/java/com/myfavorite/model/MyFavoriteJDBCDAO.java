package com.myfavorite.model;

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
import com.order.model.OrderPaymentStatus;
import com.order.model.OrderStatusStatus;
import com.order.model.OrderVO;


public class MyFavoriteJDBCDAO implements MyFavoriteDAO_interface {
	
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
			"INSERT INTO my_favorite (member_Id,product_Id) VALUES (?, ?)";//���۰ʽs�X�N���ζ�o��
		private static final String GET_ALL_STMT = 
			"SELECT member_Id,product_Id FROM my_favorite order by member_Id"; 
		private static final String GET_ONE_STMT = 
			"SELECT member_Id,product_Id FROM my_favorite where member_Id = ? and product_Id=?"; 
//********************刪除特定會員收藏的特定商品*************************//	
		private static final String DELETE = 
			"DELETE FROM my_favorite where member_Id = ? and product_Id=?"; 
		private static final String UPDATE =
			"UPDATE my_favorite set member_Id=?, product_Id=? where member_Id = ? and product_Id=?"; 
//********************列出會員收藏的商品*************************//		
		private static final String GET_MEM_FAVORITE = 		
			"SELECT * FROM mumu_new.my_favorite where member_Id=?";
		
	
		
		
		@Override
		public void insert(MyFavoriteVO myFavoriteVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, myFavoriteVO.getMemberId());
				pstmt.setInt(2, myFavoriteVO.getProductId());
				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void update(MyFavoriteVO myFavoriteVO,MyFavoriteVO myFavoriteVO2) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, myFavoriteVO.getMemberId());
				pstmt.setInt(2, myFavoriteVO.getProductId());
				pstmt.setInt(3, myFavoriteVO2.getMemberId());
				pstmt.setInt(4, myFavoriteVO2.getProductId());

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
//********************刪除特定會員收藏的特定商品*************************//	
		@Override
		public void delete(Integer memberId, Integer productId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, memberId);
				pstmt.setInt(2, productId);
				

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public MyFavoriteVO findByPrimaryKey(Integer memberId, Integer productId) {

			MyFavoriteVO myFavoriteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, memberId);
				pstmt.setInt(2, productId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// myFavoriteVO �]�٬� Domain objects
					myFavoriteVO = new MyFavoriteVO();
					myFavoriteVO.setMemberId(rs.getInt("member_Id"));
					myFavoriteVO.setProductId(rs.getInt("product_Id"));
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return myFavoriteVO;
		}

		@Override
		public List<MyFavoriteVO> getAll() {
			List<MyFavoriteVO> list = new ArrayList<MyFavoriteVO>();
			MyFavoriteVO myFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// myFavoriteVO �]�٬� Domain objects
					myFavoriteVO = new MyFavoriteVO();
					myFavoriteVO.setMemberId(rs.getInt("member_Id"));
					myFavoriteVO.setProductId(rs.getInt("product_Id"));
					list.add(myFavoriteVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

			MyFavoriteJDBCDAO dao = new MyFavoriteJDBCDAO();

//			// �s�W
//			MyFavoriteVO myFavoriteVO1 = new MyFavoriteVO();
//			myFavoriteVO1.setMemberId(4);
//			myFavoriteVO1.setProductId(2);
//			dao.insert(myFavoriteVO1);

//			// �ק�
//			MyFavoriteVO myFavoriteVO2 = new MyFavoriteVO();
//			MyFavoriteVO myFavoriteVO3 = new MyFavoriteVO();
//			myFavoriteVO2.setMemberId(5);
//			myFavoriteVO2.setProductId(2);
//			myFavoriteVO3.setMemberId(2);
//			myFavoriteVO3.setProductId(2);
//			
//			dao.update(myFavoriteVO2,myFavoriteVO3);

//			// �R��
//			dao.delete(4,2);
//
//			//  �d�߳���
//			MyFavoriteVO myFavoriteVO3 = dao.findByPrimaryKey(5, 2);
//			System.out.print(myFavoriteVO3.getMemberId() + ",");
//			System.out.print(myFavoriteVO3.getProductId() + ",");
//			System.out.println("---------------------");
//
			// �d�ߥ���		
			List<MyFavoriteVO> list = dao.getAll();
			for (MyFavoriteVO aMyFavorite : list) {
				System.out.print(aMyFavorite.getMemberId() + ",");
				System.out.print(aMyFavorite.getProductId() + ",");
				System.out.println();
			}
		}
//********************列出會員收藏的商品*************************//
		@Override
		// 需先拿到會員編號來找會員的收藏-->getMmeOrder(Integer memberID)
		
		public List<MyFavoriteVO> getMmeFavorite(Integer memberId) {
			List<MyFavoriteVO> list = new ArrayList<MyFavoriteVO>();
			MyFavoriteVO myFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEM_FAVORITE);

				pstmt.setInt(1, memberId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					myFavoriteVO = new MyFavoriteVO();
					myFavoriteVO.setMemberId(rs.getInt("member_Id"));
					myFavoriteVO.setProductId(rs.getInt("productr_Id"));
					list.add(myFavoriteVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
//					throw new RuntimeException("A database error occured. "
//							+ se.getMessage());
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
