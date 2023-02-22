package com.adminaccessmanage.model;

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




public class AdminAccessManageJDBCDAO implements AdminAccessManageDAO_interface {
	
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
			"INSERT INTO admin_access_management (admin_id, access_function_id) VALUES ( ?, ?)";
	
	private static final String SELECT_ALL_STMT =
			"SELECT admin_id, access_function_id FROM admin_access_management ORDER BY admin_id ";
	
	private static final String SELECT_ONE_STMT =
			"SELECT admin_id, access_function_id FROM admin_access_management WHERE admin_id = ? AND accessFunctionId=?";
	
	private static final String SELECT_ADMID =
			"SELECT admin_id, access_function_id FROM admin_access_management WHERE admin_id = ?";
	
	private static final String SELECT_AFID =
			"SELECT admin_id, access_function_id FROM admin_access_management WHERE accessFunctionId = ?";
	
	private static final String DELETE_ONE =
			"delete from admin_access_management WHERE (ADMIN_ID = ?) and (ACCESS_FUNCTION_ID = ?)";
	
	
	
	@Override
	public void insert(AdminAccessManageVO adminAccessManageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, adminAccessManageVO.getAdminId());
			pstmt.setInt(2, adminAccessManageVO.getAccessFunctionId());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<AdminAccessManageVO> getAll() {
		List<AdminAccessManageVO> list = new ArrayList<AdminAccessManageVO>();
		AdminAccessManageVO adminAccessManageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				adminAccessManageVO = new AdminAccessManageVO();
				adminAccessManageVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminAccessManageVO.setAccessFunctionId(rs.getInt("ACCESS_FUNCTION_ID"));
				list.add(adminAccessManageVO);
			}

				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
	public List<AdminAccessManageVO> findByAdminId(Integer adminId) {

		List<AdminAccessManageVO> list = new ArrayList<AdminAccessManageVO>();
		AdminAccessManageVO adminAccessManageVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ADMID);

			pstmt.setInt(1, adminId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adminAccessManageVO = new AdminAccessManageVO();
				adminAccessManageVO.setAdminId(rs.getInt("admin_id"));
				adminAccessManageVO.setAccessFunctionId(rs.getInt("access_function_id"));
				list.add(adminAccessManageVO);
			}
				
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
	public AdminAccessManageVO findByPrimaryKey(Integer adminId, Integer accessFunctionId) {
	
		AdminAccessManageVO adminAccessManageVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			
			pstmt.setInt(1, adminId);
			pstmt.setInt(2, accessFunctionId);
			
			pstmt.executeQuery();
			
			while(rs.next()) {
				adminAccessManageVO = new AdminAccessManageVO();
				adminAccessManageVO.setAdminId(rs.getInt("admin_id"));
				adminAccessManageVO.setAccessFunctionId(rs.getInt("access_function_id"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
		return adminAccessManageVO;
	}




	@Override
	public void deleteByPrimaryKey(Integer adminId, Integer accessFunctionId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONE);
			
			pstmt.setInt(1, adminId);
			pstmt.setInt(2, accessFunctionId);			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public List<AdminAccessManageVO> findByAccessFunctionId(Integer accessFunctionId) {
		List<AdminAccessManageVO> list = new ArrayList<AdminAccessManageVO>();
		AdminAccessManageVO adminAccessManageVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ADMID);

			pstmt.setInt(1, accessFunctionId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adminAccessManageVO = new AdminAccessManageVO();
				adminAccessManageVO.setAdminId(rs.getInt("admin_id"));
				adminAccessManageVO.setAccessFunctionId(rs.getInt("access_function_id"));
				list.add(adminAccessManageVO);
			}
				
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
