package com.proposalcollection.model;

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

	
	public void deleteEmp(Integer memberID, Integer proposalID) {
		dao.delete(memberID,proposalID);
	}

	public PropCollectionVO getOneEmp(Integer memberID, Integer proposalID) {
		return dao.findByPrimaryKey(memberID,proposalID);
	}

	public List<PropCollectionVO> getAll() {
		return dao.getAll();
	}
}
