package com.prop.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public interface PropDAO_interface {
	public void insert(PropVO propVO);

	public void update(PropVO propVO);

	public void delete(Integer proposalId);

	public PropVO findByPrimaryKey(Integer proposalId);

	public List<PropVO> getAll();

	public void updateStatus(PropVO propVO);

	public void extend(PropVO propVO);

	public void setExtend(Integer proposalId, Integer extendedDays);

	public void drop(Integer proposalId);

	public void propType2wish(Integer proposalId);

	public void updatePropsalStatus(Integer proposalId, Integer proposaStatus);
	
	public void updateFundingProposalWithoutProposalStatus(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime);

	public void updateFundingProposal(Integer proposalId, String proposalName, Integer memberId, Integer prodTypeId, Integer selectedProposalStatus,String proposalArticle, byte[] uploadProposalImg, Integer accumulativeDonateMoney, Integer targetDonateMoney, Integer extendedDays, Integer extendedTimes, Date proposalStartDatetime, Date proposalEndDatetime, Date productProduceTime, Date targetDeliveryTime);
	
	public List<PropVO> getTheProposal(Integer memberId);
}
