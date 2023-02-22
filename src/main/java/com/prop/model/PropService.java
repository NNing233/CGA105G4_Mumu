package com.prop.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.propopt.model.PropOptService;
import com.propopt.model.PropOptVO;
import com.prop.model.*;

public class PropService {

	private PropDAO_interface dao;

	public PropService() {
		dao = new PropJDBCDAO();
	}

	public PropVO addProp(Integer memberId, Integer proposalTypeId, String proposalName, Status proposalApprovalStatus, ProposalStatus proposalStatus,
			Date proposalStartDatetime, Date proposalEndDatetime, Integer targetDonateMoney,
			Integer accumulativeDonateMoney, String proposalArticle, byte[] proposalPicture, Integer extendedTimes, Date productProduceTime, Date targetDeliveryTime) {

		PropVO propVO = new PropVO();

		propVO.setMemberId(memberId);
		propVO.setProposalTypeId(proposalTypeId);
		propVO.setProposalName(proposalName);
		propVO.setProposalApprovalStatus(proposalApprovalStatus);
		propVO.setProposalStatus(proposalStatus);
		propVO.setProposalStartDatetime(proposalStartDatetime);
		propVO.setProposalEndDatetime(proposalEndDatetime);
		propVO.setTargetDonateMoney(targetDonateMoney);
		propVO.setAccumulativeDonateMoney(accumulativeDonateMoney);
		propVO.setProposalArticle(proposalArticle);
		propVO.setProposalPicture(proposalPicture);
		propVO.setExtendedTimes(extendedTimes);
		propVO.setProductProduceTime(productProduceTime);
		propVO.setTargetDeliveryTime(targetDeliveryTime);
		dao.insert(propVO);

		return propVO;
	}

	public PropVO updatePropOpt(Integer proposalId, Integer memberId, String proposalName, Status proposalApprovalStatus, ProposalStatus proposalStatus,
			Date proposalStartDatetime, Date proposalEndDatetime, Integer targetDonateMoney,
			Integer accumulativeDonateMoney, String proposalArticle, Integer extendedTimes, Integer proposalTypeId, Date productProduceTime, Date targetDeliveryTime) {

		PropVO propVO = new PropVO();

		propVO.setProposalId(proposalId);
		propVO.setMemberId(memberId);
		propVO.setProposalName(proposalName);
		propVO.setProposalApprovalStatus(proposalApprovalStatus);
		propVO.setProposalStatus(proposalStatus);
		propVO.setProposalStartDatetime(proposalStartDatetime);
		propVO.setProposalEndDatetime(proposalEndDatetime);
		propVO.setTargetDonateMoney(targetDonateMoney);
		propVO.setAccumulativeDonateMoney(accumulativeDonateMoney);
		propVO.setProposalArticle(proposalArticle);
		propVO.setExtendedTimes(extendedTimes);
		propVO.setProposalTypeId(proposalTypeId);
		propVO.setProductProduceTime(productProduceTime);
		propVO.setTargetDeliveryTime(targetDeliveryTime);

		dao.update(propVO);

		return propVO;
	}

	public void deleteProp(Integer proposalId) {
		dao.delete(proposalId);
	}

	public PropVO getOneProp(Integer proposalId) {
		return dao.findByPrimaryKey(proposalId);
	}

	public List<PropVO> getAll() {
		return dao.getAll();

	}
	
	public List<PropVO> getTheProposal(Integer memberId) {
		return dao.getTheProposal(memberId);
	}

	public PropVO updateStatus(Integer proposalId, Status proposalApprovalStatus) {
		PropVO propVO = new PropVO();
		propVO.setProposalId(proposalId);
		propVO.setProposalApprovalStatus(proposalApprovalStatus);
		dao.updateStatus(propVO);
		return propVO;
		
	}
	public void updatePropsalStatus(Integer proposalId, Integer proposaStatus) {
		dao.updatePropsalStatus(proposalId, proposaStatus);
	}
	
	public void propType2wish(Integer proposalId) {
		dao.propType2wish(proposalId);
	}
	
	public void dropProp(Integer proposalId) {
		dao.drop(proposalId);
	}
	
	public void setExtendedDays(Integer proposalId, Integer extendedDays) {
		 dao.setExtend(proposalId, extendedDays);
	}
	
	public void extendedDays(Integer proposalId) {
		 PropVO propVO = dao.findByPrimaryKey(proposalId);
		 dao.extend(propVO);
	}
	
	public void updateFundingProposal(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId,Integer selectedProposalStatus,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime) {
		dao.updateFundingProposal(proposalId, proposalName, memberId, prodTypeId, selectedProposalStatus, proposalArticle, uploadProposalImg, accumulativeDonateMoney, targetDonateMoney, extendedDays, extendedTimes,proposalStartDatetime, proposalEndDatetime, productProduceTime, targetDeliveryTime) ;
	}
	public void updateFundingProposalWithoutProposalStatus(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime) {
		dao.updateFundingProposalWithoutProposalStatus(proposalId, proposalName, memberId, prodTypeId, proposalArticle, uploadProposalImg, accumulativeDonateMoney, targetDonateMoney, extendedDays, extendedTimes,proposalStartDatetime, proposalEndDatetime, productProduceTime, targetDeliveryTime) ;
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
	//}
}
