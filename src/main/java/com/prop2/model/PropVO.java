package com.prop2.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.proptype.model.PropTypeService;
import com.proptype.model.PropTypeVO;

public class PropVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer proposalId;
	private Integer memberId;
	private Integer proposalTypeId;
	private String proposalName;
	private Integer proposalApprovalStatus;
	private Integer proposalStatus;
	private Timestamp proposalStartDatetime;
	private Timestamp proposalEndDatetime;
	private Integer targetDonateMoney;
	private Integer accumulativeDonateMoney;
	private String proposalArticle;
	private byte[] proposalPicture;
	private String proposalRejectedReason;
	private String productLaunchRejectedReason;
	private Integer extendedTimes;
	private Integer articleId;
	private Date productProduceTime;
	private Date targetDeliveryTime;
	
	public PropTypeVO getPropTypeVO() {
		PropTypeService propTypeService = new PropTypeService();
		return propTypeService.getOnePropType(proposalTypeId);
	}
	
	public MemVO getMemVO() {
		MemService memSvc = new MemService();
		return memSvc.getOneMem(memberId);
	}
	
	
	public Date getProductProduceTime() {
		return productProduceTime;
	}

	public void setProductProduceTime(Date productProduceTime) {
		this.productProduceTime = productProduceTime;
	}

	public Date getTargetDeliveryTime() {
		return targetDeliveryTime;
	}

	public void setTargetDeliveryTime(Date targetDeliveryTime) {
		this.targetDeliveryTime = targetDeliveryTime;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getProposalName() {
		return proposalName;
	}

	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}

	public Integer getProposalApprovalStatus() {
		return proposalApprovalStatus;
	}

	public void setProposalApprovalStatus(Integer proposalApprovalStatus) {
		this.proposalApprovalStatus = proposalApprovalStatus;
	}

	public Integer getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(Integer proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Timestamp getProposalStartDatetime() {
		return proposalStartDatetime;
	}

	public void setProposalStartDatetime(Timestamp proposalStartDatetime) {
		this.proposalStartDatetime = proposalStartDatetime;
	}

	public Timestamp getProposalEndDatetime() {
		return proposalEndDatetime;
	}

	public void setProposalEndDatetime(Timestamp proposalEndDatetime) {
		this.proposalEndDatetime = proposalEndDatetime;
	}

	public Integer getTargetDonateMoney() {
		return targetDonateMoney;
	}

	public void setTargetDonateMoney(Integer targetDonateMoney) {
		this.targetDonateMoney = targetDonateMoney;
	}

	public Integer getAccumulativeDonateMoney() {
		return accumulativeDonateMoney;
	}

	public void setAccumulativeDonateMoney(Integer accumulativeDonateMoney) {
		this.accumulativeDonateMoney = accumulativeDonateMoney;
	}

	public String getProposalArticle() {
		return proposalArticle;
	}

	public void setProposalArticle(String proposalArticle) {
		this.proposalArticle = proposalArticle;
	}

	public byte[] getProposalPicture() {
		return proposalPicture;
	}

	public void setProposalPicture(byte[] proposalPicture) {
		this.proposalPicture = proposalPicture;
	}

	public String getProposalRejectedReason() {
		return proposalRejectedReason;
	}

	public void setProposalRejectedReason(String proposalRejectedReason) {
		this.proposalRejectedReason = proposalRejectedReason;
	}

	public String getProductLaunchRejectedReason() {
		return productLaunchRejectedReason;
	}

	public void setProductLaunchRejectedReason(String productLaunchRejectedReason) {
		this.productLaunchRejectedReason = productLaunchRejectedReason;
	}

	public Integer getExtendedTimes() {
		return extendedTimes;
	}

	public void setExtendedTimes(Integer extendedTimes) {
		this.extendedTimes = extendedTimes;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getProposalTypeId() {
		return proposalTypeId;
	}

	public void setProposalTypeId(Integer proposalTypeId) {
		this.proposalTypeId = proposalTypeId;
	}



}
