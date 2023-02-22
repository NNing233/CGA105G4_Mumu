package com.prop.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;

import com.prop.model.*;

public class PropJDBCDAO implements PropDAO_interface {
	
	
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
		"INSERT INTO proposal (MEMBER_ID,PROPOSAL_TYPE_ID,PROPOSAL_NAME,PROPOSAL_APPROVAL_STATUS,PROPOSAL_STATUS,PROPOSAL_START_DATETIME,PROPOSAL_END_DATETIME,TARGET_DONATE_MONEY,ACCUMULATIVE_DONATE_MONEY,PROPOSAL_ARTICLE,PROPOSAL_PICTURE,EXTENDED_TIMES,PRODUCT_PRODUCE_TIME,TARGET_DELIVERY_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM proposal order by PROPOSAL_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM proposal where PROPOSAL_ID = ?";
	private static final String DELETE = 
		"DELETE FROM proposal where PROPOSAL_ID = ?";
	private static final String UPDATE = 
		"UPDATE proposal set PROPOSAL_APPROVAL_STATUS=?, PROPOSAL_STATUS=?, PROPOSAL_START_DATETIME=?, PROPOSAL_END_DATETIME=?, PROPOSAL_ARTICLE=?, PRODUCT_PRODUCE_TIME=?, TARGET_DELIVERY_TIME=? WHERE PROPOSAL_ID=?";
//	private static final String JOIN_MEMBER = 
//		"SELECT member_ID FROM proposal RIGHT JOIN  ON  = ";
//	
//	private static final String JOIN_OPTION =
//		"SELECT PROPOSAL_ID FROM proposal_option FULL OUTER JOIN   ";
	
	private static final String UPDATESTATUS = "UPDATE proposal set PROPOSAL_APPROVAL_STATUS=? WHERE PROPOSAL_ID=?";
	
	
		private static final String SETEXTEND = 
			"UPDATE proposal SET EXTENDED_DAYS = ? where PROPOSAL_ID = ?";
		private static final String DROP = 
			"UPDATE proposal SET PROPOSAL_STATUS = ? where PROPOSAL_ID = ?";
		private static final String PROPTYPE2WISH= 
				"UPDATE proposal SET PROPOSAL_TYPE_ID = ? where PROPOSAL_ID = ?";
		
		private static final String EXTEND = 
			"UPDATE proposal set PROPOSAL_END_DATETIME=?, EXTENDED_TIMES=?, PRODUCT_PRODUCE_TIME=?, TARGET_DELIVERY_TIME=? WHERE PROPOSAL_ID=?";
		
		private static final String UPDATE_PropsalStatus_ByProposalId = 
				"UPDATE proposal set PROPOSAL_STATUS=? WHERE PROPOSAL_ID=?";
		
		private static final String UPDATE_Propsal_ByProposalId = 
				"UPDATE proposal set PROPOSAL_NAME=?, MEMBER_ID=?, PROPOSAL_TYPE_ID=?, PROPOSAL_STATUS=?, PROPOSAL_ARTICLE=?, PROPOSAL_PICTURE=?, ACCUMULATIVE_DONATE_MONEY=?, TARGET_DONATE_MONEY=?, EXTENDED_DAYS=?, EXTENDED_TIMES=?, PROPOSAL_START_DATETIME=?, PROPOSAL_END_DATETIME=?, PRODUCT_PRODUCE_TIME=?, TARGET_DELIVERY_TIME=? WHERE PROPOSAL_ID=?";
		
		private static final String UPDATE_Propsal_ByProposalId_WithoutStatus = 
				"UPDATE proposal set PROPOSAL_NAME=?, MEMBER_ID=?, PROPOSAL_TYPE_ID=?, PROPOSAL_ARTICLE=?, PROPOSAL_PICTURE=?, ACCUMULATIVE_DONATE_MONEY=?, TARGET_DONATE_MONEY=?, EXTENDED_DAYS=?, EXTENDED_TIMES=?, PROPOSAL_START_DATETIME=?, PROPOSAL_END_DATETIME=?, PRODUCT_PRODUCE_TIME=?, TARGET_DELIVERY_TIME=? WHERE PROPOSAL_ID=?";
		
