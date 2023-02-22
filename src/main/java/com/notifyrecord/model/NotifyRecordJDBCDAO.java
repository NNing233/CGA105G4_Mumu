package com.notifyrecord.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class NotifyRecordJDBCDAO implements NotifyRecordDAO_interface {
	
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
			"INSERT INTO notify_record (member_id,notify_content,is_read,notify_datetime) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT member_id,notify_record_id,notify_content,is_read,notify_datetime FROM notify_record order by notify_record_id";
		private static final String GET_ONE_STMT = 
			"SELECT member_id,notify_record_id,notify_content,is_read,notify_datetime FROM notify_record where notify_record_id = ?";
		private static final String DELETE = 
			"DELETE FROM notify_record where notify_record_id = ?";
		private static final String UPDATE = 
			"UPDATE notify_record set member_id=? notify_content=?, is_read=?, notify_datetime=? where notify_record_id = ?";
	
	
	@Override
	public void insert(NotifyRecordVO notifyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, notifyRecordVO.getMemberId());
			pstmt.setString(2, notifyRecordVO.getNotifyContent());
			pstmt.setByte(3, notifyRecordVO.getIsRead());
			pstmt.setDate(4, notifyRecordVO.getNotifyDatetime() );
			
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
	public void update(NotifyRecordVO notifyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, notifyRecordVO.getMemberId());
			pstmt.setString(2, notifyRecordVO.getNotifyContent());
			pstmt.setByte(3, notifyRecordVO.getIsRead());
			pstmt.setDate(4, notifyRecordVO.getNotifyDatetime() );

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
	public void delete(Integer notify_record_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notify_record_id);

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
	public NotifyRecordVO findByPrimaryKey(Integer notify_record_id) {
		NotifyRecordVO notifyRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notify_record_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				notifyRecordVO = new NotifyRecordVO();
				notifyRecordVO.setNotifyRecordId(rs.getInt("empno"));
				notifyRecordVO.setMemberId(rs.getInt("ename"));
				notifyRecordVO.setNotifyContent(rs.getString("job"));
				notifyRecordVO.setIsRead(rs.getByte("hiredate"));
				notifyRecordVO.setNotifyDatetime(rs.getDate("sal"));

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
		return notifyRecordVO;
	}
	@Override
	public List<NotifyRecordVO> getAll() {
		List<NotifyRecordVO> list = new ArrayList<NotifyRecordVO>();
		NotifyRecordVO notifyRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				notifyRecordVO = new NotifyRecordVO();
				notifyRecordVO.setNotifyRecordId(rs.getInt("empno"));
				notifyRecordVO.setMemberId(rs.getInt("ename"));
				notifyRecordVO.setNotifyContent(rs.getString("job"));
				notifyRecordVO.setIsRead(rs.getByte("hiredate"));
				notifyRecordVO.setNotifyDatetime(rs.getDate("sal"));

				list.add(notifyRecordVO); // Store the row in the list
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
		
		NotifyRecordJDBCDAO dao = new NotifyRecordJDBCDAO();
		
		dao.delete(2);
	}
}
