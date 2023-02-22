package com.comment.model;

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

public class CommentJDBCDAO implements CommentDAO_inerface {
	
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
			"insert into COMMENT (ARTICLE_ID, MEMBER_ID, COMMENT_CONTENT, COMMENT_STATUS, COMMENT_PICTURE)"
					+" values (?, ?, ?, ?, ?)";
	private static final String GET_ALL =
			"select COMMENT_ID, ARTICLE_ID, MEMBER_ID, COMMENT_CONTENT, COMMENT_STATUS, COMMENT_POST_TIME,"
					+" COMMENT_UPDATE_TIME, COMMENT_PICTURE from COMMENT order by COMMENT_POST_TIME DESC";
	private static final String GET_ONE =
			"select COMMENT_ID, ARTICLE_ID, MEMBER_ID, COMMENT_CONTENT, COMMENT_STATUS, COMMENT_POST_TIME,"
					+" COMMENT_UPDATE_TIME, COMMENT_PICTURE from COMMENT where COMMENT_ID=?";
	private static final String DELETE =
		"delete from COMMENT where COMMENT_ID=?";
	private static final String UPDATE =
		"update COMMENT set COMMENT_CONTENT=?, COMMENT_STATUS=?,COMMENT_PICTURE=? where COMMENT_ID =?";
	private static final String GET_ARTICLEID =
			"select COMMENT_ID, ARTICLE_ID, MEMBER_ID, COMMENT_CONTENT, COMMENT_STATUS, COMMENT_POST_TIME,"
					+" COMMENT_UPDATE_TIME, COMMENT_PICTURE from COMMENT where ARICLE_ID=?";
	
	@Override
	public void insert(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, commentVO.getArticleId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setString(3, commentVO.getCommentContent());
			pstmt.setInt(4, commentVO.getCommentStatus());
			pstmt.setBytes(5, commentVO.getCommentPicture());
			
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
	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, commentVO.getCommentContent());
			pstmt.setInt(2, commentVO.getCommentStatus());			
			pstmt.setBytes(3, commentVO.getCommentPicture());
			pstmt.setInt(4, commentVO.getCommentId());
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
	public void delete(Integer commentId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, commentId);
			
