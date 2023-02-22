package com.sponrecord2.model;


import java.util.List;

public class SponRecordService {

	private SponRecordDAO_interface dao;

	public SponRecordService() {
		dao = new  SponRecordJDBCDAO();
	}

	public  SponRecordVO addSponRecord(Integer memberId, Integer sponsorRecordStatus, String sponsorRecordAddress, Integer sponsorRecordPayment, Integer sponsorRecordDelivery, Integer sponsorTotalAmount, Integer proposalOptionId, Integer proposalOptionQuantity, Integer proposalId) {

		SponRecordVO sponRecordVO = new SponRecordVO();
		
		sponRecordVO.setMemberId(memberId);
		sponRecordVO.setSponsorRecordStatus(sponsorRecordStatus);
		sponRecordVO.setSponsorRecordAddress(sponsorRecordAddress);
		sponRecordVO.setSponsorRecordPayment(sponsorRecordPayment);
		sponRecordVO.setSponsorRecordDelivery(sponsorRecordDelivery);
		sponRecordVO.setSponsorTotalAmount(sponsorTotalAmount);
//		後來新增
		sponRecordVO.setProposalOptionId(proposalOptionId);
		sponRecordVO.setProposalOptionQuantity(proposalOptionQuantity);
		sponRecordVO.setProposalId(proposalId);
		
		dao.insert(sponRecordVO);

		return sponRecordVO;
	}

	public SponRecordVO updateSponRecord(Integer sponsorRecordId, Integer sponsorRecordStatus, String sponsorRecordAddress, Integer sponsorRecordPayment, Integer sponsorRecordDelivery, Integer sponsorTotalAmount) {

		SponRecordVO sponRecordVO = new SponRecordVO();

		sponRecordVO.setSponsorRecordId(sponsorRecordId);
		sponRecordVO.setSponsorRecordStatus(sponsorRecordStatus);
		sponRecordVO.setSponsorRecordAddress(sponsorRecordAddress);
		sponRecordVO.setSponsorRecordPayment(sponsorRecordPayment);
		sponRecordVO.setSponsorRecordDelivery(sponsorRecordDelivery);
		sponRecordVO.setSponsorTotalAmount(sponsorTotalAmount);
		
		dao.update(sponRecordVO);

		return sponRecordVO;
	}
	
	public void modifySponsorRecordWithAddress(Integer sponsorRecordId, String sponsorRecordAddress) {
		
		dao.modifySponsorRecordWithAddress(sponsorRecordId, sponsorRecordAddress);
	}

	public void deleteSponRecord(Integer sponsorRecordId) {
		dao.delete(sponsorRecordId);
	}

	public SponRecordVO getOneSponRecord(Integer sponsorRecordId) {
		return dao.findByPrimaryKey(sponsorRecordId);
	}
	
	public Integer getSponsorNumByProposalId(Integer proposalId) {
		return dao.getSponsorNumByProposalId(proposalId);
	}

	public List<SponRecordVO> getAll() {
		return dao.getAll();
	}
	
	public List<SponRecordVO> getSponsorRecordByMember(int memberId) {
		return dao.getSponsorRecordByMember(memberId);
	}
	
	public void cancelSponsorRecord(int sponsorRecordId, int sponsorTotalAmount, int proposalId) {
		dao.cancelSponsorRecord(sponsorRecordId, sponsorTotalAmount, proposalId);
	}
	
}