		private static final String GET_TheProposal_ByMemberId_STMT = 
				"SELECT * FROM proposal where MEMBER_ID=? order by PROPOSAL_ID";
		
	@Override
	public void insert(PropVO propVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, propVO.getMemberId());
			pstmt.setInt(2, propVO.getProposalTypeId());
			pstmt.setString(3, propVO.getProposalName());
			pstmt.setInt(4, propVO.getProposalApprovalStatus().getValue());
			pstmt.setInt(5, propVO.getProposalStatus().getValue());
			pstmt.setDate(6, propVO.getProposalStartDatetime());
			pstmt.setDate(7, propVO.getProposalEndDatetime());
			pstmt.setInt(8, propVO.getTargetDonateMoney());
			pstmt.setInt(9, propVO.getAccumulativeDonateMoney());
			pstmt.setString(10, propVO.getProposalArticle());
			pstmt.setBytes(11, propVO.getProposalPicture());
			pstmt.setInt(12, propVO.getExtendedTimes());
			pstmt.setDate(13, propVO.getProductProduceTime());
			pstmt.setDate(14, propVO.getTargetDeliveryTime());

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
	public void update(PropVO propVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, propVO.getProposalApprovalStatus().getValue());
			pstmt.setInt(2, propVO.getProposalStatus().getValue());
			pstmt.setDate(3, propVO.getProposalStartDatetime());
			pstmt.setDate(4, propVO.getProposalEndDatetime());
			pstmt.setString(5, propVO.getProposalArticle());
			pstmt.setDate(6, propVO.getProductProduceTime());
			pstmt.setDate(7, propVO.getTargetDeliveryTime());
			pstmt.setInt(8, propVO.getProposalId());
			
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
	public void delete(Integer proposalId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, proposalId);

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
	public PropVO findByPrimaryKey(Integer proposalId) {

		PropVO propVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, proposalId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				propVO = new PropVO();
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(Status.from(rs.getInt("PROPOSAL_APPROVAL_STATUS")));
				propVO.setProposalStatus(ProposalStatus.from(rs.getInt("PROPOSAL_STATUS")));
				propVO.setProposalStartDatetime(rs.getDate("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getDate("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
				propVO.setExtendedDays(rs.getInt("EXTENDED_DAYS"));
				propVO.setArticleId(rs.getInt("ARTICLE_ID"));
				if(rs.wasNull()) { // 判斷最近一欄位是否為空值
					propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));	
				} 
				else {
				propVO.setProposalTypeId(7);	
				}
//				propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				
				propVO.setProductProduceTime(rs.getDate("PRODUCT_PRODUCE_TIME"));	
				propVO.setTargetDeliveryTime(rs.getDate("TARGET_DELIVERY_TIME"));	
				
				
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
		return propVO;
	}

