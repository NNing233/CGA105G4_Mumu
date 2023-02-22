package com.prop2.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;

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
		"INSERT INTO proposal (MEMBER_ID,PROPOSAL_NAME,PROPOSAL_APPROVAL_STATUS,PROPOSAL_STATUS,PROPOSAL_START_DATETIME,PROPOSAL_END_DATETIME,TARGET_DONATE_MONEY,ACCUMULATIVE_DONATE_MONEY,PROPOSAL_ARTICLE,EXTENDED_TIMES,PROPOSAL_TYPE_ID,PRODUCT_PRODUCE_TIME,TARGET_DELIVERY_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select * FROM PROPOSAL WHERE PROPOSAL_TYPE_ID in (select PROPOSAL_TYPE_ID from proposal_type where PROPOSAL_TYPE_STATUS=1) AND PROPOSAL_STATUS>1 order by PROPOSAL_ID";
	private static final String GET_POPULAR_PROP_STMT = 
			"SELECT * FROM proposal WHERE PROPOSAL_TYPE_ID in (select PROPOSAL_TYPE_ID from proposal_type where PROPOSAL_TYPE_STATUS=1) AND PROPOSAL_STATUS>1 order by ACCUMULATIVE_DONATE_MONEY DESC LIMIT 1";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM proposal where PROPOSAL_ID = ?";
	private static final String DELETE = 
		"DELETE FROM proposal where PROPOSAL_ID = ?";
	private static final String UPDATE = 
		"UPDATE proposal set PROPOSAL_APPROVAL_STATUS=?, PROPOSAL_STATUS=?, PROPOSAL_START_DATETIME=?, PROPOSAL_END_DATETIME=?, PROPOSAL_ARTICLE=?, PRODUCT_PRODUCE_TIME=?, TARGET_DELIVERY_TIME=? WHERE PROPOSAL_ID=?";

	private static final String GET_TOTAL_NUM_STMT = 
			"SELECT count( * ) as TOTAL_PROPOSAL_NUM FROM PROPOSAL";
	
	private static final String GET_PROPOSAL_FROM_TYPE = 
			"SELECT * FROM proposal where PROPOSAL_TYPE_ID = ? AND PROPOSAL_STATUS>1 order by PROPOSAL_ID";
	
	private static final String CUSTOM_PROPOSAL_STMT = 
			"select * FROM PROPOSAL";
	
	private static final String UPDATE_ACCUMULATIVE_DONATE_MONEY = 
			"UPDATE proposal set ACCUMULATIVE_DONATE_MONEY=? WHERE PROPOSAL_ID=?";
	
	private static final String GET_SUCCESS_PROP = 
			"select * FROM PROPOSAL WHERE PROPOSAL_STATUS > 2 order by PROPOSAL_ID";
	
	
	
	
	@Override
	public List<PropVO> getCompositeQuery(Integer proposalTypeId,Integer min,Integer max) {
		
		String propTypeStmt = (proposalTypeId==0)?"":"PROPOSAL_TYPE_ID="+proposalTypeId;
		String minStmt = (min==-1)?"":"PROPOSAL_OPTION_PRICE>="+min;
		String maxStmt = (max==-1)?"":"PROPOSAL_OPTION_PRICE<="+max;
		
		String finalStmt = "";
		
		if(propTypeStmt.isEmpty() && minStmt.isEmpty() && maxStmt.isEmpty()) {
			finalStmt = "";
		} else if (!propTypeStmt.isEmpty() && minStmt.isEmpty() && maxStmt.isEmpty()) {
			finalStmt = " WHERE "+ propTypeStmt;
		} else if(!propTypeStmt.isEmpty() && !minStmt.isEmpty() && maxStmt.isEmpty()) {
			finalStmt = " WHERE "+ propTypeStmt+ " AND PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+ minStmt +")";
		} else if(!propTypeStmt.isEmpty() && !minStmt.isEmpty() && !maxStmt.isEmpty()) {
			finalStmt = " WHERE "+ propTypeStmt+ " AND PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+ minStmt +" AND "+maxStmt+")";
		} else if(propTypeStmt.isEmpty() && !minStmt.isEmpty() && !maxStmt.isEmpty()) {
			finalStmt = " WHERE PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+ minStmt +" AND "+maxStmt+")";
		} else if(propTypeStmt.isEmpty() && minStmt.isEmpty() && !maxStmt.isEmpty()) {
			finalStmt = " WHERE PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+maxStmt+")";
		} else if(propTypeStmt.isEmpty() && !minStmt.isEmpty() && maxStmt.isEmpty()) {
			finalStmt = " WHERE PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+minStmt+")";
		} else {
			finalStmt = " WHERE "+ propTypeStmt+ " AND PROPOSAL_ID in (select distinct PROPOSAL_ID FROM PROPOSAL_OPTION WHERE "+ maxStmt +")";
		}
		

		List<PropVO> list = new ArrayList<PropVO>();
		PropVO propVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			System.out.println(CUSTOM_PROPOSAL_STMT + finalStmt+ " AND PROPOSAL_STATUS>1 order by PROPOSAL_ID");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(CUSTOM_PROPOSAL_STMT + finalStmt+ " AND PROPOSAL_STATUS>1 order by PROPOSAL_ID");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
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
	public List<PropVO> getProposalFromType(Integer proposalTypeId) {
		List<PropVO> list = new ArrayList<PropVO>();
		PropVO propVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROPOSAL_FROM_TYPE);
			
			pstmt.setInt(1, proposalTypeId);	
			rs = pstmt.executeQuery();

			while (rs.next()) {

				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
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
	public List<PropVO> getSuccessProp() {
		List<PropVO> list = new ArrayList<PropVO>();
		PropVO propVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SUCCESS_PROP);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
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
	public void insert(PropVO propVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, propVO.getMemberId());
			pstmt.setString(2, propVO.getProposalName());
			pstmt.setInt(3, propVO.getProposalApprovalStatus());
			pstmt.setInt(4, propVO.getProposalStatus());
			pstmt.setTimestamp(5, propVO.getProposalStartDatetime());
			pstmt.setTimestamp(6, propVO.getProposalEndDatetime());
			pstmt.setInt(7, propVO.getTargetDonateMoney());
			pstmt.setInt(8, propVO.getAccumulativeDonateMoney());
			pstmt.setString(9, propVO.getProposalArticle());
			pstmt.setInt(10, propVO.getExtendedTimes());
			pstmt.setInt(11, propVO.getProposalTypeId());
			pstmt.setDate(12, propVO.getProductProduceTime());
			pstmt.setDate(13, propVO.getTargetDeliveryTime());

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

			pstmt.setInt(1, propVO.getProposalApprovalStatus());
			pstmt.setInt(2, propVO.getProposalStatus());
			pstmt.setTimestamp(3, propVO.getProposalStartDatetime());
			pstmt.setTimestamp(4, propVO.getProposalEndDatetime());
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
	public void updateAccumulativeDonateMoney(int proposalId, int addDonateMoney, Connection con) {

		PreparedStatement pstmt = null;
		PropService propSvc = new PropService();		
		int accumulativeDonateMoney = propSvc.getOneProp(proposalId).getAccumulativeDonateMoney();
		accumulativeDonateMoney = accumulativeDonateMoney + addDonateMoney;

		try {
			
			System.out.println("accumulativeDonateMoney:" + accumulativeDonateMoney);
			System.out.println("proposalId:" + proposalId);

			pstmt = con.prepareStatement(UPDATE_ACCUMULATIVE_DONATE_MONEY);

			pstmt.setInt(1, accumulativeDonateMoney);
			pstmt.setInt(2, proposalId);
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-PropJDBCDAO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
				// propVO �]�٬� Domain objects
				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
				propVO.setArticleId(rs.getInt("ARTICLE_ID"));
				propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));	
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
	public PropVO getPopularProp() {

		PropVO propVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_POPULAR_PROP_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// propVO �]�٬� Domain objects
				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
				propVO.setArticleId(rs.getInt("ARTICLE_ID"));
				propVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
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
				// propVO �]�٬� Domain objects
				propVO = new PropVO();
				propVO.setProposalPicture(rs.getBytes("PROPOSAL_PICTURE"));
				propVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propVO.setMemberId(rs.getInt("MEMBER_ID"));
				propVO.setProposalName(rs.getString("PROPOSAL_NAME"));
				propVO.setProposalApprovalStatus(rs.getInt("PROPOSAL_APPROVAL_STATUS"));
				propVO.setProposalStatus(rs.getInt("PROPOSAL_STATUS"));
				propVO.setProposalStartDatetime(rs.getTimestamp("PROPOSAL_START_DATETIME"));
				propVO.setProposalEndDatetime(rs.getTimestamp("PROPOSAL_END_DATETIME"));
				propVO.setTargetDonateMoney(rs.getInt("TARGET_DONATE_MONEY"));
				propVO.setAccumulativeDonateMoney(rs.getInt("ACCUMULATIVE_DONATE_MONEY"));
				propVO.setProposalArticle(rs.getString("PROPOSAL_ARTICLE"));
				propVO.setProposalRejectedReason(rs.getString("PROPOSAL_REJECTED_REASON"));
				propVO.setExtendedTimes(rs.getInt("EXTENDED_TIMES"));
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
	public Integer getTotalProposalNum() {

		int totalProposalNum = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOTAL_NUM_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
					
				totalProposalNum = rs.getInt("TOTAL_PROPOSAL_NUM");	
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
		return totalProposalNum;
	}
	

	public static void main(String[] args) {

		PropJDBCDAO dao = new PropJDBCDAO();

		// �s�W
		PropVO propVO1 = new PropVO();
		propVO1.setMemberId(1);
		propVO1.setProposalName("�@�ϥծ��b����2");
		propVO1.setProposalApprovalStatus(1);
		propVO1.setProposalStatus(2);

		propVO1.setProposalStartDatetime(Timestamp.valueOf("2022-08-23 10:10:10.0"));
		propVO1.setProposalEndDatetime(Timestamp.valueOf("2023-10-23 10:10:7.0"));
		propVO1.setTargetDonateMoney(2000);
		propVO1.setAccumulativeDonateMoney(1000);
		propVO1.setProposalArticle("�������״��մ���111111");
		propVO1.setExtendedTimes(1);
		propVO1.setProposalTypeId(2);
		propVO1.setProductProduceTime(Date.valueOf("2023-12-23"));	
		propVO1.setTargetDeliveryTime(Date.valueOf("2023-12-31"));	
		dao.insert(propVO1);

		// �ק�
//		UPDATE��k���èS���Ҧ���쳣�ѻP�ק� ���OProposal Name�N�S��
		PropVO propVO2 = new PropVO();
		propVO2.setProposalId(2);
		propVO2.setMemberId(2);
		propVO2.setProposalName("���׭ק�ծ��b");
		propVO2.setProposalApprovalStatus(2);
		propVO2.setProposalStatus(3);

		propVO2.setProposalStartDatetime(Timestamp.valueOf("2022-10-21 10:10:10.0"));
		propVO2.setProposalEndDatetime(Timestamp.valueOf("2023-11-23 10:10:7.0"));
		propVO2.setTargetDonateMoney(2000);
		propVO2.setAccumulativeDonateMoney(1000);
		propVO2.setProposalArticle("���׭ק�");
		propVO2.setExtendedTimes(1);
		propVO2.setProposalTypeId(2);
		propVO2.setProductProduceTime(Date.valueOf("2023-12-31"));	
		propVO2.setTargetDeliveryTime(Date.valueOf("2024-10-31"));	
		dao.update(propVO2);

		// �R��
		dao.delete(1);
//
//		// �d��
//		�Y�O��Ʈw����� 0000-00-00�A���L�X�ӷ|�����D ��� RuntimeError:Zero data value prohibited
		PropVO propVO3 = dao.findByPrimaryKey(1);
		System.out.print(propVO3.getProposalId() + ",");
		System.out.print(propVO3.getMemberId() + ",");
		System.out.print(propVO3.getProposalName() + ",");
		System.out.print(propVO3.getProposalApprovalStatus() + ",");
		System.out.print(propVO3.getProposalStatus() + ",");
		System.out.print(propVO3.getProposalStartDatetime() + ",");
		System.out.println(propVO3.getProposalEndDatetime());
		System.out.println(propVO3.getTargetDonateMoney());
		System.out.println(propVO3.getAccumulativeDonateMoney());
		System.out.println(propVO3.getProposalArticle());
		System.out.println(propVO3.getProposalPicture());
		System.out.println(propVO3.getProposalRejectedReason());
		System.out.println(propVO3.getProductLaunchRejectedReason());
		System.out.println(propVO3.getExtendedTimes());
		System.out.println(propVO3.getArticleId());
		System.out.println(propVO3.getProposalTypeId());
		System.out.println(propVO3.getProductProduceTime());
		System.out.println(propVO3.getTargetDeliveryTime());
//		
		System.out.println("---------------------");
//
//		// �d��
//		�Y�O��Ʈw����� 0000-00-00�A���L�X�ӷ|�����D ��� RuntimeError:Zero data value prohibited
		List<PropVO> list = dao.getAll();
		for (PropVO aProp : list) {
			System.out.print(aProp.getProposalId() + ",");
			System.out.print(aProp.getMemberId() + ",");
			System.out.print(aProp.getProposalName() + ",");
			System.out.print(aProp.getProposalApprovalStatus() + ",");
			System.out.print(aProp.getProposalStatus() + ",");
			System.out.print(aProp.getProposalStartDatetime() + ",");
			System.out.println(aProp.getProposalEndDatetime());
			System.out.println(aProp.getTargetDonateMoney());
			System.out.println(aProp.getAccumulativeDonateMoney());
			System.out.println(aProp.getProposalArticle());
			System.out.println(aProp.getProposalPicture());
			System.out.println(aProp.getProposalRejectedReason());
			System.out.println(aProp.getExtendedTimes());
			System.out.println(aProp.getArticleId());
			System.out.println(aProp.getProposalTypeId());
			System.out.println();
		}
	}
}