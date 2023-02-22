package com.proposalreport.model;

import java.sql.Timestamp;
import java.util.List;

public class PropReportService {
	private PropReportDAO_interface dao;

	public PropReportService() {
		dao = new PropReportJDBCDAO();
	}

	public PropReportVO addPropReport(Integer memberID, Integer proposalID, Integer adminID,
			Timestamp reportTime, String reportResult, Byte reportStatus, String reportCause) {

		PropReportVO propReportVO = new PropReportVO();

		propReportVO.setMemberID(memberID);
		propReportVO.setProposalID(proposalID);
		propReportVO.setAdminID(adminID);
		propReportVO.setReportTime(reportTime);
		propReportVO.setReportResult(reportResult);
		propReportVO.setReportStatus(reportStatus);
		propReportVO.setReportCause(reportCause);
		dao.insert(propReportVO);

		return propReportVO;
	}

	public PropReportVO updatePropReport(Integer proposalReportID,Integer memberID, Integer proposalID, Integer adminID,
			Timestamp reportTime, String reportResult, Byte reportStatus, String reportCause) {

		PropReportVO propReportVO = new PropReportVO();
		
		propReportVO.setProposalReportID(proposalReportID);
		propReportVO.setMemberID(memberID);
		propReportVO.setProposalID(proposalID);
		propReportVO.setAdminID(adminID);
		propReportVO.setReportTime(reportTime);
		propReportVO.setReportResult(reportResult);
		propReportVO.setReportStatus(reportStatus);
		propReportVO.setReportCause(reportCause);	
		dao.update(propReportVO);

		return propReportVO;
	}

	public void deletePropReport(Integer proposalReportID) {
		dao.delete(proposalReportID);
	}

	public PropReportVO getOneEmp(Integer proposalReportID) {
		return dao.findByPrimaryKey(proposalReportID);
	}

	public List<PropReportVO> getAll() {
		return dao.getAll();
	}
}
