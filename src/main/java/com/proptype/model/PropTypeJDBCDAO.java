package com.proptype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemJDBCDAO;
import com.mem.model.MemVO;


public class PropTypeJDBCDAO implements PropTypeDAO_interface{

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
			"INSERT INTO proposal_type (PROPOSAL_TYPE_NAME,PROPOSAL_TYPE_STATUS) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM proposal_type order by PROPOSAL_TYPE_ID";

	private static final String GET_ACTIVE_PROP_TYPE = 
			"SELECT * FROM proposal_type WHERE PROPOSAL_TYPE_STATUS=1 order by PROPOSAL_TYPE_ID";
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM proposal_type where PROPOSAL_TYPE_ID = ?";
	private static final String DELETE = 
		"DELETE FROM proposal_type where PROPOSAL_TYPE_ID = ?";
	private static final String UPDATE = 
		"UPDATE proposal_type set PROPOSAL_TYPE_NAME=? where PROPOSAL_TYPE_ID = ?";
	private static final String UPDATE_STATUS = 
			"UPDATE proposal_type set PROPOSAL_TYPE_STATUS=? where PROPOSAL_TYPE_ID = ?";
	
	@Override
	public void insert(PropTypeVO propTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, propTypeVO.getProposalTypeName());
			pstmt.setInt(2, propTypeVO.getProposalTypeStatus());

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
	public void update(PropTypeVO propTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, propTypeVO.getProposalTypeName());
			pstmt.setInt(2, propTypeVO.getProposalTypeId());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void updateStatus(Integer proposalTypeId, Integer proposalTypeStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setInt(1, proposalTypeStatus);
			pstmt.setInt(2, proposalTypeId);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
	public void delete(Integer proposalTypeId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, proposalTypeId);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public PropTypeVO findByPrimaryKey(Integer proposalTypeId) {
		PropTypeVO propTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, proposalTypeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				propTypeVO = new PropTypeVO();
				propTypeVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				propTypeVO.setProposalTypeName(rs.getString("PROPOSAL_TYPE_NAME"));
				propTypeVO.setProposalTypeStatus(rs.getInt("PROPOSAL_TYPE_STATUS"));

			}

			// Handle any driver errors
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
		return propTypeVO;
	}

	@Override
	public List<PropTypeVO> getAll() {
		List<PropTypeVO> list = new ArrayList<PropTypeVO>();
		PropTypeVO propTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				propTypeVO = new PropTypeVO();
				propTypeVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				propTypeVO.setProposalTypeName(rs.getString("PROPOSAL_TYPE_NAME"));
				propTypeVO.setProposalTypeStatus(rs.getInt("PROPOSAL_TYPE_STATUS"));
				
				list.add(propTypeVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<PropTypeVO> getActivePropType() {
		List<PropTypeVO> list = new ArrayList<PropTypeVO>();
		PropTypeVO propTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTIVE_PROP_TYPE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				propTypeVO = new PropTypeVO();
				propTypeVO.setProposalTypeId(rs.getInt("PROPOSAL_TYPE_ID"));
				propTypeVO.setProposalTypeName(rs.getString("PROPOSAL_TYPE_NAME"));
				propTypeVO.setProposalTypeStatus(rs.getInt("PROPOSAL_TYPE_STATUS"));
				
				list.add(propTypeVO); // Store the row in the list
			}
			
			// Handle any driver errors
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
	
//	public static void main(String[] args) {
//
//		PropTypeJDBCDAO dao = new PropTypeJDBCDAO();
//
//		// �s�W
//		PropTypeVO propV01 = new PropTypeVO();
//	
//		
//		propV01.setProposalTypeName("政治");
//		dao.insert(propV01);
//
//
//	}

}
