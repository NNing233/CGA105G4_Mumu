package com.propreport.model;

import java.sql.Timestamp;
import java.util.List;

public class PropReportService {
	private PropReportDAO_interface dao;

	public PropReportService() {
		dao = new PropReportJDBCDAO();
	}

	public PropReportVO addPropReportU(Integer memberId, Integer proposalId,
			Status reportStatus, String reportCause) {

		PropReportVO propReportVO = new PropReportVO();

		propReportVO.setMemberId(memberId);
		propReportVO.setProposalId(proposalId);
		propReportVO.setReportStatus(reportStatus);
		propReportVO.setReportCause(reportCause);
		dao.insertU(propReportVO);

		return propReportVO;
	}
	


	public PropReportVO updatePropReport(Integer proposalReportId,Integer memberId, Integer proposalId, Integer adminId,
			Timestamp reportTime, String reportResult, Status reportStatus, String reportCause) {

		PropReportVO propReportVO = new PropReportVO();
		
		propReportVO.setProposalReportId(proposalReportId);
		propReportVO.setMemberId(memberId);
		propReportVO.setProposalId(proposalId);
		propReportVO.setAdminId(adminId);
		propReportVO.setReportTime(reportTime);
		propReportVO.setReportResult(reportResult);
		propReportVO.setReportStatus(reportStatus);
		propReportVO.setReportCause(reportCause);	
		dao.update(propReportVO);

		return propReportVO;
	}

	public void deletePropReport(Integer proposalReportId) {
		dao.delete(proposalReportId);
	}

	public PropReportVO getOnePeport(Integer proposalReportId) {
		return dao.findByPrimaryKey(proposalReportId);
	}

	public List<PropReportVO> getAll() {
		return dao.getAll();
	}
	
	public PropReportVO updateStatus(Integer adminId,String reportResult,Status pass, Integer proposalReportId) {
		PropReportVO propReportVO = new PropReportVO();
		propReportVO.setAdminId(adminId);
		propReportVO.setReportResult(reportResult);
		propReportVO.setReportStatus(pass);
		propReportVO.setProposalReportId(proposalReportId);
		dao.updateStatus(propReportVO);
		
		return propReportVO;
	}
	
}
