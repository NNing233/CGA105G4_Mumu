package com.propprodreview.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.propopt.model.*;

public class PropProdReviewJDBCDAO implements PropProdReviewDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO proposal_product_review (PROPOSAL_ID, ADMIN_ID, REVIEW_PRODUCT_STATUS, APPLY_TIME, REVIEW_RESULT) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT REVIEW_PRODUCT_ID, PROPOSAL_ID, ADMIN_ID, REVIEW_PRODUCT_STATUS, APPLY_TIME, REVIEW_RESULT FROM proposal_product_review order by REVIEW_PRODUCT_ID";
	private static final String GET_ONE_STMT = "SELECT REVIEW_PRODUCT_ID, PROPOSAL_ID, ADMIN_ID, REVIEW_PRODUCT_STATUS, APPLY_TIME, REVIEW_RESULT FROM proposal_product_review where REVIEW_PRODUCT_ID = ?";
	private static final String GET_PropOptionId_ByPropReviewId_STMT = "SELECT PROPOSAL_OPTION_ID, PROPOSAL_ID, PROPOSAL_OPTION_NAME, PROPOSAL_OPTION_PRICE, PROPOSAL_OPTION_PICTURE, REVIEW_PRODUCT_ID FROM proposal_option where REVIEW_PRODUCT_ID = ? order by PROPOSAL_OPTION_ID";
	private static final String DELETE_ProposalOptions = "DELETE FROM proposal_option where REVIEW_PRODUCT_ID = ?";
	private static final String DELETE_ProductReviews = "DELETE FROM proposal_product_review where REVIEW_PRODUCT_ID = ?";
	private static final String UPDATE = "UPDATE proposal_product_review set PROPOSAL_ID=?, ADMIN_ID=?, REVIEW_PRODUCT_STATUS=?, APPLY_TIME=?, REVIEW_RESULT=? where REVIEW_PRODUCT_ID = ?";
	private static final String INSERT_NEW = "INSERT INTO proposal_product_review(PROPOSAL_ID, REVIEW_PRODUCT_STATUS) VALUES (?,?)";
	private static final String INSERT_ByPropsalId_STMT = "INSERT INTO proposal_product_review (PROPOSAL_ID, REVIEW_PRODUCT_STATUS) VALUES (?, ?)";
	
	@Override
	public void insert(PropProdReviewVO propRrodReviewVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, propRrodReviewVO.getProposalId());
			pstmt.setInt(2, propRrodReviewVO.getAdminId());
			pstmt.setInt(3, propRrodReviewVO.getReviewProductStatus().getValue());
			pstmt.setTimestamp(4, propRrodReviewVO.getApplyTime());
			pstmt.setString(5, propRrodReviewVO.getReviewResult());

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
	public void update(PropProdReviewVO propRrodReviewVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, propRrodReviewVO.getProposalId());
			pstmt.setInt(2, propRrodReviewVO.getAdminId());
			pstmt.setInt(3, propRrodReviewVO.getReviewProductStatus().getValue());
			pstmt.setTimestamp(4, propRrodReviewVO.getApplyTime());
			pstmt.setString(6, propRrodReviewVO.getReviewResult());
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
	public void delete(Integer reviewProductId) {
		int updateCount_ProposalOptions = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			con.setAutoCommit(false);

			
			pstmt = con.prepareStatement(DELETE_ProposalOptions);
			pstmt.setInt(1, reviewProductId);
			updateCount_ProposalOptions = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_ProductReviews);
			pstmt.setInt(1, reviewProductId);
			pstmt.executeUpdate();

			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R���W�[�ܰӫ��f�ֽs��" + reviewProductId + "��,�@�����׿ﶵ" + updateCount_ProposalOptions + "�ӦP�ɳQ�R��");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
				
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public PropProdReviewVO findByPrimaryKey(Integer reviewProductId) {

		PropProdReviewVO propRrodReviewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reviewProductId);
			rs = pstmt.executeQuery();
			while (rs.next())
				;
			{

				propRrodReviewVO = new PropProdReviewVO();
				propRrodReviewVO.setReviewProductId(rs.getInt("REVIEW_PRODUCT_ID"));
				propRrodReviewVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propRrodReviewVO.setAdminId(rs.getInt("ADMIN_ID"));
				propRrodReviewVO.setReviewProductStatus(Status.from(rs.getInt("REVIEW_PRODUCT_STATUS")));
				propRrodReviewVO.setApplyTime(rs.getTimestamp("APPLY_TIME"));
				propRrodReviewVO.setReviewResult(rs.getString("REVIEW_RESULT"));

			}

			// Handle any driver errors
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
		return propRrodReviewVO;
	}

	@Override
	public List<PropProdReviewVO> getAll() {
		List<PropProdReviewVO> list = new ArrayList<PropProdReviewVO>();
		PropProdReviewVO propRrodReviewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				propRrodReviewVO = new PropProdReviewVO();
				propRrodReviewVO.setReviewProductId(rs.getInt("REVIEW_PRODUCT_ID"));
				propRrodReviewVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propRrodReviewVO.setAdminId(rs.getInt("ADMIN_ID"));
				propRrodReviewVO.setReviewProductStatus(Status.from(rs.getInt("REVIEW_PRODUCT_STATUS")));
				propRrodReviewVO.setApplyTime(rs.getTimestamp("APPLY_TIME"));
				propRrodReviewVO.setReviewResult(rs.getString("REVIEW_RESULT"));

				list.add(propRrodReviewVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<PropOptVO> getPropOptionIdByReviewProductId(Integer reviewProductId) {
		Set<PropOptVO> set = new HashSet<PropOptVO>();
		PropOptVO propOptionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PropOptionId_ByPropReviewId_STMT);
			pstmt.setInt(1, reviewProductId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				propOptionVO = new PropOptVO();
				propOptionVO.setProposalOptionId(rs.getInt("PROPOSAL_OPTION_ID"));
				propOptionVO.setProposalId(rs.getInt("PROPOSAL_ID"));
				propOptionVO.setProposalOptionName(rs.getString("PROPOSAL_OPTION_NAME"));
				propOptionVO.setProposalOptionPrice(rs.getInt("PROPOSAL_OPTION_PRICE"));
				propOptionVO.setProposalOptionPicture(rs.getBytes("PROPOSAL_OPTION_PICTURE"));
				propOptionVO.setReviewProductId(rs.getInt("REVIEW_PRODUCT_ID"));
				set.add(propOptionVO); // Store the row in the vector
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	@Override
	public void insertWithPropOptionId(PropProdReviewVO propProdReviewVO, List<PropOptVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			
			con.setAutoCommit(false);

		
			String cols[] = { "PROPOSAL_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, propProdReviewVO.getProposalId());
			pstmt.setInt(2, propProdReviewVO.getAdminId());
			pstmt.setInt(3, propProdReviewVO.getReviewProductStatus().getValue());
			pstmt.setTimestamp(4, propProdReviewVO.getApplyTime());
			pstmt.setString(5, propProdReviewVO.getReviewResult());

			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=10;");
			stmt.executeUpdate("set auto_increment_increment=10;"); 
			pstmt.executeUpdate();
			
			String next_reviewProdId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_reviewProdId = rs.getString(1);
				System.out.println("" + next_reviewProdId + "");
			} else {
				System.out.println("");
			}
			rs.close();
		
//			PropOptJDBCDAO dao = new PropOptJDBCDAO();
//			System.out.println("list.size()-A=" + list.size());
//			for (PropOptVO aPropOption : list) {
//				aPropOption.setProposalId(new Integer(next_reviewProdId));
//				dao.insert2(aPropOption, con);    
//			}

		
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("��" + next_reviewProdId + "" + list.size() + "");

			// Handle any driver errors

		} catch (SQLException se) {
			if (con != null) {
				try {
					
					System.err.print("Transaction is being ");
					System.err.println("rolled back--dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public int insertNew(PropProdReviewVO propRrodReviewVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_NEW,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, propRrodReviewVO.getProposalId());
			
			pstmt.setInt(2, propRrodReviewVO.getReviewProductStatus().getValue());

			pstmt.executeUpdate();
			
			ResultSet resultSet = pstmt.getGeneratedKeys();
			
			
			resultSet.next();
			
			return resultSet.getInt(1);
			
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
	public void insertByPropsalId(Integer propsalId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PropProdReviewVO propRrodReviewVO = new PropProdReviewVO();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ByPropsalId_STMT);
			pstmt.setInt(1, propsalId);
			pstmt.setInt(2, 1);
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

}
