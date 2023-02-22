package com.proptype.model;

import java.util.List;

public class PropTypeService {

	private PropTypeDAO_interface dao;

	public PropTypeService() {
		dao = new PropTypeJDBCDAO();
	}

	public PropTypeVO addPropType(String proposalTypeName) {

		PropTypeVO propTypeVO = new PropTypeVO();

		propTypeVO.setProposalTypeName(proposalTypeName);
		propTypeVO.setProposalTypeStatus(1);

		dao.insert(propTypeVO);

		return propTypeVO;
	}

	public PropTypeVO updatePropType(Integer proposalTypeId, String proposalTypeName) {

		PropTypeVO propTypeVO = new PropTypeVO();

		propTypeVO.setProposalTypeName(proposalTypeName);
		propTypeVO.setProposalTypeId(proposalTypeId);

		dao.update(propTypeVO);

		return propTypeVO;
	}

	public void updateStatus(Integer proposalTypeId, Integer proposalTypeStatus) {

		dao.updateStatus(proposalTypeId, proposalTypeStatus);

	}

	public void deletePropType(Integer proposalTypeId) {
		dao.delete(proposalTypeId);
	}

	public PropTypeVO getOnePropType(Integer proposalTypeId) {
		return dao.findByPrimaryKey(proposalTypeId);
	}

	public List<PropTypeVO> getAll() {
		return dao.getAll();
	}
	
	public List<PropTypeVO> getActivePropType() {
		return dao.getActivePropType();
	}

}
