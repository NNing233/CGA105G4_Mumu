package com.commentreport.model;

import java.sql.Date;

public class CommentReportVO implements java.io.Serializable {

	private Integer commentReportId;
	private Integer commentId;
	private Integer memberId;
	private Integer adminId;
	private Date reportTime;
	private String reportResult;
	private Integer reportStatus;
	private String reportCause;

	public Integer getCommentReportId() {
		return commentReportId;
	}

	public void setCommentReportId(Integer commentReportId) {
		this.commentReportId = commentReportId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportCause() {
		return reportCause;
	}

	public void setReportCause(String reportCause) {
		this.reportCause = reportCause;
	}

}
