package com.propcollection.model;

import java.util.*;

public interface PropCollectionDAO_interface {
	public void insert(PropCollectionVO propCollectionVO);

//	public void update(PropCollectionVO propCollectionVO);

	public void delete(Integer memberID, Integer proposalID);

	public PropCollectionVO findByPrimaryKey(Integer memberID, Integer proposalID);

	public List<PropCollectionVO> getAll();
	public List<PropCollectionVO> getPropCollectionByMem(Integer memberId);
}