	@Override
	public List<PropVO> getTheProposal(Integer memberId) {
		List<PropVO> list = new ArrayList<PropVO>();
		PropVO propVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TheProposal_ByMemberId_STMT);
			pstmt.setInt(1,memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				propVO = new PropVO();
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(Status.from(rs.getInt("PROPOSAL_APPROVAL_STATUS")));
				propVO.setProposalStatus(ProposalStatus.from(rs.getInt("PROPOSAL_STATUS")));
				propVO.setProposalStartDatetime(rs.getDate("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getDate("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
				propVO.setExtendedDays(rs.getInt("EXTENDED_DAYS"));
				propVO.setArticleId(rs.getInt("ARTICLE_ID"));
				propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				propVO.setProductProduceTime(rs.getDate("PRODUCT_PRODUCE_TIME"));	
				propVO.setTargetDeliveryTime(rs.getDate("TARGET_DELIVERY_TIME"));	
				list.add(propVO); // Store the row in the list
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

	@Override
	public void updateStatus(PropVO propVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, propVO.getProposalApprovalStatus().getValue());
			pstmt.setInt(2, propVO.getProposalId());
			
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
	public void updatePropsalStatus(Integer proposalId, Integer proposaStatus) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PropsalStatus_ByProposalId);

			pstmt.setInt(1, proposaStatus);
			pstmt.setInt(2, proposalId);
		
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
	public void setExtend(Integer proposalId, Integer extendedDays) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SETEXTEND);
			pstmt.setInt(1, extendedDays);
			pstmt.setInt(2, proposalId);
			
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
	public void drop(Integer proposalId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DROP);
			pstmt.setInt(1, new Integer(1));
			pstmt.setInt(2, proposalId);

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
	public void propType2wish(Integer proposalId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(PROPTYPE2WISH);
			pstmt.setInt(1, new Integer(0));
			pstmt.setInt(2, proposalId);
			
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
	public void extend(PropVO propVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(EXTEND);
			Calendar c = Calendar.getInstance();
			
			c.setTime(propVO.getProposalEndDatetime());
			c.add(Calendar.DATE, propVO.getExtendedDays());
			Timestamp time1 = new Timestamp(c.getTimeInMillis());
			
			c.setTime(propVO.getProductProduceTime());
			c.add(Calendar.DATE, propVO.getExtendedDays());
			java.util.Date utilDate = (java.util.Date)c.getTime();
			Date time2 = new Date(utilDate.getTime());
			
			c.setTime(propVO.getProductProduceTime());
			c.add(Calendar.DATE, propVO.getExtendedDays());
			utilDate = (java.util.Date)c.getTime();
			Date time3 = new Date(utilDate.getTime());
			
			pstmt.setTimestamp(1, time1);
			pstmt.setInt(2, propVO.getExtendedTimes()+1);
			
			pstmt.setDate(3, time2);
			pstmt.setDate(4, time3);
			pstmt.setInt(5, propVO.getProposalId());

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
	public void updateFundingProposalWithoutProposalStatus(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Propsal_ByProposalId_WithoutStatus);
			
			pstmt.setString(1, proposalName);
			pstmt.setInt(2, memberId);
			pstmt.setInt(3, prodTypeId);
			pstmt.setString(4, proposalArticle);
			pstmt.setBytes(5, uploadProposalImg);
			pstmt.setInt(6, accumulativeDonateMoney);
			pstmt.setInt(7, targetDonateMoney);
			pstmt.setInt(8, extendedDays);
			pstmt.setInt(9, extendedTimes);
			pstmt.setDate(10, proposalStartDatetime);
			pstmt.setDate(11, proposalEndDatetime);
			pstmt.setDate(12, productProduceTime);
			pstmt.setDate(13, targetDeliveryTime);
			pstmt.setInt(14, proposalId);
			
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
	public void updateFundingProposal(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId, Integer selectedProposalStatus,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Propsal_ByProposalId);
			
			pstmt.setString(1, proposalName);
			pstmt.setInt(2, memberId);
			pstmt.setInt(3, prodTypeId);
			pstmt.setInt(4, selectedProposalStatus);
			pstmt.setString(5, proposalArticle);
			pstmt.setBytes(6, uploadProposalImg);
			pstmt.setInt(7, accumulativeDonateMoney);
			pstmt.setInt(8, targetDonateMoney);
			pstmt.setInt(9, extendedDays);
			pstmt.setInt(10, extendedTimes);
			pstmt.setDate(11, proposalStartDatetime);
			pstmt.setDate(12, proposalEndDatetime);
			pstmt.setDate(13, productProduceTime);
			pstmt.setDate(14, targetDeliveryTime);
			pstmt.setInt(15, proposalId);
			
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
	public List<PropVO> getAll() {
		List<PropVO> list = new ArrayList<PropVO>();
		PropVO propVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				propVO = new PropVO();
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(Status.from(rs.getInt("PROPOSAL_APPROVAL_STATUS")));
				propVO.setProposalStatus(ProposalStatus.from(rs.getInt("PROPOSAL_STATUS")));
				propVO.setProposalStartDatetime(rs.getDate("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getDate("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
				propVO.setExtendedDays(rs.getInt("EXTENDED_DAYS"));
				propVO.setArticleId(rs.getInt("ARTICLE_ID"));
				propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				propVO.setProductProduceTime(rs.getDate("PRODUCT_PRODUCE_TIME"));	
				propVO.setTargetDeliveryTime(rs.getDate("TARGET_DELIVERY_TIME"));	
				list.add(propVO); // Store the row in the list
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
}