package com.admin.model;

import java.util.List;

public interface AdminDAO_interface { 
    public void insert(AdminVO adminVO);
    public void update(AdminVO adminVO);
    public void delete(Integer adminVO); 
    public AdminVO findByPrimaryKey(Integer adminId);
    public List<AdminVO> getAll();
    public AdminVO findByAccount(String adminAccount);
    public void updateStatus(AdminVO adminVO);
    public void updateAdminAccountName(AdminVO adminVO);
    public void updateAdminPassword(AdminVO adminVO);
    
    
}