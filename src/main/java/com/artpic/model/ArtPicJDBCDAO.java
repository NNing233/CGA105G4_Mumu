package com.artpic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.mysql.cj.conf.ConnectionUrl.Type;

import java.sql.*;

public class ArtPicJDBCDAO implements ArtPicDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "insert into ARTICLE_PICTURE (ARTICLE_ID, ARTICLE_PICTURE) values ( ?, ?)";
	private static final String GET_ALL = "select ARTICLE_PICTURE_ID, ARTICLE_ID, COMMENT_ID, ARTICLE_PICTURE from ARTICLE_PICTURE order by ARTICLE_PICTURE_ID";
	private static final String GET_ONE = "select ARTICLE_PICTURE_ID, ARTICLE_ID, COMMENT_ID, ARTICLE_PICTURE from ARTICLE_PICTURE where ARTICLE_PICTURE_ID=?";
	private static final String DELETE = "delete from ARTICLE_PICTURE where ARTICLE_PICTURE_ID = ?";
	private static final String UPDATE = "update ARTICLE_PICTURE set ARTICLE_PICTURE=? where ARTICLE_PICTURE_ID=?";

	@Override
	public void insert(ArtPicVO artPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
					
			pstmt.setInt(1, artPicVO.getArticleId());				
			pstmt.setBytes(2, artPicVO.getArticlePicture());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	@Override
	public void update(ArtPicVO artPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setBytes(1, artPicVO.getArticlePicture());
			pstmt.setInt(2, artPicVO.getArticlePictureId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Integer articlePictureId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articlePictureId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public ArtPicVO findByPrimaryKey(Integer articlePictureId) {

		ArtPicVO artPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setInt(1, articlePictureId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				artPictureVO = new ArtPicVO();
				artPictureVO.setArticlePictureId(rs.getInt("ARTICLE_PICTURE_ID"));
				artPictureVO.setArticleId(rs.getInt("ARTICLE_ID"));
				artPictureVO.setArticlePicture(rs.getBytes("ARTICLE_PICTURE"));
				
			}


		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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

		return artPictureVO;
	}

	@Override
	public List<ArtPicVO> getAll() {

		List<ArtPicVO> list = new ArrayList<ArtPicVO>();
		ArtPicVO artPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				artPictureVO = new ArtPicVO();
				artPictureVO.setArticlePictureId(rs.getInt("ARTICLE_PICTURE_ID"));
				artPictureVO.setArticleId(rs.getInt("ARTICLE_ID"));
				artPictureVO.setArticlePicture(rs.getBytes("ARTICLE_PICTURE"));

				list.add(artPictureVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
