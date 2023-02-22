package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
	
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(Integer memberId);
    public MemVO findByPrimaryKey(Integer memberId);
    public List<MemVO> getAll();
    public MemVO findByAccount(String memberAccount);
    
    public List<MemVO> findByGender(String memberGender);
    public void updateStatus(MemVO memVO);
	public void changePassword(MemVO memVO);
	

}

