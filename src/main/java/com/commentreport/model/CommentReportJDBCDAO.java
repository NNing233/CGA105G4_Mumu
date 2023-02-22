package com.commentreport.model;

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

public class CommentReportJDBCDAO implements CommentReportDAO_interface {
	
	
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
		"insert into COMMENT_REPORT (COMMENT_ID, MEMBER_ID, ADMIN_ID, REPORT_STATUS, REPORT_CAUSE)"
				+" values (?, ?, ?, ?, ?)";
	private static final String GET_ALL = 
		"select COMMENT_REPORT_ID, COMMENT_ID, MEMBER_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT,"
				+" REPORT_STATUS, REPORT_CAUSE from COMMENT_REPORT order by COMMENT_REPORT_ID";
	private static final String GET_ONE = 
		"select COMMENT_REPORT_ID, COMMENT_ID, MEMBER_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT,"
				+" REPORT_STATUS, REPORT_CAUSE from COMMENT_REPORT where COMMENT_REPORT_ID=?";
	private static final String DELETE = 
		"delete from COMMENT_REPORT where COMMENT_REPORT_ID = ?";
	private static final String UPDATE = 
		"update COMMENT_REPORT set ADMIN_ID=?, REPORT_RESULT=?, REPORT_STATUS=? where COMMENT_REPORT_ID=?";

	
	
	@Override
	public void insert(CommentReportVO commentReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, commentReportVO.getCommentId());
			pstmt.setInt(2, commentReportVO.getMemberId());
			pstmt.setInt(3, commentReportVO.getAdminId());
			pstmt.setInt(4,commentReportVO.getReportStatus());
			pstmt.setString(5, commentReportVO.getReportCause());
			
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
	public void update(CommentReportVO commentReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, commentReportVO.getAdminId());
			pstmt.setString(2, commentReportVO.getReportResult());
			pstmt.setInt(3,commentReportVO.getReportStatus());
			pstmt.setInt(4, commentReportVO.getCommentReportId());
			
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
	public void delete(Integer commentReportId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, commentReportId);
			
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
	public CommentReportVO findByPrimaryKey(Integer commentReportId) {

		CommentReportVO commentReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setInt(1, commentReportId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				commentReportVO = new CommentReportVO();
				commentReportVO.setCommentReportId(rs.getInt("COMMENT_REPORT_ID"));
				commentReportVO.setCommentId(rs.getInt("COMMENT_ID"));
				commentReportVO.setMemberId(rs.getInt("MEMBER_ID"));
				commentReportVO.setAdminId(rs.getInt("ADMIN_ID"));
				commentReportVO.setReportTime(rs.getDate("REPORT_TIME"));
				commentReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				commentReportVO.setReportStatus(rs.getInt("REPORT_STATUS"));
				commentReportVO.setReportCause(rs.getNString("REPORT_CAUSE"));				
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
		return commentReportVO;
	}
	
	
	
	@Override
	public List<CommentReportVO> getAll() {
		
		List<CommentReportVO> list = new ArrayList<CommentReportVO>();
		CommentReportVO commentReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				commentReportVO = new CommentReportVO();
				commentReportVO.setCommentReportId(rs.getInt("COMMENT_REPORT_ID"));
				commentReportVO.setCommentId(rs.getInt("COMMENT_ID"));
				commentReportVO.setMemberId(rs.getInt("MEMBER_ID"));
				commentReportVO.setAdminId(rs.getInt("ADMIN_ID"));
				commentReportVO.setReportTime(rs.getDate("REPORT_TIME"));
				commentReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				commentReportVO.setReportStatus(rs.getInt("REPORT_STATUS"));
				commentReportVO.setReportCause(rs.getNString("REPORT_CAUSE"));
				list.add(commentReportVO);			
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
//	
//	public static void main(String[] args) {
//
//		CommentReportJDBCDAO dao = new CommentReportJDBCDAO();
//
//		// �憓k
//		CommentReportVO crVO = new CommentReportVO();
//		crVO.setCommentId(2);
//		crVO.setMemberId(1);
//		crVO.setAdminId(2);
//		crVO.setReportStatus(1);
//		crVO.setReportCause("3�憓��瑼Ｚ�葫閰�");
//		dao.insert(crVO);

//		// 靽格ok
//		CommentReportVO crVO2 = new CommentReportVO();
//		crVO2.setAdminId(1);
//		crVO2.setReportResult("皜祈岫���炎���");
//		crVO2.setReportStatus(1);
//		crVO2.setCommentReportId(2);
//		dao.update(crVO2);
//
//
		// ��ok
//		dao.delete(4);
//		System.out.println("����ㄐ");
//
//		// �閰�
//		CommentReportVO crVO = dao.findByPrimaryKey(2);
//		System.out.print("瑼Ｚ���蝺刻��:" + crVO.getCommentReportId() + ", ");
//		System.out.print("���蝺刻��:" + crVO.getCommentId() + ", ");
//		System.out.print("瑼Ｚ��蝺刻��:" + crVO.getMemberId() + ", ");
//		System.out.print("鞎痊蝞∠�蝺刻��:" + crVO.getAdminId() );
//		System.out.println("瑼Ｚ��儔:" + crVO.getReportResult() + ", ");
//		System.out.print("瑼Ｚ�����:" + crVO.getReportStatus() + ", ");
//		System.out.print("瑼Ｚ����:" + crVO.getReportCause() + ", ");
//		System.out.println("瑼Ｚ����:" + crVO.getReportTime());
//		System.out.println("---------------------");
//
//		// �閰�
//		List<CommentReportVO> list = dao.getAll();
//		for(CommentReportVO crVO : list) {
//			System.out.print("瑼Ｚ���蝺刻��:" + crVO.getCommentReportId() + ", ");
//			System.out.print("���蝺刻��:" + crVO.getCommentId() + ", ");
//			System.out.print("瑼Ｚ��蝺刻��:" + crVO.getMemberId() + ", ");
//			System.out.print("鞎痊蝞∠�蝺刻��:" + crVO.getAdminId() );
//			System.out.println("瑼Ｚ��儔:" + crVO.getReportResult() + ", ");
//			System.out.print("瑼Ｚ�����:" + crVO.getReportStatus() + ", ");
//			System.out.print("瑼Ｚ����:" + crVO.getReportCause() + ", ");
//			System.out.println("瑼Ｚ����:" + crVO.getReportTime());
//			System.out.println("---------------------");			
//		}
//		
//		
//		List<ArtVO> list = dao.getAll();
//		for(ArtVO artVO4 : list) {
//			System.out.print("���楊���:" + artVO4.getArticleId() + ", ");
//			System.out.print("��蝺刻��:" + artVO4.getMemberId() + ", ");
//			System.out.print("����:" + artVO4.getArticleTypeId() + ", ");
//			System.out.print("�������:" + artVO4.getArticleStatus() + ", ");
//			System.out.println("������:" + artVO4.getArticleTitle());
//			System.out.println("���摰�:" + artVO4.getArticleContent());
//			System.out.println("����:" + artVO4.getArticleLikesAmount());
//			System.out.print("��������" + artVO4.getArticlePostTime() + ",\t");
//			System.out.println("���耨�����" + artVO4.getArticleUpdateTime());
//			System.out.println("---------------------");
//		}
//		
//	}//test


}
