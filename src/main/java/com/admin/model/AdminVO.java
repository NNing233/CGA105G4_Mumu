package com.admin.model;

import java.util.List;

import com.adminaccessmanage.model.AdminAccessManageService;
import com.adminaccessmanage.model.AdminAccessManageVO;

public class AdminVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer adminId;
	private String adminAccount;
	private String adminPassword;
	private byte adminAccountStatus;
	private String adminAccountName;
	
	private String adminAcess;//裝所屬權限字串
	
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public byte getAdminAccountStatus() {
		return adminAccountStatus;
	}
	public void setAdminAccountStatus(byte adminAccountStatus) {
		this.adminAccountStatus = adminAccountStatus;
	}
	public String getAdminAccountName() {
		return adminAccountName;
	}
	public void setAdminAccountName(String adminAccountName) {
		this.adminAccountName = adminAccountName;
	}
	
	
	//以下都是未確定
	public List<AdminAccessManageVO> getAcessList(){
		AdminAccessManageService aamSvc = new AdminAccessManageService();
		List<AdminAccessManageVO>list = aamSvc.getOneAdmin(this.adminId);
		return list;
	}
	public String getAdminAcess() {
		return adminAcess;
	}
	public void setAdminAcess(String adminAcess) {
		this.adminAcess = adminAcess;
	}

}
