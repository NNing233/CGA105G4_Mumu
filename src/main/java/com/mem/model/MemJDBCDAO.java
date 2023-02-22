package com.mem.model;


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


public class MemJDBCDAO implements MemDAO_interface {
	
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
			  "INSERT INTO member (member_account, member_password, member_nickname, member_status, id_number, member_name, gender, date_of_birth, member_email, member_phone, member_address, profile_photo, bank_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String SELECT_ALL_STMT = 
			  "SELECT member_id, member_account, member_password, member_nickname, sign_up_datetime, member_status, id_number, member_name, gender, date_of_birth, member_email, member_phone, member_address, profile_photo, bank_account FROM member order by member_id ";
	
	private static final String SELECT_ONE_STMT = 
			  "SELECT member_id, member_account, member_password, member_nickname, sign_up_datetime, member_status, id_number, member_name, gender, date_of_birth, member_email, member_phone, member_address, profile_photo, bank_account FROM member where member_id =?";
	
	private static final String DELETE = 
			  "DELETE FROM member WHERE member_id = ?";
	
	private static final String UPDATE = 
			  "UPDATE member SET member_account=?,  member_nickname=? , id_number =?,member_name=?, gender=?, date_of_birth=?, member_email=?, member_phone=?, member_address=?, profile_photo=?, bank_account=? where member_id =? ";
	
	private static final String SELECT_ONE_ACCOUNT =
			"SELECT * FROM member WHERE member_account =?";
	
	private static final String UPDATE_STATUS =
			"UPDATE member SET member_status =? where member_id =?";
	
	private static final String UPDATE_PASSWORD =
			"UPDATE member SET member_password = ? WHERE member_id = ?";

	
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMemberAccount());
			pstmt.setString(2, memVO.getMemberPassword());
			pstmt.setString(3, memVO.getMemberNickname());
			pstmt.setInt(4, memVO.getMemberStatus());
			pstmt.setString(5, memVO.getIdNumber());
			pstmt.setString(6, memVO.getMemberName());
			pstmt.setString(7, memVO.getMemberGender());
			pstmt.setDate(8, memVO.getDateOfBirth());
			pstmt.setString(9, memVO.getMemberEmail());
			pstmt.setString(10, memVO.getMemberPhone());
			pstmt.setString(11, memVO.getMemberAddress());
			pstmt.setBytes(12, memVO.getProfilePhoto());
			pstmt.setString(13, memVO.getBankAccount());
			
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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getMemberAccount());
			pstmt.setString(2, memVO.getMemberNickname());
			pstmt.setString(3, memVO.getIdNumber());
			pstmt.setString(4, memVO.getMemberName());
			pstmt.setString(5, memVO.getMemberGender());
			pstmt.setDate(6, memVO.getDateOfBirth());
			pstmt.setString(7, memVO.getMemberEmail());
			pstmt.setString(8, memVO.getMemberPhone());
			pstmt.setString(9, memVO.getMemberAddress());
			pstmt.setBytes(10, memVO.getProfilePhoto());
			pstmt.setString(11, memVO.getBankAccount());
			pstmt.setInt(12, memVO.getMemberId());
			
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
	public void delete(Integer memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);

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
	public MemVO findByPrimaryKey(Integer memberId) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_STMT);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemberId(rs.getInt("MEMBER_ID"));
				memVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memVO.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				memVO.setSignUpDatetime(rs.getTimestamp("SIGN_UP_DATETIME"));
				memVO.setMemberStatus(rs.getInt("MEMBER_STATUS"));
				memVO.setIdNumber(rs.getString("ID_NUMBER"));
				memVO.setMemberName(rs.getString("MEMBER_NAME"));
				memVO.setMemberGender(rs.getString("GENDER"));
				memVO.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				memVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memVO.setProfilePhoto(rs.getBytes("profile_photo"));
				memVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
			
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMemberId(rs.getInt("MEMBER_ID"));
				memVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memVO.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				memVO.setSignUpDatetime(rs.getTimestamp("SIGN_UP_DATETIME"));
				memVO.setMemberStatus(rs.getInt("MEMBER_STATUS"));
				memVO.setIdNumber(rs.getString("ID_NUMBER"));
				memVO.setMemberName(rs.getString("MEMBER_NAME"));
				memVO.setMemberGender(rs.getString("GENDER"));
				memVO.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				memVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memVO.setProfilePhoto(rs.getBytes("Profile_photo"));
				memVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
				list.add(memVO); // Store the row in the list
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
	public MemVO findByAccount(String memberAccount) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_ACCOUNT);

			pstmt.setString(1, memberAccount);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {				
				memVO = new MemVO();
				memVO.setMemberId(rs.getInt("MEMBER_ID"));
				memVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memVO.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				memVO.setSignUpDatetime(rs.getTimestamp("SIGN_UP_DATETIME"));
				memVO.setMemberStatus(rs.getInt("MEMBER_STATUS"));
				memVO.setIdNumber(rs.getString("ID_NUMBER"));
				memVO.setMemberName(rs.getString("MEMBER_NAME"));
				memVO.setMemberGender(rs.getString("GENDER"));
				memVO.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				memVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memVO.setProfilePhoto(rs.getBytes("profile_photo"));
				memVO.setBankAccount(rs.getString("BANK_ACCOUNT"));								
			}
			
		}  catch (SQLException se) {
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
		
		return memVO;	
	}


	@Override
	public void updateStatus(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setInt(1, memVO.getMemberStatus());
			pstmt.setInt(2, memVO.getMemberId());
			
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
	public void changePassword(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASSWORD);
			
			pstmt.setString(1, memVO.getMemberPassword());
			pstmt.setInt(2, memVO.getMemberId());
			
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
	
	

	
}

