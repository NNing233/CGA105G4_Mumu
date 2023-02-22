package com.sponrecord.model;


import java.util.List;

import com.prodopt.model.ProdOptVO;

public class SponRecordService {

	private SponRecordDAO_interface dao;

	public SponRecordService() {
		dao = new  SponRecordJDBCDAO();
	}
	
	public void updateSponRecordStatus(Integer proposalId, Integer sponRecordStatus) {
		dao.updateSponRecordStatus(proposalId,sponRecordStatus);
	}
	
	public void updateTheSponRecordStatus(Integer sponsorRecordId, Integer sponRecordStatus) {
		dao.updateTheSponRecordStatus(sponsorRecordId,sponRecordStatus);
	}
	
	public List< SponRecordVO> getTheOptionSponrecords(Integer productOptionId) {
		return dao.getTheOptionSponrecords(productOptionId);
	}
	
	
	
	public SponRecordVO getTheOptionSponrecord(Integer proposalOptionId) {
		return dao.getTheOptionSponrecord(proposalOptionId);
	}

	public  SponRecordVO addSponRecord(Integer memberId, SponRecordStatus sponsorRecordStatus, String sponsorRecordAddress, Integer sponsorRecordPayment, Integer sponsorRecordDelivery, Integer sponsorTotalAmount, Integer proposalOptionId, Integer proposalOptionQuantity, Integer proposalId) {

		SponRecordVO sponRecordVO = new SponRecordVO();
		
		sponRecordVO.setMemberId(memberId);
		sponRecordVO.setSponsorRecordStatus(sponsorRecordStatus);
		sponRecordVO.setSponsorRecordAddress(sponsorRecordAddress);
		sponRecordVO.setSponsorRecordPayment(sponsorRecordPayment);
		sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(sponsorRecordDelivery));
		sponRecordVO.setSponsorTotalAmount(sponsorTotalAmount);
//		後來新增
		sponRecordVO.setProposalOptionId(proposalOptionId);
		sponRecordVO.setProposalOptionQuantity(proposalOptionQuantity);
		sponRecordVO.setProposalId(proposalId);
		
		dao.insert(sponRecordVO);

		return sponRecordVO;
	}

	public SponRecordVO updateSponRecord(Integer sponsorRecordId, SponRecordStatus sponsorRecordStatus, String sponsorRecordAddress, Integer sponsorRecordPayment, Integer sponsorRecordDelivery, Integer sponsorTotalAmount) {

		SponRecordVO sponRecordVO = new SponRecordVO();

		sponRecordVO.setSponsorRecordId(sponsorRecordId);
		sponRecordVO.setSponsorRecordStatus(sponsorRecordStatus);
		sponRecordVO.setSponsorRecordAddress(sponsorRecordAddress);
		sponRecordVO.setSponsorRecordPayment(sponsorRecordPayment);
		sponRecordVO.setSponsorRecordDelivery(LogisticsStatus.from(sponsorRecordDelivery));
		sponRecordVO.setSponsorTotalAmount(sponsorTotalAmount);
		
		dao.update(sponRecordVO);

		return sponRecordVO;
	}

	public void deleteSponRecord(Integer sponsorRecordId) {
		dao.delete(sponsorRecordId);
	}

	public SponRecordVO getOneSponRecord(Integer sponsorRecordId) {
		return dao.findByPrimaryKey(sponsorRecordId);
	}

	public List<SponRecordVO> getAll() {
		return dao.getAll();
	}
	
}
