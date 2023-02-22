package com.art.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ArtJDBCDAO implements ArtDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT =
		"insert into ARTICLE ( MEMBER_ID,ARTICLE_TYPE_ID, ARTICLE_STATUS, ARTICLE_TITLE, ARTICLE_CONTENT)"
				+" values (?,?,?,?,?)";
	private static final String GET_ALL = 
		"select ARTICLE_ID, MEMBER_ID, ARTICLE_TYPE_ID, ARTICLE_STATUS, ARTICLE_TITLE, ARTICLE_CONTENT,"
				+" ARTICLE_LIKES_AMOUNT, ARTICLE_POST_TIME, ARTICLE_UPDATE_TIME from ARTICLE order by ARTICLE_POST_TIME DESC";
	private static final String GET_ONE = 
		"select ARTICLE_ID, MEMBER_ID, ARTICLE_TYPE_ID, ARTICLE_STATUS, ARTICLE_TITLE, ARTICLE_CONTENT,"
				+" ARTICLE_LIKES_AMOUNT, ARTICLE_POST_TIME, ARTICLE_UPDATE_TIME from ARTICLE where ARTICLE_ID=?";
//	private static final String GET_TWO = 
//			"select*from article where ARTICLE_TYPE_ID =?";
	private static final String DELETE = 
		"delete from ARTICLE where ARTICLE_ID =?";
	private static final String UPDATE = 
		"update ARTICLE set ARTICLE_TITLE=?, ARTICLE_CONTENT=?, ARTICLE_STATUS=?, ARTICLE_TYPE_ID=? where ARTICLE_ID=?";
//	private static final String UPDATE = 
//			"update ARTICLE set ARTICLE_TYPE_ID=?, ARTICLE_STATUS=?, ARTICLE_TITLE=?, ARTICLE_CONTENT=?, ARTICLE_LIKES_AMOUNT=?, ARTICLE_POST_TIME=? where ARTICLE_ID=?";	
	
	private static final String SELECT = 
		"select * from article where ARTICLE_TITLE like ? or ARTICLE_CONTENT like ? "; //資料庫搜尋文章關鍵字
	
	private static final String SELECT_ARTICLE_TYPE_ID = 
	"select * from article where ARTICLE_TYPE_ID =?";  //搜尋文章類別之文章
	
	//===================關鍵字==============================================	
	@Override
		public List<ArtVO> selectKeyword(String keyword) {
		List<ArtVO> keywordlist = new ArrayList<ArtVO>();
		    ArtVO artVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT);
				pstmt.setString(1,"%"+keyword+"%");
				pstmt.setString(2,"%"+keyword+"%");
				rs = pstmt.executeQuery();	
				
				while (rs.next()) {
					
					artVO = new ArtVO();
					artVO.setArticleId(rs.getInt("ARTICLE_ID"));
					artVO.setMemberId(rs.getInt("MEMBER_ID"));
					artVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
					artVO.setArticleStatus(rs.getInt("ARTICLE_STATUS"));
					artVO.setArticleTitle(rs.getString("ARTICLE_TITLE"));
					artVO.setArticleContent(rs.getString("ARTICLE_CONTENT"));
					artVO.setArticleLikesAmount(rs.getInt("ARTICLE_LIKES_AMOUNT"));
					artVO.setArticlePostTime(rs.getTimestamp("ARTICLE_POST_TIME"));
					artVO.setArticleUpdateTime(rs.getTimestamp("ARTICLE_UPDATE_TIME"));
					keywordlist.add(artVO);
				}

			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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

			return keywordlist;
		}

	
		
	
	//========================搜尋文章類別之文章======================================	
	@Override
	public List<ArtVO> select(Integer articleTypeId) {
		
		List<ArtVO>list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ARTICLE_TYPE_ID);

			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
			
				artVO = new ArtVO();
				artVO.setArticleId(rs.getInt("ARTICLE_ID"));
				artVO.setMemberId(rs.getInt("MEMBER_ID"));
				artVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
				artVO.setArticleStatus(rs.getInt("ARTICLE_STATUS"));
				artVO.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				artVO.setArticleContent(rs.getString("ARTICLE_CONTENT"));
				artVO.setArticleLikesAmount(rs.getInt("ARTICLE_LIKES_AMOUNT"));
				artVO.setArticlePostTime(rs.getTimestamp("ARTICLE_POST_TIME"));
				artVO.setArticleUpdateTime(rs.getTimestamp("ARTICLE_UPDATE_TIME"));
				list.add(artVO); 
			}
			
			
		
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
		public void insert(ArtVO artVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT);
				
				pstmt.setInt(1, artVO.getMemberId());
				pstmt.setInt(2, artVO.getArticleTypeId());
				pstmt.setInt(3, artVO.getArticleStatus());
				pstmt.setString(4, artVO.getArticleTitle());
				pstmt.setString(5, artVO.getArticleContent());
