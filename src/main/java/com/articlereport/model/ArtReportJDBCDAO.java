package com.articlereport.model;

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



public class ArtReportJDBCDAO implements ArtReportDAO_interface {
	
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
			"insert into ARTICLE_REPORT (MEMBER_ID, ARTICLE_ID, ADMIN_ID, REPORT_STATUS, REPORT_CAUSE)"
					+" values (?, ?, ?, ?, ?)";
		private static final String GET_ALL = 
			"select ARTICLE_REPORT_ID, MEMBER_ID, ARTICLE_ID,  ADMIN_ID, REPORT_TIME,"+
					" REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE from ARTICLE_REPORT order by ARTICLE_REPORT_ID";
		private static final String GET_ONE = 
			"select ARTICLE_REPORT_ID, MEMBER_ID, ARTICLE_ID,  ADMIN_ID, REPORT_TIME,"+
						" REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE from ARTICLE_REPORT where ARTICLE_REPORT_ID=?";
		private static final String DELETE = 
			"delete from ARTICLE_REPORT where ARTICLE_REPORT_ID = ?";
		private static final String UPDATE = 
			"update ARTICLE_REPORT set ADMIN_ID=?, REPORT_RESULT=?, REPORT_STATUS=?  where ARTICLE_REPORT_ID = ?";

		
		
		@Override
		public void insert(ArtReportVO artReportVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT);
				
				pstmt.setInt(1, artReportVO.getMemberId());
				pstmt.setInt(2, artReportVO.getArticleId());
				pstmt.setInt(3, artReportVO.getAdminId());				
				pstmt.setInt(4, artReportVO.getReportStatus());
				pstmt.setString(5, artReportVO.getReportCause());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace(System.err);
					}
				}
				
				if(con != null) {
					try {
						con.close();
					} catch (Exception e) {
						
						e.printStackTrace(System.err);
					}
				}
			}
			
		}
		
		@Override
		public void update(ArtReportVO artReportVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, artReportVO.getAdminId());
				pstmt.setString(2, artReportVO.getReportResult());
				pstmt.setInt(3, artReportVO.getReportStatus());
				pstmt.setInt(4, artReportVO.getArticleReportId());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}
		
		@Override
		public void delete(Integer articleReportId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1, articleReportId);

				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
		public ArtReportVO findByPrimaryKey(Integer articleReportId) {
			
			ArtReportVO artReportVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE);
				
				pstmt.setInt(1, articleReportId);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					artReportVO = new ArtReportVO();
					artReportVO.setArticleReportId(rs.getInt("ARTICLE_REPORT_ID"));
					artReportVO.setMemberId(rs.getInt("MEMBER_ID"));
					artReportVO.setArticleId(rs.getInt("ARTICLE_ID"));
					artReportVO.setAdminId(rs.getInt("ADMIN_ID"));
					artReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
					artReportVO.setReportResult(rs.getString("REPORT_RESULT"));
					artReportVO.setReportStatus(rs.getInt("REPORT_STATUS"));
					artReportVO.setReportCause(rs.getString("REPORT_CAUSE"));					
					
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
			
			return artReportVO;
		}
		
		
		@Override
		public List<ArtReportVO> getAll() {
			
			List<ArtReportVO> list = new ArrayList<ArtReportVO>();
			ArtReportVO artReportVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);
				
				rs= pstmt.executeQuery();
				
				while(rs.next()) {
					
					artReportVO = new ArtReportVO();
					artReportVO.setArticleReportId(rs.getInt("ARTICLE_REPORT_ID"));
					artReportVO.setMemberId(rs.getInt("MEMBER_ID"));
					artReportVO.setArticleId(rs.getInt("ARTICLE_ID"));
					artReportVO.setAdminId(rs.getInt("ADMIN_ID"));
					artReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
					artReportVO.setReportResult(rs.getString("REPORT_RESULT"));
					artReportVO.setReportStatus(rs.getInt("REPORT_STATUS"));
					artReportVO.setReportCause(rs.getString("REPORT_CAUSE"));
					list.add(artReportVO);
					
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
}
