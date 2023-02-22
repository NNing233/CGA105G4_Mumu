package com.prop.model;

import java.sql.Date;

import com.mem.model.*;
import com.prop.model.*;
import com.propopt.model.PropOptVO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

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
	private Status proposalApprovalStatus;
	private ProposalStatus proposalStatus;
	private Date proposalStartDatetime;
	private Date proposalEndDatetime;
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
	private Integer extendedDays;

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

	public Status getProposalApprovalStatus() {
		return proposalApprovalStatus;
	}

	public void setProposalApprovalStatus(Status proposalApprovalStatus) {
		this.proposalApprovalStatus = proposalApprovalStatus;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Date getProposalStartDatetime() {
		return proposalStartDatetime;
	}

	public void setProposalStartDatetime(Date proposalStartDatetime) {
		this.proposalStartDatetime = proposalStartDatetime;
	}

	public Date getProposalEndDatetime() {
		return proposalEndDatetime;
	}

	public void setProposalEndDatetime(Date proposalEndDatetime) {
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

	public Integer getExtendedDays() {
		return extendedDays;
	}

	public void setExtendedDays(Integer extendedDays) {
		this.extendedDays = extendedDays;
	}

	public PropOptVO getPropOptVO() {

		com.propopt.model.PropOptService propOptSvc = new com.propopt.model.PropOptService();
		com.propopt.model.PropOptVO propOptVO = propOptSvc.getOnePropOpt(proposalId);

		return propOptVO;

	}

	public List<com.propopt.model.PropOptVO> getAllPropOptVO() {
		com.propopt.model.PropOptService propOptSvc = new com.propopt.model.PropOptService();
		List<com.propopt.model.PropOptVO> list = propOptSvc.getAllOption(proposalId);
		return list;
	}

	public int daysDifference(Date proposalStartDatetime, Date proposalEndDatetime) {

		long diff =  proposalEndDatetime.getTime() - proposalStartDatetime.getTime();

		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		return diffDays;
	}

	public String TampStamp2Date(Timestamp ts) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String transDate = df.format(ts);
		return transDate;
	}
	
}
