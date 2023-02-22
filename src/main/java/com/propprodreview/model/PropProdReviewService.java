package com.propprodreview.model;

import java.sql.Timestamp;
import java.util.List;



public class PropProdReviewService {
	private PropProdReviewDAO_interface dao;

	public PropProdReviewService() {
		dao = new PropProdReviewJDBCDAO();
	}

	public PropProdReviewVO addNew(Integer proposalId,
			Status propProdReviewStatus) {

		PropProdReviewVO propProdReviewVO = new PropProdReviewVO();

		
		propProdReviewVO.setProposalId(proposalId);
		propProdReviewVO.setReviewProductStatus(propProdReviewStatus);
		
		int generatedId = dao.insertNew(propProdReviewVO);
		propProdReviewVO.setReviewProductId(generatedId);
		

		return propProdReviewVO;
	}
	


	public void deletePropRrodReview(Integer reviewProductId) {
		dao.delete(reviewProductId);
	}

	public PropProdReviewVO getOnePropProd(Integer reviewProductId) {
		return dao.findByPrimaryKey(reviewProductId);
	}

	public List<PropProdReviewVO> getAll() {
		return dao.getAll();
	}
	

	
	public void setPropProdReviewByProposalId(Integer proposalId) {
		dao.insertByPropsalId(proposalId);
	}
	
}
