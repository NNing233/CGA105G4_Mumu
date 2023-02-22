package com.accessfunc.model;

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





public class AccessFuncJDBCDAO implements AccessFuncDAO_interface{
	
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
			"INSERT INTO access_function (access_function_id, access_function_name) VALUES ( ?, ?)";
	
	private static final String SELECT_ALL_STMT =
			"SELECT access_function_id, access_function_name FROM access_function order by access_function_name";
	private static final String SELECT_ONE_STMT =
			"SELECT access_function_id, access_function_name FROM access_function where access_function_id =?";
	
	private static final String DELETE = 
			"DELETE FROM access_function WHERE access_function_id = ?";
	
	private static final String UPDATE = 
			"UPDATE access_function SET access_function_name =? where access_function_id =?";
	
	@Override
	public void insert(AccessFuncVO adminFuncVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, adminFuncVO.getAccessFunctionId());
			pstmt.setString(2, adminFuncVO.getAccessFunctionName());

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
	public void update(AccessFuncVO adminFuncVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, adminFuncVO.getAccessFunctionId());
			pstmt.setString(2, adminFuncVO.getAccessFunctionName());


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
	public void delete(Integer accessFunctionId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, accessFunctionId);

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
	public AccessFuncVO findByPrimaryKey(Integer accessFunctionId) {
		AccessFuncVO adminFuncVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_STMT);

			pstmt.setInt(1, accessFunctionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				adminFuncVO = new AccessFuncVO();
				adminFuncVO.setAccessFunctionId(rs.getInt("ACCESS_FUNCTION_ID"));
				adminFuncVO.setAccessFunctionName(rs.getString("ACCESS_FUNCTION_NAME"));
	
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
		return adminFuncVO;
		
	}

	@Override
	public List<AccessFuncVO> getAll() {
		List<AccessFuncVO> list = new ArrayList<AccessFuncVO>();
		AccessFuncVO adminFuncVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				adminFuncVO = new AccessFuncVO();
				adminFuncVO.setAccessFunctionId(rs.getInt("access_function_id"));
				adminFuncVO.setAccessFunctionName(rs.getString("access_function_name"));
				list.add(adminFuncVO);
		
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
	
	public static void main (String[] args) { //�L���X���
		
		AccessFuncJDBCDAO dao = new AccessFuncJDBCDAO();
		
		List<AccessFuncVO> list = dao.getAll();
		for (AccessFuncVO aEmp : list) {
			System.out.print(aEmp.getAccessFunctionId() + ",");
			System.out.print(aEmp.getAccessFunctionName());
			System.out.println();
			
		}
	}

}
