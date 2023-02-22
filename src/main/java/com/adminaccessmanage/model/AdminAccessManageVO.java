package com.adminaccessmanage.model;

import com.accessfunc.model.AccessFuncService;
import com.accessfunc.model.AccessFuncVO;
import com.admin.model.AdminService;
import com.admin.model.AdminVO;

public class AdminAccessManageVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer adminId;
	private Integer accessFunctionId;
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getAccessFunctionId() {
		return accessFunctionId;
	}
	public void setAccessFunctionId(Integer accessFunctionId) {
		this.accessFunctionId = accessFunctionId;
	}
	
	//for EL ${adminAccessManageVO.adminVO.adminId}
	public AdminVO getAdminVO() {
		AdminService adminSvc = new AdminService();
		AdminVO adminVO = adminSvc.getOneAdmin(adminId);
		return adminVO;
	}
	
	//for EL ${adminAccessManageVO.accessFuncVO.accessFunctionId}
	public AccessFuncVO getAccessFuncVO() {
		AccessFuncService accessFuncSvc = new AccessFuncService();
		AccessFuncVO accessFuncVO = accessFuncSvc.getOneAdminFunc(accessFunctionId);
		return accessFuncVO;
	}
	
}
