package com.artcarousel.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class ArtCarouselJDBCDAO implements ArtCarouselDAO_interface{
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
			"INSERT INTO article_carousel(ARTICLE_CAROUSEL_POSITION,ARTICLE_TYPE_ID,ARTICLE_CAROUSEL_PICTURE,ARTICLE_LINK)"
			+ "VALUE(?,?,?,?);";
	private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_CAROUSEL_ID,ARTICLE_CAROUSEL_POSITION,ARTICLE_TYPE_ID,ARTICLE_CAROUSEL_PICTURE,ARTICLE_LINK "
			+ "FROM article_carousel order by ARTICLE_CAROUSEL_ID;";
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_CAROUSEL_ID,ARTICLE_CAROUSEL_POSITION,ARTICLE_TYPE_ID,ARTICLE_CAROUSEL_PICTURE,ARTICLE_LINK "
			+ "FROM article_carousel where ARTICLE_CAROUSEL_ID = ?;";
		private static final String DELETE = 
			"DELETE FROM article_carousel where ARTICLE_CAROUSEL_ID = ?;";
		private static final String UPDATE = 
			"UPDATE article_carousel set ARTICLE_CAROUSEL_POSITION=?, ARTICLE_TYPE_ID=?, ARTICLE_CAROUSEL_PICTURE=? ,ARTICLE_LINK=? "
			+ "where ARTICLE_CAROUSEL_ID = ?;";


	@Override
	public void insert(ArtCarouselVO artCarouselVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, artCarouselVO.getArticleCarouselPosition() );
			pstmt.setInt(2, artCarouselVO.getArticleTypeId());
			pstmt.setBytes(3, artCarouselVO.getArticleCarouselPicture());
			pstmt.setString(4, artCarouselVO.getArticleLink());

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
	public void update(ArtCarouselVO artCarouselVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, artCarouselVO.getArticleCarouselPosition() );
			pstmt.setInt(2, artCarouselVO.getArticleTypeId());
			pstmt.setBytes(3, artCarouselVO.getArticleCarouselPicture());
			pstmt.setString(4, artCarouselVO.getArticleLink());
			pstmt.setInt(5, artCarouselVO.getArticleCarouselId());

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
	public void delete(Integer ARTICLE_CAROUSEL_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,ARTICLE_CAROUSEL_ID);

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
	public ArtCarouselVO findByPrimaryKey(Integer ARTICLE_CAROUSEL_ID) {
		ArtCarouselVO artCarouselVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ARTICLE_CAROUSEL_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				artCarouselVO = new ArtCarouselVO();
				artCarouselVO.setArticleCarouselId(rs.getInt("ARTICLE_CAROUSEL_ID"));
				artCarouselVO.setArticleCarouselPosition(rs.getInt("ARTICLE_CAROUSEL_POSITION"));
				artCarouselVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
				artCarouselVO.setArticleCarouselPicture(rs.getBytes("ARTICLE_CAROUSEL_PICTURE"));
				artCarouselVO.setArticleLink(rs.getString("ARTICLE_LINK"));
				
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
		return artCarouselVO;
	}
	@Override
	public List<ArtCarouselVO> getAll() {
		List<ArtCarouselVO> list = new ArrayList<ArtCarouselVO>();
		ArtCarouselVO artCarouselVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				artCarouselVO = new ArtCarouselVO();
				artCarouselVO.setArticleCarouselId(rs.getInt("ARTICLE_CAROUSEL_ID"));
				artCarouselVO.setArticleCarouselPosition(rs.getInt("ARTICLE_CAROUSEL_POSITION"));
				artCarouselVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
				artCarouselVO.setArticleCarouselPicture(rs.getBytes("ARTICLE_CAROUSEL_PICTURE"));
				artCarouselVO.setArticleLink(rs.getString("ARTICLE_LINK"));

				list.add(artCarouselVO); // Store the row in the list
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
	public static void main(String[] args)throws FileNotFoundException {
		String inFile=args[0]; //檔案來源路徑
        String outFile=args[1]; //檔案輸出路徑

		File f = new File("C:\\CGA105_WebApp\\eclipse_WTP_workspace1\\CGA105G4\\src\\main\\webapp\\back_end\\artCarousel\\images\\test5.jpg");
		FileInputStream fis = new FileInputStream(f);
		
		ArtCarouselJDBCDAO dao = new ArtCarouselJDBCDAO();
//
//		//新增
//		ArtCarouselVO artCarouselVO1 = new ArtCarouselVO();
//		artCarouselVO1.setArticleCarouselPosition(4);
//		artCarouselVO1.setArticleTypeId(5);
//		artCarouselVO1.setBytes(null);
//		artCarouselVO1.setArticleLink("www.google.com");
//		dao.insert(artCarouselVO1);
//		
		
//
//		// 修改
//		ArtCarouselVO artCarouselVO2 = new ArtCarouselVO();
//		artCarouselVO2.setArticleCarouselId(1004);
//		artCarouselVO2.setArticleCarouselPosition(4);;
//		artCarouselVO2.setArticleTypeId(5);
//		artCarouselVO2.setArticleCarouselPicture(fis);
//		artCarouselVO2.setArticleLink("https://sgh.tw/");
//		dao.update(artCarouselVO2);
//		
//
//		 刪除
//		dao.delete(6);
//
		// 查詢
//		ArtCarouselVO artCarouselVO3 = dao.findByPrimaryKey(4);
//		System.out.print(artCarouselVO3.getArticleCarouselId() + ",");
//		System.out.print(artCarouselVO3.getArticleCarouselPosition() + ",");
//		System.out.print(artCarouselVO3.getArticleTypeId() + ",");
//		System.out.print(artCarouselVO3.getArticleCarouselPicture() + ",");
//		System.out.print(artCarouselVO3.getArticleLink() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<ArtCarouselVO> list = dao.getAll();
//		for (ArtCarouselVO artCarouselVO : list) {
//			System.out.print(artCarouselVO.getArticleCarouselId() + ",");
//			System.out.print(artCarouselVO.getArticleCarouselPosition() + ",");
//			System.out.print(artCarouselVO.getArticleTypeId() + ",");
//			System.out.print(artCarouselVO.getArticleCarouselPicture() + ",");
//			System.out.print(artCarouselVO.getArticleLink() + ",");
//			
//			System.out.println();
//		}
	}
}