package com.propreport.model;

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

import com.propreport.model.Status;

public class PropReportJDBCDAO implements PropReportDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_USER = "INSERT INTO proposal_report (MEMBER_ID, PROPOSAL_ID, REPORT_STATUS, REPORT_CAUSE) VALUES (?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT PROPOSAL_REPORT_ID, MEMBER_ID, PROPOSAL_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE FROM proposal_report order by REPORT_TIME";
	private static final String GET_ONE_STMT = "SELECT PROPOSAL_REPORT_ID, MEMBER_ID, PROPOSAL_ID, ADMIN_ID, REPORT_TIME, REPORT_RESULT, REPORT_STATUS, REPORT_CAUSE FROM proposal_report where PROPOSAL_REPORT_ID = ?";
	private static final String DELETE = "DELETE FROM proposal_report where PROPOSAL_REPORT_ID = ?";
	private static final String UPDATE = "UPDATE proposal_report set MEMBER_ID=?, PROPOSAL_ID=?, ADMIN_ID=?, REPORT_TIME=?, REPORT_RESULT=?, REPORT_STATUS=?, REPORT_CAUSE=? where PROPOSAL_REPORT_ID = ?";
	private static final String UPDATE_STATUS = "UPDATE proposal_report set ADMIN_ID=?, REPORT_RESULT=?, REPORT_STATUS=? where PROPOSAL_REPORT_ID = ? ";
	
	
	@Override
	public void insertU(PropReportVO propReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_USER);

			pstmt.setInt(1, propReportVO.getMemberId());
			pstmt.setInt(2, propReportVO.getProposalId());
			
			pstmt.setInt(3, propReportVO.getReportStatus().getValue());
			pstmt.setString(4, propReportVO.getReportCause());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, propReportVO.getMemberId());
			pstmt.setInt(2, propReportVO.getProposalId());
			pstmt.setInt(3, propReportVO.getAdminId());
			pstmt.setTimestamp(4, propReportVO.getReportTime());
			pstmt.setString(5, propReportVO.getReportResult());
			pstmt.setInt(6, propReportVO.getReportStatus().getValue());
			pstmt.setString(7, propReportVO.getReportCause());
			pstmt.setInt(8, propReportVO.getProposalReportId());
			
			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, proposalReportID);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, proposalReportID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO ä¹Ÿç¨±?‚º Domain objects
				propReportVO = new PropReportVO();
				propReportVO.setProposalReportId(rs.getInt("PROPOSAL_REPORT_ID"));
				propReportVO.setMemberId(rs.getInt("MEMBER_ID"));
				propReportVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propReportVO.setAdminId(rs.getInt("ADMIN_ID"));
				propReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
				propReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				propReportVO.setReportStatus(Status.from(rs.getInt("REPORT_STATUS")));
				propReportVO.setReportCause(rs.getString("REPORT_CAUSE"));
			}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ï¿½]ï¿½Ù¬ï¿½ Domain objects
				propReportVO = new PropReportVO();
				propReportVO.setProposalReportId(rs.getInt("PROPOSAL_REPORT_ID"));
				propReportVO.setMemberId(rs.getInt("MEMBER_ID"));
				propReportVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propReportVO.setAdminId(rs.getInt("ADMIN_ID"));
				propReportVO.setReportTime(rs.getTimestamp("REPORT_TIME"));
				propReportVO.setReportResult(rs.getString("REPORT_RESULT"));
				propReportVO.setReportStatus(Status.from(rs.getInt("REPORT_STATUS")));
				propReportVO.setReportCause(rs.getString("REPORT_CAUSE"));
				list.add(propReportVO); // Store the row in the list
			}

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

		// ?–°å¢?
//		PropReportVO propReportVO1 = new PropReportVO();
//		propReportVO1.setMemberID(2);
//		propReportVO1.setProposalID(1);
//		propReportVO1.setAdminID(2);
//		propReportVO1.setReportTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		propReportVO1.setReportResult(null);
//		propReportVO1.setReportStatus((byte) 1);
//		propReportVO1.setReportCause("æ¸¬è©¦?–°å¢?");
//		dao.insert(propReportVO1);

		// ä¿®æ”¹
//		PropReportVO propReportVO2 = new PropReportVO();
//		propReportVO2.setMemberID(2);
//		propReportVO2.setProposalID(1);
//		propReportVO2.setAdminID(2);
//		propReportVO2.setReportTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		propReportVO2.setReportResult("?šé??");
//		propReportVO2.setReportStatus((byte) 0);
//		propReportVO2.setReportCause("æ¸¬è©¦ä¿®æ”¹");
//		propReportVO2.setProposalReportID(2);
//		dao.update(propReportVO2);
		
		// ?ˆª?™¤
//		dao.delete(2);

		// ?Ÿ¥è©?
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

		// ?Ÿ¥è©?
//		List<PropReportVO> list = dao.getAll();
//		for (PropReportVO aPropReport : list) {
//			System.out.print(aPropReport.getProposalReportID() + ",");
//			System.out.print(aPropReport.getMemberID() + ",");
//			System.out.print(aPropReport.getProposalID() + ",");
//			System.out.print(aPropReport.getAdminID() + ",");
//			System.out.print(aPropReport.getReportTime() + ",");
//			System.out.print(aPropReport.getReportResult() + ",");
//			System.out.print(aPropReport.getReportStatus() + ",");
//			System.out.print(aPropReport.getReportCause());
//			System.out.println();
//		}

	}

	
	@Override
	public void updateStatus(PropReportVO propReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, propReportVO.getAdminId());
			pstmt.setString(2, propReportVO.getReportResult());
			pstmt.setInt(3, propReportVO.getReportStatus().getValue());
			pstmt.setInt(4, propReportVO.getProposalReportId());
			
			pstmt.executeUpdate();

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
}