//				pstmt.setInt(6, artVO.getArticleLikesAmount());
				

				pstmt.executeUpdate();				
				
			
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
		public void update(ArtVO artVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
//				pstmt.setInt(1, artVO.getArticleTypeId());
//				pstmt.setInt(2, artVO.getArticleStatus());
//				pstmt.setString(3, artVO.getArticleTitle());
//				pstmt.setString(4, artVO.getArticleContent());
//				pstmt.setInt(5, artVO.getArticleLikesAmount());
//				pstmt.setTimestamp(6, artVO.getArticlePostTime());
//				pstmt.setInt(7, artVO.getArticleId());
				
				pstmt.setString(1, artVO.getArticleTitle());
				pstmt.setString(2, artVO.getArticleContent());
				pstmt.setInt(3, artVO.getArticleStatus());
				pstmt.setInt(4, artVO.getArticleTypeId());				
				pstmt.setInt(5, artVO.getArticleId());
				
				pstmt.executeUpdate();
				
			
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
				
				if(con != null) {
					try {
						con.close();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			}
			
		}
		
		
		@Override
		public void delete(Integer articleId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1, articleId);
				
				pstmt.executeUpdate();
				
			
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
				
				if(con != null) {
					try {
						con.close();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			}				
		}
		
		
		@Override
		public ArtVO findByPrimaryKey(Integer articleId) {
			
			ArtVO artVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE);
				
				pstmt.setInt(1, articleId);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					artVO = new ArtVO();
					artVO.setArticleId(rs.getInt("ARTICLE_ID"));
					artVO.setMemberId(rs.getInt("MEMBER_ID"));
					artVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
					artVO.setArticleStatus(rs.getInt("ARTICLE_STATUS"));
					artVO.setArticleTitle(rs.getString("ARTICLE_TITLE"));
					artVO.setArticleContent(rs.getString("ARTICLE_CONTENT"));
					artVO.setArticleLikesAmount(rs.getInt("ARTICLE_LIKES_AMOUNT"));
					artVO.setArticlePostTime(rs.getTimestamp("ARTICLE_POST_TIME"));
					artVO.setArticleUpdateTime(rs.getTimestamp("ARTICLE_UPDATE_TIME"));
				}
				
			
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
				
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
			
			return artVO;
		}
		
		
		@Override
		public List<ArtVO> getAll() {
			
			List<ArtVO> list = new ArrayList<ArtVO>();
			ArtVO artVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);

				rs = pstmt.executeQuery();
				
				
				while (rs.next()) {
				
					artVO = new ArtVO();
					artVO.setArticleId(rs.getInt("ARTICLE_ID"));
					artVO.setMemberId(rs.getInt("MEMBER_ID"));
					artVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
					artVO.setArticleStatus(rs.getInt("ARTICLE_STATUS"));
					artVO.setArticleTitle(rs.getString("ARTICLE_TITLE"));
					artVO.setArticleContent(rs.getString("ARTICLE_CONTENT"));
					artVO.setArticleLikesAmount(rs.getInt("ARTICLE_LIKES_AMOUNT"));
					artVO.setArticlePostTime(rs.getTimestamp("ARTICLE_POST_TIME"));
					artVO.setArticleUpdateTime(rs.getTimestamp("ARTICLE_UPDATE_TIME"));

					list.add(artVO); 
				}
				

			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
		
		//test
		
		public static void main(String[] args) {

			ArtJDBCDAO dao = new ArtJDBCDAO();
//
//			// 新增
//			ArtVO artVO1 = new ArtVO();
//			artVO1.setMemberId(1);
//			artVO1.setArticleTypeId(2);
//			artVO1.setArticleStatus(2);
//			artVO1.setArticleTitle("二篇測試文章");
//			artVO1.setArticleContent("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//			artVO1.setArticleLikesAmount(0);
//			dao.insert(artVO1);

//			// 修改
			
//			ArtVO artVO2 = new ArtVO();
//			artVO2.setArticleTypeId(5);
//			artVO2.setArticleStatus(0);
//			artVO2.setArticleTitle("修改標題");
//			artVO2.setArticleContent("修改內容");
//			artVO2.setArticleLikesAmount(4);
//			artVO2.setArticlePostTime(null);
//			artVO2.setArticleId(5);
//			dao.update(artVO2);
			
//
//			// 刪除
//			dao.delete(4);
//
//			// 查詢
			
//			ArtVO artVO3 = dao.findByPrimaryKey(2);
//			System.out.print("文章編號:" + artVO3.getArticleId() + ", ");
//			System.out.print("會員編號:" + artVO3.getMemberId() + ", ");
//			System.out.print("文章類別:" + artVO3.getArticleTypeId() + ", ");
//			System.out.print("文章狀態:" + artVO3.getArticleStatus() + ", ");
//			System.out.println("文章標題:" + artVO3.getArticleTitle());
//			System.out.println("文章內容:" + artVO3.getArticleContent());
//			System.out.println("按讚:" + artVO3.getArticleLikesAmount());
//			System.out.print("文章上傳時間" + artVO3.getArticlePostTime() + ",\t");
//			System.out.println("文章修改時間" + artVO3.getArticleUpdateTime());
//			System.out.println("---------------------");


//
//			// 查詢
//			String dateOrId = "ARTICLE_LIKES_AMOUNT";
//			List<ArtVO> list = dao.getAll(dateOrId);
			List<ArtVO> list = dao.getAll();
			for(ArtVO artVO4 : list) {
				System.out.print("文章編號:" + artVO4.getArticleId() + ", ");
				System.out.print("會員編號:" + artVO4.getMemberId() + ", ");
				System.out.print("文章類別:" + artVO4.getArticleTypeId() + ", ");
				System.out.print("文章狀態:" + artVO4.getArticleStatus() + ", ");
				System.out.println("文章標題:" + artVO4.getArticleTitle());
				System.out.println("文章內容:" + artVO4.getArticleContent());
				System.out.println("按讚數:" + artVO4.getArticleLikesAmount()+ "\t");
				System.out.print("文章上傳時間" + artVO4.getArticlePostTime() + "\t");
				System.out.println("文章修改時間" + artVO4.getArticleUpdateTime());
				System.out.println("---------------------");
			}
			


		}//test
		

		

	

		
			
		}
		
		
		
		
	


