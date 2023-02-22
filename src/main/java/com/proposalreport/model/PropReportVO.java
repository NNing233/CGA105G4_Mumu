package com.proposalreport.model;

import java.sql.Timestamp;

public class PropReportVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer proposalReportID;
	private Integer memberID;
	private Integer proposalID;
	private Integer adminID;
	private Timestamp reportTime;
	private String reportResult;
	private Byte reportStatus;
	private String reportCause;

	public Integer getProposalReportID() {
		return proposalReportID;
	}

	public void setProposalReportID(Integer proposalReportID) {
		this.proposalReportID = proposalReportID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getProposalID() {
		return proposalID;
	}

	public void setProposalID(Integer proposalID) {
		this.proposalID = proposalID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
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

	public Byte getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Byte reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportCause() {
		return reportCause;
	}

	public void setReportCause(String reportCause) {
		this.reportCause = reportCause;
	}

}
