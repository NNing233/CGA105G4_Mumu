package com.propcollection.model;

import java.util.List;

public class PropCollectionService {
	private PropCollectionDAO_interface dao;

	public PropCollectionService() {
		dao = new PropCollectionJDBCDAO();
	}

	public PropCollectionVO addPropCollection(Integer memberID, Integer proposalID) {
		
		PropCollectionVO propCollectionVO = new PropCollectionVO();

		propCollectionVO.setMemberID(memberID);
		propCollectionVO.setProposalID(proposalID);
		dao.insert(propCollectionVO);

		return propCollectionVO;
	}

	
	public void deletePropCollection(Integer memberID, Integer proposalID) {
		dao.delete(memberID,proposalID);
	}

	public PropCollectionVO getOnePropCollection(Integer memberID, Integer proposalID) {
		return dao.findByPrimaryKey(memberID,proposalID);
	}

	public List<PropCollectionVO> getAll() {
		return dao.getAll();
	}
	
	public List<PropCollectionVO> getPropCollectionByMem(Integer memberId) {
		return dao.getPropCollectionByMem(memberId);
	}
}
