package com.arttype.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class ArtTypeJDBCDAO implements ArtTypeDAO_interface {
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
			"INSERT INTO article_type (ARTICLE_TYPE_NAME) VALUES (?)";
		private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_TYPE_ID,ARTICLE_TYPE_NAME FROM article_type order by ARTICLE_TYPE_ID;";
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_TYPE_ID,ARTICLE_TYPE_NAME FROM article_type where ARTICLE_TYPE_ID = ?";		
		private static final String DELETE = 
			"DELETE FROM article_Type where ARTICLE_TYPE_ID = ?";
		private static final String UPDATE = 
			"UPDATE article_type set ARTICLE_TYPE_NAME = ? where ARTICLE_TYPE_ID = ?";

		@Override
		public void insert(ArtTypeVO artTypeVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, artTypeVO.getArticleTypeName());
			
				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void update(ArtTypeVO artTypeVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, artTypeVO.getArticleTypeName());
				pstmt.setInt(2, artTypeVO.getArticleTypeId());

				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void delete(Integer ARTICLE_TYPE_ID) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, ARTICLE_TYPE_ID);

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		public ArtTypeVO findByPrimaryKey(Integer ARTICLE_TYPE_ID) {

			ArtTypeVO artTypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, ARTICLE_TYPE_ID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					artTypeVO = new ArtTypeVO();
					artTypeVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
					artTypeVO.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));			
				}

				// Handle any driver errors
			}  catch (SQLException se) {
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
			return artTypeVO;
		}

		@Override
		public List<ArtTypeVO> getAll() {
			List<ArtTypeVO> list = new ArrayList<ArtTypeVO>();
			ArtTypeVO artTypeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					artTypeVO = new ArtTypeVO();
					artTypeVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
					artTypeVO.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
					
					list.add(artTypeVO); // Store the row in the list
				}

				// Handle any driver errors
			}  catch (SQLException se) {
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

			ArtTypeJDBCDAO dao = new ArtTypeJDBCDAO();

//			// 新增
//			ArtTypeVO artTypeVO1 = new ArtTypeVO();
//			artTypeVO1.setArticleTypeName("test2");
//			dao.insert(artTypeVO1);
//
//			// 修改
//			ArtTypeVO artTypeVO2 = new ArtTypeVO();
//			artTypeVO2.setArticleTypeId(8);
//			artTypeVO2.setArticleTypeName("test6");
//			dao.update(artTypeVO2);
//
//			// 刪除
//			dao.delete(8);
//
//			// 查詢
//			ArtTypeVO artTypeVO3 = dao.findByPrimaryKey(1);
//			System.out.print(artTypeVO3.getArticleTypeId() + ",");
//			System.out.print(artTypeVO3.getArticleTypeName() + ",");
//			
//			System.out.println("---------------------");
//
			// 查詢
			List<ArtTypeVO> list = dao.getAll();
			for (ArtTypeVO artType : list) {
				System.out.print(artType.getArticleTypeId() + ",");
				System.out.print(artType.getArticleTypeName() + ",");
			
				System.out.println();
			}
		}
	}