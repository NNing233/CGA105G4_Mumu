package com.favoriteart.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class FavoriteArtJDBCDAO implements FavoriteArtDAO_interface {
	
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
	"INSERT INTO favorite_article (ARTICLE_ID,MEMBER_ID) VALUES (?,?);";
	private static final String GET_ALL_STMT = 
		"SELECT  ARTICLE_ID, MEMBER_ID FROM favorite_article order by ARTICLE_ID";
	private static final String GET_ONE_STMT = 
		"SELECT  ARTICLE_ID, MEMBER_ID FROM favorite_article where ARTICLE_ID = ?";
	private static final String DELETE = 
        "DELETE FROM favorite_article where ARTICLE_ID = ? and MEMBER_ID= ?";
	private static final String UPDATE = 
        "UPDATE favorite_article SET ARTICLE_ID = ? WHERE ARTICLE_ID =? and MEMBER_ID= ?";
	// 由memberId取該會員收藏的文章
	private static final String GET_ALL_ByMemberId_STMT = 
			"SELECT  ARTICLE_ID, MEMBER_ID FROM favorite_article where MEMBER_ID=? order by ARTICLE_ID ";
	private static final String GET_TheFavoriteArt_STMT = 
			"SELECT  ARTICLE_ID, MEMBER_ID FROM favorite_article where ARTICLE_ID = ? AND MEMBER_ID=?";
	@Override
	public FavoriteArtVO getTheFavoriteArt(Integer articleId, Integer memberId) {
		FavoriteArtVO favoriteArtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TheFavoriteArt_STMT);

			pstmt.setInt(1, articleId);
			pstmt.setInt(2, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				favoriteArtVO = new FavoriteArtVO();
				favoriteArtVO.setArticleId(rs.getInt("ARTICLE_ID"));
				favoriteArtVO.setMemberId(rs.getInt("MEMBER_ID"));
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
		return favoriteArtVO;
	}
	
	public List<FavoriteArtVO> getTheMemberAllFavorArt(Integer memberId){
		List<FavoriteArtVO> list = new ArrayList<FavoriteArtVO>();
		FavoriteArtVO favoriteArtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ByMemberId_STMT);
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				favoriteArtVO = new FavoriteArtVO();
				favoriteArtVO.setArticleId(rs.getInt("ARTICLE_ID"));
				favoriteArtVO.setMemberId(rs.getInt("MEMBER_ID"));
				list.add(favoriteArtVO); // Store the row in the list
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
		
	@Override
	public void insert(FavoriteArtVO favoriteArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, favoriteArtVO.getArticleId());//��ݸ�
			pstmt.setInt(2, favoriteArtVO.getMemberId());//��ݸ�
//����sql�y�k
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
	public void update(FavoriteArtVO favoriteArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(2, favoriteArtVO.getArticleId());
			pstmt.setInt(1, favoriteArtVO.getMemberId());
			pstmt.setInt(3, favoriteArtVO.getMemberId());

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
	public void delete(Integer articleId, Integer memberId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleId);
			pstmt.setInt(2, memberId);

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
	public FavoriteArtVO findByPrimaryKey(Integer articleId) {

		FavoriteArtVO favoriteArtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				favoriteArtVO = new FavoriteArtVO();
				favoriteArtVO.setArticleId(rs.getInt("ARTICLE_ID"));
				favoriteArtVO.setMemberId(rs.getInt("MEMBER_ID"));
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
		return favoriteArtVO;
	}

	@Override
	public List<FavoriteArtVO> getAll() {
		List<FavoriteArtVO> list = new ArrayList<FavoriteArtVO>();
		FavoriteArtVO favoriteArtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				favoriteArtVO = new FavoriteArtVO();
				favoriteArtVO.setArticleId(rs.getInt("ARTICLE_ID"));
				favoriteArtVO.setMemberId(rs.getInt("MEMBER_ID"));
				list.add(favoriteArtVO); // Store the row in the list
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

		FavoriteArtJDBCDAO dao = new FavoriteArtJDBCDAO();

//		// �s�W��
//		FavoriteArtVO favoriteArtVO1 = new FavoriteArtVO();
//		favoriteArtVO1.setArticleId (2);
//		favoriteArtVO1.setMemberId (4);
//		
//		dao.insert(favoriteArtVO1);

		// �ק
		FavoriteArtVO favoriteArtVO2= new FavoriteArtVO();
		favoriteArtVO2.setArticleId(2);
		favoriteArtVO2.setMemberId(2);
		
		dao.update(favoriteArtVO2);
//
		// �R��
//		dao.delete(1);

		// �d��
//		FavoriteArtVO favoriteArtVO3 = dao.findByPrimaryKey(2);
//		
//		System.out.print(favoriteArtVO3.getArticleId() + ",");
//		System.out.print(favoriteArtVO3.getMemberId() + ",");
//		
//		System.out.println("---------------------");

		// �d��
		List<FavoriteArtVO> list = dao.getAll();
		for (FavoriteArtVO favoriteArt : list) {
			System.out.print(favoriteArt.getArticleId () + ",");
			System.out.print(favoriteArt.getMemberId() + ",");
		
			System.out.println();
		}
	}
}