package com.artliked.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ArtLikedJDBCDAO implements ArtLikedDAO_interface {
	
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
		"INSERT INTO article_Liked (ARTICLE_ID,member_Id) VALUES (?,?);";
	private static final String GET_ALL_STMT = 
		"SELECT ARTICLE_ID ,MEMBER_ID FROM article_liked order by ARTICLE_ID;";
	private static final String GET_ONE_STMT = 
		"SELECT article_Id,member_Id FROM article_Liked where ARTICLE_ID = ?";
	private static final String DELETE = 
		"DELETE FROM article_Liked where ARTICLE_ID =  ?";
	private static final String UPDATE = 
		"UPDATE article_Liked set memberId=? where article_Id = ?";

	@Override
	public void insert(ArtLikedVO artLikedVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, artLikedVO.getArticleId());
			pstmt.setInt(2, artLikedVO.getMemberId());
			
			pstmt.executeUpdate();

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
	public void update(ArtLikedVO artLikedVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, artLikedVO.getArticleId());
			pstmt.setInt(2, artLikedVO.getMemberId());

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
	public void delete(Integer ARTICLE_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ARTICLE_ID);

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
	public ArtLikedVO findByPrimaryKey(Integer articleId) {

		ArtLikedVO artLikedVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				artLikedVO = new ArtLikedVO();
				artLikedVO.setArticleId(rs.getInt("article_Id"));
				artLikedVO.setMemberId(rs.getInt("member_Id"));
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
		return artLikedVO;
	}

	@Override
	public List<ArtLikedVO> getAll() {
		List<ArtLikedVO> list = new ArrayList<ArtLikedVO>();
		ArtLikedVO artLikedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				artLikedVO = new ArtLikedVO();
				artLikedVO.setArticleId(rs.getInt("article_Id"));
				artLikedVO.setMemberId(rs.getInt("member_Id"));
				list.add(artLikedVO); // Store the row in the list
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

		ArtLikedJDBCDAO dao = new ArtLikedJDBCDAO();

//		// 新增★
//		ArtLikedVO articleLikedVO1 = new ArtLikedVO();
//		articleLikedVO1.setArticleId (7);
//		articleLikedVO1.setMemberId (7);
//		dao.insert(articleLikedVO1);

//		// 修改★
//		ArtLikedVO artLikedVO2 = new ArtLikedVO();
//		artLikedVO2.setArticleId(1);
//		artLikedVO2.setMemberId(4);
//		dao.update(artLikedVO2);

//		// 刪除
//		dao.delete(3);
//
		// 查詢
//		ArtLikedVO articleLikedVO3 = dao.findByPrimaryKey(2);
//		System.out.print(articleLikedVO3.getArticleId() + ",");
//		System.out.print(articleLikedVO3.getMemberId() + ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
		List<ArtLikedVO> list = dao.getAll();
		for (ArtLikedVO articleLiked : list) {
			System.out.print(articleLiked.getArticleId () + ",");
			System.out.print(articleLiked.getMemberId() + ",");
		
			System.out.println();
		}
	}
}