			pstmt.executeUpdate();
			
			
		}  catch (SQLException e) {
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
	public CommentVO findByPrimaryKey(Integer commentId) {
		
		CommentVO commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setInt(1,  commentId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				commentVO = new CommentVO();
				commentVO.setCommentId(rs.getInt("COMMENT_ID"));
				commentVO.setArticleId(rs.getInt("ARTICLE_ID"));
				commentVO.setMemberId(rs.getInt("MEMBER_ID"));
				commentVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
				commentVO.setCommentStatus(rs.getInt("COMMENT_STATUS"));
				commentVO.setCommentPostTime(rs.getTimestamp("COMMENT_POST_TIME"));
				commentVO.setCommentUpdateTime(rs.getTimestamp("COMMENT_UPDATE_TIME"));
				commentVO.setCommentPicture(rs.getBytes("COMMENT_PICTURE"));
				
			}
			
			
		}  catch (SQLException e) {
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
		
		
		return commentVO;
	}

	@Override
	public List<CommentVO> getAll() {
		
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				commentVO = new CommentVO();
				commentVO.setCommentId(rs.getInt("COMMENT_ID"));
				commentVO.setArticleId(rs.getInt("ARTICLE_ID"));
				commentVO.setMemberId(rs.getInt("MEMBER_ID"));
				commentVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
				commentVO.setCommentStatus(rs.getInt("COMMENT_STATUS"));
				commentVO.setCommentPostTime(rs.getTimestamp("COMMENT_POST_TIME"));
				commentVO.setCommentUpdateTime(rs.getTimestamp("COMMENT_UPDATE_TIME"));
				commentVO.setCommentPicture(rs.getBytes("COMMENT_PICTURE"));
				list.add(commentVO);
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
     public List<CommentVO> getCommentByArtId(Integer aricleId) {
		
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTICLEID);
			
			pstmt.setInt(1,  aricleId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				commentVO = new CommentVO();
				commentVO.setCommentId(rs.getInt("COMMENT_ID"));
				commentVO.setArticleId(rs.getInt("ARTICLE_ID"));
				commentVO.setMemberId(rs.getInt("MEMBER_ID"));
				commentVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
				commentVO.setCommentStatus(rs.getInt("COMMENT_STATUS"));
				commentVO.setCommentPostTime(rs.getTimestamp("COMMENT_POST_TIME"));
				commentVO.setCommentUpdateTime(rs.getTimestamp("COMMENT_UPDATE_TIME"));
				commentVO.setCommentPicture(rs.getBytes("COMMENT_PICTURE"));
				list.add(commentVO);
			}
			
			
		}  catch (SQLException e) {
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
		
		
		return list;
	}




	



	

		
		
	
	
	
	//test
	
//	public static void main(String[] args) {
//
//		CommentDAO dao = new CommentDAO();
//
//		// �憓�
//		CommentVO coVO = new CommentVO();
//		coVO.setArticleId(1);
//		coVO.setMemberId(4);
//		coVO.setCommentContent("�憓��皜祈岫2���");
//		coVO.setCommentStatus(1);
//		File file = new File("src/main/webapp/artticle/imag/鞈���.bmp");
//		InputStream fin = null;
//		try {				
//			fin = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				fin.close();					
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		coVO.setCommentPicture(fin);
		
//		dao.insert(coVO);
//
//		// 靽格OK
//		
//		CommentVO coVO2 = new CommentVO();
//		coVO2.setCommentContent("靽格���皜祈岫");
//		coVO2.setCommentStatus(1);
//		coVO2.setCommentId(3);
//		File file = new File("src/main/webapp/artticle/imag/鞈���.bmp");
//		InputStream fin = null;
//		try {				
//			fin = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				fin.close();					
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		coVO2.setCommentPicture(fin);
//		dao.update(coVO2);
//		
//		
//
//		// ��OK
//		dao.delete(3);
//
//		// �閰�
//		CommentVO coVo3 = dao.findByPrimaryKey(2);
//		System.out.print("���蝺刻��:" + coVo3.getCommentId() + ", ");
//		System.out.print("���楊���:" + coVo3.getArticleId() + ", ");
//		System.out.println("��蝺刻��:" + coVo3.getMemberId() + ", ");
//		System.out.println("����摰�:" + coVo3.getCommentContent());
//		System.out.println("��������:" + coVo3.getCommentStatus() + ", ");
//		try {
//			FileOutputStream fos = new FileOutputStream("src/main/webapp/artticle/test01.bmp");
//			int i;
//			while ((i = artPictureVO.getArticlePicture().read()) != -1) {
//		    	fos.write(i);
//			}
//			fos.flush();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("�����敺蝵株楝敺�:src/main/webapp/artticle/test01.bmp");
//		System.out.print("�������:" + coVo3.getCommentPostTime() + ", ");
//		System.out.println("��敺楊頛舀���:" + coVo3.getCommentUpdateTime() );
//		System.out.println("---------------------");
//
//
//
//		// �閰▊k
//
//		List<CommentVO> list = dao.getAll();
//		for(CommentVO coVO3 : list) {
//			System.out.print("���蝺刻��:" + coVO3.getCommentId() + ", ");
//			System.out.print("���楊���:" + coVO3.getArticleId() + ", ");
//			System.out.println("��蝺刻��:" + coVO3.getMemberId() + ", ");
//			System.out.println("����摰�:" + coVO3.getCommentContent());
//			System.out.println("��������:" + coVO3.getCommentStatus() + ", ");
//			System.out.print("�������:" + coVO3.getCommentPostTime() + ", ");
//			System.out.println("��敺楊頛舀���:" + coVO3.getCommentUpdateTime() );
//			System.out.println("---------------------");
//		}
//		
//	}//test
}

