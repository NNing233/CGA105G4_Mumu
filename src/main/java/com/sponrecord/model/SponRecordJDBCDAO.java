package com.sponrecord.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.prop.model.ProposalStatus;

import java.sql.*;

public class SponRecordJDBCDAO implements SponRecordDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO sponsor_record (MEMBER_ID,SPONSOR_RECORD_STATUS,SPONSOR_RECORD_ADDRESS,"
			+ "SPONSOR_RECORD_PAYMENT,SPONSOR_RECORD_DELIVERY,SPONSOR_TOTAL_AMOUNT,PROPOSAL_OPTION_ID,PROPOSAL_OPTION_QUANTITY,PROPOSAL_ID) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM sponsor_record order by SPONSOR_RECORD_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM sponsor_record where SPONSOR_RECORD_ID = ?";
	private static final String DELETE = "DELETE FROM sponsor_record where SPONSOR_RECORD_ID = ?";
	private static final String UPDATE = "UPDATE sponsor_record set SPONSOR_RECORD_STATUS=?, SPONSOR_RECORD_ADDRESS=?, SPONSOR_RECORD_PAYMENT=?, SPONSOR_RECORD_DELIVERY=?, SPONSOR_TOTAL_AMOUNT=? WHERE SPONSOR_RECORD_ID=?";
	
	private static final String GET_THEOPTION_SPONRECORDS_STMT = "SELECT * FROM sponsor_record where PROPOSAL_OPTION_ID = ? order by MEMBER_ID";
	private static final String UPDATE_SponRecordStatus_STMT= "UPDATE sponsor_record set SPONSOR_RECORD_STATUS=? WHERE PROPOSAL_ID=?";
	private static final String UPDATE_TheSponRecordStatus_STMT= "UPDATE sponsor_record set SPONSOR_RECORD_STATUS=? WHERE SPONSOR_RECORD_ID=?";
	
		
		@Override
		public void updateTheSponRecordStatus(Integer sponsorRecordId, Integer sponRecordStatus) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_TheSponRecordStatus_STMT);

				pstmt.setInt(1, sponRecordStatus);
				pstmt.setInt(2, sponsorRecordId);

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateSponRecordStatus(Integer proposalId, Integer sponRecordStatus) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SponRecordStatus_STMT);

			pstmt.setInt(1, sponRecordStatus);
			pstmt.setInt(2, proposalId);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SponRecordVO> getTheOptionSponrecords(Integer proposalOptionId) {
		List<SponRecordVO> list = new ArrayList<SponRecordVO>();
		SponRecordVO sponRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THEOPTION_SPONRECORDS_STMT);

			pstmt.setInt(1, proposalOptionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				sponRecordVO = new SponRecordVO();
				sponRecordVO.setSponsorRecordId(rs.getInt("SPONSOR_RECORD_ID"));
				sponRecordVO.setMemberId(rs.getInt("MEMBER_ID"));
				sponRecordVO.setSponsorRecordStatus(SponRecordStatus.from(rs.getInt("SPONSOR_RECORD_STATUS")));
				sponRecordVO.setSponsorDatetime(rs.getTimestamp("SPONSOR_DATETIME"));
				sponRecordVO.setSponsorRecordAddress(rs.getString("SPONSOR_RECORD_ADDRESS"));
				sponRecordVO.setSponsorRecordPayment(rs.getInt("SPONSOR_RECORD_PAYMENT"));
				sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(rs.getInt("SPONSOR_RECORD_DELIVERY")));
				sponRecordVO.setSponsorTotalAmount(rs.getInt("SPONSOR_TOTAL_AMOUNT"));
				sponRecordVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				sponRecordVO.setProposalOptionQuantity(rs.getInt("PROPOSAL_OPTION_QUANTITY"));
				sponRecordVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				list.add(sponRecordVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public SponRecordVO getTheOptionSponrecord(Integer proposalOptionId) {
		List<SponRecordVO> list = new ArrayList<SponRecordVO>();
		SponRecordVO sponRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THEOPTION_SPONRECORDS_STMT);

			pstmt.setInt(1, proposalOptionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				sponRecordVO = new SponRecordVO();
				sponRecordVO.setSponsorRecordId(rs.getInt("SPONSOR_RECORD_ID"));
				sponRecordVO.setMemberId(rs.getInt("MEMBER_ID"));
				sponRecordVO.setSponsorRecordStatus(SponRecordStatus.from(rs.getInt("SPONSOR_RECORD_STATUS")));
				sponRecordVO.setSponsorDatetime(rs.getTimestamp("SPONSOR_DATETIME"));
				sponRecordVO.setSponsorRecordAddress(rs.getString("SPONSOR_RECORD_ADDRESS"));
				sponRecordVO.setSponsorRecordPayment(rs.getInt("SPONSOR_RECORD_PAYMENT"));
				sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(rs.getInt("SPONSOR_RECORD_DELIVERY")));
				sponRecordVO.setSponsorTotalAmount(rs.getInt("SPONSOR_TOTAL_AMOUNT"));
				sponRecordVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				sponRecordVO.setProposalOptionQuantity(rs.getInt("PROPOSAL_OPTION_QUANTITY"));
				sponRecordVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				list.add(sponRecordVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return sponRecordVO;
	}
	
	@Override
	public void insert(SponRecordVO sponRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, sponRecordVO.getMemberId());
			pstmt.setInt(2, sponRecordVO.getSponsorRecordStatus().getValue());

			pstmt.setString(3, sponRecordVO.getSponsorRecordAddress());
			pstmt.setInt(4, sponRecordVO.getSponsorRecordPayment());
			pstmt.setInt(5, sponRecordVO.getSponsorRecordDelivery().getValue());
			pstmt.setInt(6, sponRecordVO.getSponsorTotalAmount());
			pstmt.setInt(7, sponRecordVO.getProposalOptionId());
			pstmt.setInt(8, sponRecordVO.getProposalOptionQuantity());
			pstmt.setInt(9, sponRecordVO.getProposalId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(SponRecordVO sponRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, sponRecordVO.getSponsorRecordStatus().getValue());
			pstmt.setString(2, sponRecordVO.getSponsorRecordAddress());
			pstmt.setInt(3, sponRecordVO.getSponsorRecordPayment());
			pstmt.setInt(4, sponRecordVO.getSponsorRecordDelivery().getValue());
			pstmt.setInt(5, sponRecordVO.getSponsorTotalAmount());
			pstmt.setInt(6, sponRecordVO.getSponsorRecordId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer sponsorRecordId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sponsorRecordId);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public SponRecordVO findByPrimaryKey(Integer sponsorRecordId) {

		SponRecordVO sponRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sponsorRecordId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				sponRecordVO = new SponRecordVO();
				sponRecordVO.setSponsorRecordId(rs.getInt("SPONSOR_RECORD_ID"));
				sponRecordVO.setMemberId(rs.getInt("MEMBER_ID"));
				sponRecordVO.setSponsorRecordStatus(SponRecordStatus.from(rs.getInt("SPONSOR_RECORD_STATUS")));
				sponRecordVO.setSponsorDatetime(rs.getTimestamp("SPONSOR_DATETIME"));
				sponRecordVO.setSponsorRecordAddress(rs.getString("SPONSOR_RECORD_ADDRESS"));
				sponRecordVO.setSponsorRecordPayment(rs.getInt("SPONSOR_RECORD_PAYMENT"));
				sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(rs.getInt("SPONSOR_RECORD_DELIVERY")));
				sponRecordVO.setSponsorTotalAmount(rs.getInt("SPONSOR_TOTAL_AMOUNT"));
				sponRecordVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				sponRecordVO.setProposalOptionQuantity(rs.getInt("PROPOSAL_OPTION_QUANTITY"));
				sponRecordVO.setProposalId(rs.getInt("PROPOSAL_ID"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return sponRecordVO;
	}

	@Override
	public List<SponRecordVO> getAll() {
		List<SponRecordVO> list = new ArrayList<SponRecordVO>();
		SponRecordVO sponRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sponRecordVO = new SponRecordVO();
				sponRecordVO.setSponsorRecordId(rs.getInt("SPONSOR_RECORD_ID"));
				sponRecordVO.setMemberId(rs.getInt("MEMBER_ID"));
				sponRecordVO.setSponsorRecordStatus(SponRecordStatus.from(rs.getInt("SPONSOR_RECORD_STATUS")));
				sponRecordVO.setSponsorDatetime(rs.getTimestamp("SPONSOR_DATETIME"));
				sponRecordVO.setSponsorRecordAddress(rs.getString("SPONSOR_RECORD_ADDRESS"));
				sponRecordVO.setSponsorRecordPayment(rs.getInt("SPONSOR_RECORD_PAYMENT"));
				sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(rs.getInt("SPONSOR_RECORD_DELIVERY")));
				sponRecordVO.setSponsorTotalAmount(rs.getInt("SPONSOR_TOTAL_AMOUNT"));
				sponRecordVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				sponRecordVO.setProposalOptionQuantity(rs.getInt("PROPOSAL_OPTION_QUANTITY"));
				sponRecordVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				list.add(sponRecordVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//	public static void main(String[] args) {
//
//		SponRecordJDBCDAO dao = new SponRecordJDBCDAO();
//
////		新增
//		SponRecordVO sponRecordVO1 = new SponRecordVO();
//		sponRecordVO1.setMemberId(1);
//		sponRecordVO1.setSponsorRecordStatus(2);
//		sponRecordVO1.setSponsorDatetime(Timestamp.valueOf("2022-11-23 20:10:10.0"));
//		sponRecordVO1.setSponsorRecordAddress("高雄市三民區");
//
//		sponRecordVO1.setSponsorRecordPayment(2);
//		sponRecordVO1.setSponsorRecordDelivery(2);
//		sponRecordVO1.setSponsorTotalAmount(1800);
//		sponRecordVO1.setProposalOptionId(1);
//		sponRecordVO1.setProposalOptionQuantity(3);
//		sponRecordVO1.setProposalId(1);
////		
////		
////		dao.insert(sponRecordVO1);
//
////		更新
//		SponRecordVO sponRecordVO2 = new SponRecordVO();
//		sponRecordVO2.setSponsorRecordStatus(1);
//		sponRecordVO2.setMemberId(2);
//		sponRecordVO2.setSponsorRecordAddress("高雄市前鎮區");
//		sponRecordVO2.setSponsorRecordPayment(3);
//		sponRecordVO2.setSponsorRecordDelivery(3);
//		sponRecordVO2.setSponsorTotalAmount(8000);
//
//		dao.update(sponRecordVO2);
////
////		刪除
//		dao.delete(3);
//
////		查單一
//		SponRecordVO sponRecordVO3 = dao.findByPrimaryKey(1);
//		System.out.print(sponRecordVO3.getSponsorRecordId() + ",");
//		System.out.print(sponRecordVO3.getMemberId() + ",");
//		System.out.print(sponRecordVO3.getSponsorRecordStatus() + ",");
//		System.out.print(sponRecordVO3.getSponsorDatetime() + ",");
//		System.out.print(sponRecordVO3.getSponsorRecordAddress() + ",");
//		System.out.print(sponRecordVO3.getSponsorRecordPayment() + ",");
//		System.out.println(sponRecordVO3.getSponsorRecordDelivery());
//		System.out.println(sponRecordVO3.getSponsorTotalAmount());
//		System.out.println(sponRecordVO3.getProposalOptionId());
//		System.out.println(sponRecordVO3.getProposalOptionQuantity());
//		System.out.println(sponRecordVO3.getProposalId());
//
//		System.out.println("---------------------");
////
////		查全部
//		List<SponRecordVO> list = dao.getAll();
//		for (SponRecordVO aSponRecord : list) {
//			System.out.print(aSponRecord.getSponsorRecordId() + ",");
//			System.out.print(aSponRecord.getMemberId() + ",");
//			System.out.print(aSponRecord.getSponsorRecordStatus() + ",");
//			System.out.print(aSponRecord.getSponsorDatetime() + ",");
//			System.out.print(aSponRecord.getSponsorRecordAddress() + ",");
//			System.out.print(aSponRecord.getSponsorRecordPayment() + ",");
//			System.out.println(aSponRecord.getSponsorRecordDelivery());
//			System.out.println(aSponRecord.getSponsorTotalAmount());
//			System.out.println(aSponRecord.getProposalOptionId());
//			System.out.println(aSponRecord.getProposalOptionQuantity());
//			System.out.println(aSponRecord.getProposalId());
//
//			System.out.println();
//		}
//	}
}