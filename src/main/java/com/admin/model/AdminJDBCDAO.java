package com.admin.model;

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

import com.adminaccessmanage.model.AdminAccessManageVO;



public class AdminJDBCDAO implements AdminDAO_interface {
	
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
			"INSERT INTO administrator (admin_account, admin_password, admin_account_status, admin_account_name) VALUES ( ?, ?, ?, ?)";
	private static final String SELECT_ALL_STMT =
			"SELECT admin_id, admin_account, admin_password, admin_account_status, admin_account_name FROM administrator ORDER BY admin_id";
	private static final String SELECT_ONE_STMT =
			"SELECT admin_id, admin_account, admin_password, admin_account_status, admin_account_name FROM administrator where admin_id = ?";
	private static final String DELETE = 
			"DELETE FROM administrator WHERE admin_id = ?";
	private static final String UPDATE = 
			"UPDATE administrator SET admin_account =?, admin_password =?, admin_account_status =?, admin_account_name =? where admin_id =?";
	private static final String SELECT_ONE_ACCOUNT =
			"SELECT admin_id, admin_account, admin_password, admin_account_status, admin_account_name FROM administrator where admin_account = ?";
	private static final String UPDATE_STATUS =
			"UPDATE administrator SET admin_account_status =? where admin_id =?";
	private static final String UPDATE_ACCOUNT_NAME =
			"UPDATE administrator SET ADMIN_ACCOUNT_NAME =? where admin_id =?";
	private static final String UPDATE_ACCOUNT_PASSWORD =
			"UPDATE administrator SET ADMIN_PASSWORD =? where admin_id =?";
	
	
	@Override
	public void insert(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getAdminAccount());
			pstmt.setString(2, adminVO.getAdminPassword());
			pstmt.setByte(3, adminVO.getAdminAccountStatus());
			pstmt.setString(4, adminVO.getAdminAccountName());

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
	public void update(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, adminVO.getAdminAccount());
			pstmt.setString(2, adminVO.getAdminPassword());
			pstmt.setByte(3, adminVO.getAdminAccountStatus());
			pstmt.setString(4, adminVO.getAdminAccountName());
			pstmt.setInt(5, adminVO.getAdminId());
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
	public void delete(Integer adminId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminId);

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
	public AdminVO findByPrimaryKey(Integer adminId) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_STMT);

			pstmt.setInt(1, adminId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminVO.setAdminAccount(rs.getString("ADMIN_ACCOUNT"));
				adminVO.setAdminPassword(rs.getString("ADMIN_PASSWORD"));
				adminVO.setAdminAccountStatus(rs.getByte("ADMIN_ACCOUNT_STATUS"));
				adminVO.setAdminAccountName(rs.getString("ADMIN_ACCOUNT_NAME"));
				String adminAccess = "";
				for(AdminAccessManageVO aamVO: adminVO.getAcessList()) {
					adminAccess += Integer.toString(aamVO.getAccessFunctionId())+" ";
				}
				adminVO.setAdminAcess(adminAccess);				

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
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));				
				adminVO.setAdminAccount(rs.getString("ADMIN_ACCOUNT"));
				adminVO.setAdminPassword(rs.getString("ADMIN_PASSWORD"));
				adminVO.setAdminAccountStatus(rs.getByte("ADMIN_ACCOUNT_STATUS"));
				adminVO.setAdminAccountName(rs.getString("ADMIN_ACCOUNT_NAME"));	
				String adminAccess = "";
				for(AdminAccessManageVO aamVO: adminVO.getAcessList()) {
					adminAccess += Integer.toString(aamVO.getAccessFunctionId())+" ";
				}
				adminVO.setAdminAcess(adminAccess);				
				
				list.add(adminVO); // Store the row in the list
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
	public AdminVO findByAccount(String adminAccount) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_ACCOUNT);

			pstmt.setString(1, adminAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminVO.setAdminAccount(rs.getString("ADMIN_ACCOUNT"));
				adminVO.setAdminPassword(rs.getString("ADMIN_PASSWORD"));
				adminVO.setAdminAccountStatus(rs.getByte("ADMIN_ACCOUNT_STATUS"));
				adminVO.setAdminAccountName(rs.getString("ADMIN_ACCOUNT_NAME"));
				String adminAccess = "";
				for(AdminAccessManageVO aamVO: adminVO.getAcessList()) {
					adminAccess += Integer.toString(aamVO.getAccessFunctionId())+" ";
				}
				adminVO.setAdminAcess(adminAccess);				

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
		return adminVO;
	}

	@Override
	public void updateStatus(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setByte(1, adminVO.getAdminAccountStatus());
			pstmt.setInt(2, adminVO.getAdminId());
			
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
	public void updateAdminAccountName(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ACCOUNT_NAME);
			
			pstmt.setString(1, adminVO.getAdminAccountName());
			pstmt.setInt(2, adminVO.getAdminId());
			
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
	public void updateAdminPassword(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ACCOUNT_PASSWORD);
			
			pstmt.setString(1, adminVO.getAdminPassword());
			pstmt.setInt(2, adminVO.getAdminId());
			
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

}
