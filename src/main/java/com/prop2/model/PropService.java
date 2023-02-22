package com.prop2.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.propopt.model.PropOptService;
import com.propopt.model.PropOptVO;

public class PropService {

	private PropDAO_interface dao;

	public PropService() {
		dao = new PropJDBCDAO();
	}

	public PropVO addProp(Integer memberId, String proposalName, Integer proposalApprovalStatus, Integer proposalStatus,
			Timestamp proposalStartDatetime, Timestamp proposalEndDatetime, Integer targetDonateMoney,
			Integer accumulativeDonateMoney, String proposalArticle, Integer extendedTimes, Integer proposalTypeId, Date productProduceTime, Date targetDeliveryTime) {

		PropVO propVO = new PropVO();

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
		dao.insert(propVO);

		return propVO;
	}

	public PropVO updatePropOpt(Integer proposalId, Integer memberId, String proposalName, Integer proposalApprovalStatus, Integer proposalStatus,
			Timestamp proposalStartDatetime, Timestamp proposalEndDatetime, Integer targetDonateMoney,
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
	
	public List<PropVO> getProposalFromType(Integer proposalTypeId) {
		return dao.getProposalFromType(proposalTypeId);
	}
	
	public List<PropVO> getSuccessProp() {
		return dao.getSuccessProp();
	}
	
	public List<PropVO> getCompositeQuery(Integer proposalTypeId,Integer min,Integer max) {
		return dao.getCompositeQuery(proposalTypeId, min, max);
	}
	
	
	
	public PropVO getPopularProp() {
		return dao.getPopularProp();
	}
	
	

	public Integer getTotalProposalNum() {
		return dao.getTotalProposalNum();
	}
	
	public static void main(String[] args) {
		PropOptService a = new PropOptService();
		
		for(PropOptVO b:a.getAll()) {
			
				System.out.println(b.getProposalOptionPrice() + "��");  
			
		
		}
	}
}
