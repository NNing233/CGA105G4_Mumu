package com.proptype.model;

import java.util.List;


public interface PropTypeDAO_interface {
	public void insert(PropTypeVO propTypeVO);
    public void update(PropTypeVO propTypeVO);
    public void delete(Integer proposalTypeId);
    public PropTypeVO findByPrimaryKey(Integer proposalTypeId);
    public List<PropTypeVO> getAll();
    
    public List<PropTypeVO> getActivePropType();
    public void updateStatus(Integer proposalTypeId, Integer proposalTypeStatus);
}
