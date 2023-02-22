package com.proposalreport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropReportJDBCDAO implements PropReportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mumu?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "p@ssw0rd";

	private static final String INSERT_STMT = "INSERT INTO proposal_report (MEMBER_ID, PROPOSAL_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PROPOSAL_REPORT_ID, MEMBER_ID, PROPOSAL_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE FROM proposal_report order by REPORT_TIME";
	private static final String GET_ONE_STMT = "SELECT PROPOSAL_REPORT_ID, MEMBER_ID, PROPOSAL_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE FROM proposal_report where PROPOSAL_REPORT_ID = ?";
	private static final String DELETE = "DELETE FROM proposal_report where PROPOSAL_REPORT_ID = ?";
	private static final String UPDATE = "UPDATE proposal_report set MEMBER_ID=?, PROPOSAL_ID=?, ADMIN_ID=?, REPORT_TIME=?, REPORT_RESULT=?, REPORT_STATUS=?, REPORT_CAUSE=? where PROPOSAL_REPORT_ID = ?";

	@Override
	public void insert(PropReportVO propReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, propReportVO.getMemberID());
			pstmt.setInt(2, propReportVO.getProposalID());
			pstmt.setInt(3, propReportVO.getAdminID());
			pstmt.setTimestamp(4, propReportVO.getReportTime());
			pstmt.setString(5, propReportVO.getReportResult());
			pstmt.setByte(6, propReportVO.getReportStatus());
			pstmt.setString(7, propReportVO.getReportCause());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace(); // 可知道原因列出錯誤路徑((line numbers + call stack)) > e.toString 只知道拋出了什麼例外+錯誤值>
									// e.getMessage 錯誤值
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public void update(PropReportVO propReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, propReportVO.getMemberID());
			pstmt.setInt(2, propReportVO.getProposalID());
			pstmt.setInt(3, propReportVO.getAdminID());
			pstmt.setTimestamp(4, propReportVO.getReportTime());
			pstmt.setString(5, propReportVO.getReportResult());
			pstmt.setByte(6, propReportVO.getReportStatus());
			pstmt.setString(7, propReportVO.getReportCause());
			pstmt.setInt(8, propReportVO.getProposalReportID());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public void delete(Integer proposalReportID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, proposalReportID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
	public PropReportVO findByPrimaryKey(Integer proposalReportID) {
		PropReportVO propReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, proposalReportID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				propReportVO = new PropReportVO();
				propReportVO.setProposalReportID(rs.getInt("PROPOSAL_REPORT_ID"));
				propReportVO.setMemberID(rs.getInt("MEMBER_ID"));
				propReportVO.setProposalID(rs.getInt("PROPOSAL_ID"));
				propReportVO.setAdminID(rs.getInt("ADMIN_ID"));
				propReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
				propReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				propReportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
				propReportVO.setReportCause(rs.getString("REPORT_CAUSE"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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
		return propReportVO;
	}

	@Override
	public List<PropReportVO> getAll() {
		List<PropReportVO> list = new ArrayList<PropReportVO>();
		PropReportVO propReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				propReportVO = new PropReportVO();
				propReportVO.setProposalReportID(rs.getInt("PROPOSAL_REPORT_ID"));
				propReportVO.setMemberID(rs.getInt("MEMBER_ID"));
				propReportVO.setProposalID(rs.getInt("PROPOSAL_ID"));
				propReportVO.setAdminID(rs.getInt("ADMIN_ID"));
				propReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
				propReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				propReportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
				propReportVO.setReportCause(rs.getString("REPORT_CAUSE"));
				list.add(propReportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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

		PropReportJDBCDAO dao = new PropReportJDBCDAO();

		// 新增
//		PropReportVO propReportVO1 = new PropReportVO();
//		propReportVO1.setMemberID(2);
//		propReportVO1.setProposalID(1);
//		propReportVO1.setAdminID(2);
//		propReportVO1.setReportTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		propReportVO1.setReportResult(null);
//		propReportVO1.setReportStatus((byte) 1);
//		propReportVO1.setReportCause("測試新增");
//		dao.insert(propReportVO1);

		// 修改
//		PropReportVO propReportVO2 = new PropReportVO();
//		propReportVO2.setMemberID(2);
//		propReportVO2.setProposalID(1);
//		propReportVO2.setAdminID(2);
//		propReportVO2.setReportTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		propReportVO2.setReportResult("通過");
//		propReportVO2.setReportStatus((byte) 0);
//		propReportVO2.setReportCause("測試修改");
//		propReportVO2.setProposalReportID(2);
//		dao.update(propReportVO2);
		
		// 刪除
//		dao.delete(2);

		// 查詢
//		PropReportVO propReportVO3 = dao.findByPrimaryKey(2);
//		System.out.print(propReportVO3.getProposalReportID() + ",");
//		System.out.print(propReportVO3.getMemberID() + ",");
//		System.out.print(propReportVO3.getProposalID() + ",");
//		System.out.print(propReportVO3.getAdminID() + ",");
//		System.out.print(propReportVO3.getReportTime() + ",");
//		System.out.print(propReportVO3.getReportResult() + ",");
//		System.out.println(propReportVO3.getReportStatus() + ",");
//		System.out.println(propReportVO3.getReportCause());
//		System.out.println("---------------------");

		// 查詢
		List<PropReportVO> list = dao.getAll();
		for (PropReportVO aPropReport : list) {
			System.out.print(aPropReport.getProposalReportID() + ",");
			System.out.print(aPropReport.getMemberID() + ",");
			System.out.print(aPropReport.getProposalID() + ",");
			System.out.print(aPropReport.getAdminID() + ",");
			System.out.print(aPropReport.getReportTime() + ",");
			System.out.print(aPropReport.getReportResult() + ",");
			System.out.print(aPropReport.getReportStatus() + ",");
			System.out.print(aPropReport.getReportCause());
			System.out.println();
		}

	}
}
