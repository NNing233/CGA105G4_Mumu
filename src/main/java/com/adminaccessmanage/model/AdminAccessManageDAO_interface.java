package com.adminaccessmanage.model;

import java.util.List;

public interface AdminAccessManageDAO_interface { 
    public void insert(AdminAccessManageVO adminAccessManageVO);//+
    public List<AdminAccessManageVO> findByAdminId(Integer adminId);//SELECT_ADMID+
    public List<AdminAccessManageVO> findByAccessFunctionId(Integer accessFunctionId); //SELECT_AFID  
    public AdminAccessManageVO findByPrimaryKey(Integer adminId,Integer accessFunctionId);//SELECT_ONE_STMT+
    public void deleteByPrimaryKey(Integer adminId,Integer accessFunctionId);//DELETE_ONE+
    public List<AdminAccessManageVO> getAll();//SELECT_ALL_STMT+
   
}