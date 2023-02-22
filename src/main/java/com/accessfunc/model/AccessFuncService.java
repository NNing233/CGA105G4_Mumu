package com.accessfunc.model;

import java.util.List;

public class AccessFuncService {
	 
	private AccessFuncDAO_interface dao;
	
	public AccessFuncService() {
		dao = new AccessFuncJDBCDAO();
	}
	
	public AccessFuncVO addAdminFunc(String accessFunctionName) {
		
		AccessFuncVO adminFuncVO = new AccessFuncVO();
		
		adminFuncVO.setAccessFunctionName(accessFunctionName);
		
		return adminFuncVO;
	}
	
	public AccessFuncVO updateAdminFunc(Integer accessFunctionId, String accessFunctionName) {
		
		AccessFuncVO adminFuncVO = new AccessFuncVO();
		
		adminFuncVO.setAccessFunctionId(accessFunctionId);
		adminFuncVO.setAccessFunctionName(accessFunctionName);
		
		return adminFuncVO;
	}
	
	public void deleteAdminFunc(Integer accessFunctionId) {
		dao.delete(accessFunctionId);
	}
	
	public AccessFuncVO getOneAdminFunc(Integer accessFunctionId) {
		return dao.findByPrimaryKey(accessFunctionId);
	}
	
	public List<AccessFuncVO> getAll(){
		return dao.getAll();
	}
}
