package com.propreport.model;

import java.sql.Timestamp;

public class PropReportVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer proposalReportId;
	private Integer memberId;
	private Integer proposalId;
	private Integer adminId;
	private Timestamp reportTime;
	private String reportResult;
	private Status reportStatus;
	private String reportCause;

	public Integer getProposalReportId() {
		return proposalReportId;
	}

	public void setProposalReportId(Integer proposalReportId) {
		this.proposalReportId = proposalReportId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public Status getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Status status) {
		this.reportStatus = status;
	}

	public String getReportCause() {
		return reportCause;
	}

	public void setReportCause(String reportCause) {
		this.reportCause = reportCause;
	}

}
