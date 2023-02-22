package com.adminaccessmanage.model;

import java.util.List;

public class AdminAccessManageService {
	
	private AdminAccessManageDAO_interface dao;
	
	public AdminAccessManageService(){
		
		dao = new AdminAccessManageJDBCDAO();
	}
	
	public AdminAccessManageVO addAdminAccessManage(Integer adminId, Integer accessFunctionId){
		
		AdminAccessManageVO adminAccessManageVO = new AdminAccessManageVO();

		adminAccessManageVO.setAdminId(adminId);
		adminAccessManageVO.setAccessFunctionId(accessFunctionId);

		dao.insert(adminAccessManageVO);
		
		return adminAccessManageVO;
		
	}
	
	public List<AdminAccessManageVO> getOneAdmin(Integer adminId){
		return dao.findByAdminId(adminId);
	}
	
	public List<AdminAccessManageVO> getOneAccess(Integer accessFunctionId){
		return dao.findByAccessFunctionId(accessFunctionId);
	}
	
	public AdminAccessManageVO getOne(Integer adminId,Integer accessFunctionId) {
		return dao.findByPrimaryKey(adminId, accessFunctionId);
	}
	
	public void deleteOne(Integer adminId,Integer accessFunctionId) {
		 dao.deleteByPrimaryKey(adminId, accessFunctionId);
	}
	
	public List<AdminAccessManageVO> getAll(){
		return dao.getAll();
	}
	
}
			

