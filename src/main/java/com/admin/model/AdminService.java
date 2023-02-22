package com.admin.model;

import java.util.List;

public class AdminService {
	private AdminDAO_interface dao;
	
	public AdminService() {
		dao = new AdminJDBCDAO();
	}
	
	public void addAdmin( String adminAccount, String adminPassword, byte adminAccountStatus, String adminAccountName ) {
		
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminAccount(adminAccount);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminAccountStatus(adminAccountStatus);
		adminVO.setAdminAccountName(adminAccountName);
		dao.insert(adminVO);
		
	}
	
	public AdminVO updateAdmin(Integer adminId, String adminAccount, String adminPassword, byte adminAccountStatus, String adminAccountName) {
		
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminId(adminId);
		adminVO.setAdminAccount(adminAccount);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminAccountStatus(adminAccountStatus);
		adminVO.setAdminAccountName(adminAccountName);
		dao.update(adminVO);
		return adminVO;
	}
	
	public void deleteAdmin(Integer adminId) {
		dao.delete(adminId);
	}
	
	public AdminVO getOneAdmin(Integer adminId) {
		return dao.findByPrimaryKey(adminId);
	}
	
	public List<AdminVO> getAll(){
		return dao.getAll();
	}

	public AdminVO findByAccount(String adminAccount) {
		return dao.findByAccount(adminAccount);
	}
	
	public void updateAdminStatus(Integer adminId, byte adminAccountStatus) {
		AdminVO adminVO = new AdminVO();		
		adminVO.setAdminId(adminId);
		adminVO.setAdminAccountStatus(adminAccountStatus);
		dao.updateStatus(adminVO);
	}
	
	public AdminVO updateAdminPassword(Integer adminId, String adminPassword) {
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId(adminId);
		adminVO.setAdminPassword(adminPassword);
		dao.updateAdminPassword(adminVO);
		adminVO = dao.findByPrimaryKey(adminId);
		return adminVO;
		
	}

	 public void updateAdminAccountName(Integer adminId, String adminAccountName) {
		 AdminVO adminVO = new AdminVO();
		 adminVO.setAdminId(adminId);
		 adminVO.setAdminAccountName(adminAccountName);
		 dao.updateAdminAccountName(adminVO);
	 }
	
}
