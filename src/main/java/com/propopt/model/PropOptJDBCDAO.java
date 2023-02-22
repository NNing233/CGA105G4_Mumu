package com.propopt.model;

import com.propprodreview.model.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class PropOptJDBCDAO implements PropOptDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO proposal_option (PROPOSAL_ID,PROPOSAL_OPTION_NAME,PROPOSAL_OPTION_PRICE,PROPOSAL_OPTION_PICTURE)"
			+ "VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM proposal_option order by PROPOSAL_OPTION_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM proposal_option where PROPOSAL_OPTION_ID = ?";
	private static final String GET_STMT_FROM_PROPOSAL_ID = "SELECT * FROM proposal_option where PROPOSAL_ID = ?";

	private static final String DELETE = "DELETE FROM proposal_option where PROPOSAL_OPTION_ID = ?";
	private static final String UPDATE = "UPDATE proposal_option set PROPOSAL_OPTION_NAME=?, PROPOSAL_OPTION_PRICE=? WHERE PROPOSAL_OPTION_ID=?";
	private static final String UPDATE_PROPOSALOPTION = "UPDATE proposal_option set REVIEW_PRODUCT_ID=? WHERE PROPOSAL_OPTION_ID=?";
	private static final String GET_THEPROPOSAL_OPTIONS_STMT = "SELECT PROPOSAL_OPTION_ID,PROPOSAL_ID,PROPOSAL_OPTION_PICTURE,PROPOSAL_OPTION_NAME,PROPOSAL_OPTION_PRICE FROM proposal_option where PROPOSAL_ID = ? order by PROPOSAL_OPTION_ID;";
	private static final String GET_PROPOPT_BY_PROPOSAL_ID = "SELECT * FROM proposal_option WHERE PROPOSAL_ID = ? order by PROPOSAL_OPTION_ID";
	
	@Override
	public void insert(PropOptVO propOptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, propOptVO.getProposalId());
			pstmt.setString(2, propOptVO.getProposalOptionName());
			pstmt.setInt(3, propOptVO.getProposalOptionPrice());
			pstmt.setBytes(4, propOptVO.getProposalOptionPicture());

			pstmt.executeUpdate();

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
	public void update(PropOptVO propOptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, propOptVO.getProposalOptionName());
			pstmt.setInt(2, propOptVO.getProposalOptionPrice());
			pstmt.setInt(3, propOptVO.getProposalOptionId());

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
	public void delete(Integer proposalOptionId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, proposalOptionId);

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
	public PropOptVO findByPrimaryKey(Integer proposalOptionId) {

		PropOptVO propOptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, proposalOptionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sponRecordVO �]�٬� Domain objects
				propOptVO = new PropOptVO();
				propOptVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));
				propOptVO.setProposalOptionPicture(rs.getBytes("PROPOSAL_OPTION_PICTURE"));
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
		return propOptVO;
	}

	public List<PropOptVO> findByProposalId(Integer proposalId) {
		List<PropOptVO> list = new ArrayList<PropOptVO>();

		PropOptVO propOptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_FROM_PROPOSAL_ID);

			pstmt.setInt(1, proposalId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sponRecordVO �]�٬� Domain objects
				propOptVO = new PropOptVO();
				propOptVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));
				list.add(propOptVO);
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
	public List<PropOptVO> getAll() {
		List<PropOptVO> list = new ArrayList<PropOptVO>();
		PropOptVO propOptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sponRecordVO �]�٬� Domain objects
				propOptVO = new PropOptVO();
				propOptVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));

				list.add(propOptVO); // Store the row in the list
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



	public static void main(String[] args) {

		PropOptJDBCDAO dao = new PropOptJDBCDAO();

		// �s�W
		PropOptVO propOptVO1 = new PropOptVO();
		propOptVO1.setProposalId(1);
		propOptVO1.setProposalOptionName("�o�O�s�W�����");
		propOptVO1.setProposalOptionPrice(200);

		dao.insert(propOptVO1);
//
//		// �ק�
		PropOptVO propOptVO2 = new PropOptVO();
		propOptVO2.setProposalOptionName("�ק諸��צW��");
		propOptVO2.setProposalOptionPrice(356);
		propOptVO2.setProposalOptionId(2);

		dao.update(propOptVO2);
//
//		// �R��
		dao.delete(3);
//
//		// �d��
		PropOptVO propOptVO3 = dao.findByPrimaryKey(5);
		System.out.print(propOptVO3.getProposalOptionId() + ",");
		System.out.print(propOptVO3.getProposalId() + ",");
		System.out.print(propOptVO3.getProposalOptionName() + ",");
		System.out.print(propOptVO3.getProposalOptionPrice() + ",");
		System.out.print(propOptVO3.getProposalOptionPicture());

		System.out.println("---------------------");

		// �d��
		List<PropOptVO> list = dao.getAll();
		for (PropOptVO aPropOpt : list) {
			System.out.print(aPropOpt.getProposalOptionId() + ",");
			System.out.print(aPropOpt.getProposalId() + ",");
			System.out.print(aPropOpt.getProposalOptionName() + ",");
			System.out.print(aPropOpt.getProposalOptionPrice() + ",");
			System.out.print(aPropOpt.getProposalOptionPicture() + ",");

			System.out.println();
		}
	}

	@Override
	public void updateReview(Integer reviewProductId, Integer proposalOptionId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			System.out.println("review product id:" + reviewProductId +",proposal option id:"+ proposalOptionId);

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PROPOSALOPTION);

			pstmt.setInt(1, reviewProductId);
		
			pstmt.setInt(2, proposalOptionId);

			pstmt.executeUpdate();

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
	public List<PropOptVO> getAllOption(Integer proposalId) {
		List<PropOptVO> list = new ArrayList<PropOptVO>();
		PropOptVO propOptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THEPROPOSAL_OPTIONS_STMT);
			pstmt.setInt(1, proposalId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodOptVO �]�٬� Domain objects
				propOptVO = new PropOptVO();
				propOptVO = new PropOptVO();
				propOptVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));
				list.add(propOptVO); // Store the row in the list
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
	public List<PropOptVO> getPropOptByProposalId(Integer proposalId) {
		List<PropOptVO> list = new ArrayList<PropOptVO>();
		PropOptVO propOptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROPOPT_BY_PROPOSAL_ID);
			pstmt.setInt(1, proposalId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sponRecordVO �]�٬� Domain objects
				propOptVO = new PropOptVO();
				propOptVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));
				propOptVO.setProposalOptionPicture(rs.getBytes("PROPOSAL_OPTION_PICTURE"));
				propOptVO.setReviewProductId(rs.getInt("REVIEW_PRODUCT_ID"));
				

				list.add(propOptVO); // Store the row in the list
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
	
	

	
	
	
	
}