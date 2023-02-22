package com.propopt.model;

import java.util.List;

public class PropOptService {

	private PropOptDAO_interface dao;

	public PropOptService() {
		dao = new  PropOptJDBCDAO();
	}

	public  PropOptVO addPropOpt(Integer proposalId, String proposalOptionName, Integer proposalOptionPrice, byte[] proposalOptionPicture) {

		 PropOptVO propOptVO = new PropOptVO();

		propOptVO.setProposalId(proposalId);
		propOptVO.setProposalOptionName(proposalOptionName);
		propOptVO.setProposalOptionPrice(proposalOptionPrice);
		propOptVO.setProposalOptionPicture(proposalOptionPicture);
		dao.insert(propOptVO);

		return propOptVO;
	}

	public PropOptVO updatePropOpt(Integer proposalOptionId, String proposalOptionName, Integer proposalOptionPrice) {

		PropOptVO propOptVO = new PropOptVO();

		propOptVO.setProposalOptionId(proposalOptionId);
		propOptVO.setProposalOptionName(proposalOptionName);
		propOptVO.setProposalOptionPrice(proposalOptionPrice);
		
		dao.update(propOptVO);

		return propOptVO;
	}

	public void deletePropOpt(Integer proposalOptionId) {
		dao.delete(proposalOptionId);
	}

	public PropOptVO getOnePropOpt(Integer proposalOptionId) {
		return dao.findByPrimaryKey(proposalOptionId);
	}

	public List<PropOptVO> getAll() {
		return dao.getAll();
	}
	
	public List<PropOptVO> findByProposalId(Integer proposalId) {
		return ((PropOptJDBCDAO)(dao)).findByProposalId(proposalId);
	}
	
	public List<PropOptVO> getPropOptByProposalId(Integer proposalId) {
		return dao.getPropOptByProposalId(proposalId);
	}
	
	public PropOptVO updateReview(Integer reviewProductId, Integer proposalOptionId) {

		PropOptVO propOptVO = getOnePropOpt(proposalOptionId);
		
		
		
		dao.updateReview(reviewProductId, proposalOptionId);

		return propOptVO;
	}
	
	public List<PropOptVO> getAllOption(Integer proposalId) {
		return dao.getAllOption(proposalId);
	}
//	public static void main(String[] args) {
//		PropOptService a = new PropOptService();
//		
//		for(PropOptVO b:a.getAll()) {
//			
//				System.out.println(b.getProposalOptionPrice() + "��");  
//			
//		
//		}
//	}
}
