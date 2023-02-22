package com.articlereport.model;

import java.util.List;

public class ArtReportService {

	private ArtReportDAO_interface dao;
	
	public ArtReportService() {
		dao = new ArtReportJDBCDAO();
	}
	
	public ArtReportVO addArticleReport(Integer memberId,Integer articleId,	Integer adminId,Integer reportStatus,String reportCause) {
		
		ArtReportVO artReportVO = new ArtReportVO();
		artReportVO.setMemberId(memberId);
		artReportVO.setArticleId(articleId);
		artReportVO.setAdminId(adminId);
		artReportVO.setReportStatus(reportStatus);
		artReportVO.setReportCause(reportCause);
		dao.insert(artReportVO);		
		
		return artReportVO;
	}
	
	public void addArticleReport(ArtReportVO artReportVO) {
		dao.insert(artReportVO);		
	}
	
	public ArtReportVO updateArticleReport(Integer adminId, Integer reportStatus, String reportResult) {
		
		ArtReportVO artReportVO = new ArtReportVO();
		artReportVO.setAdminId(adminId);
		artReportVO.setReportStatus(reportStatus);
		artReportVO.setReportCause(reportResult);
		dao.update(artReportVO);
		
		return artReportVO;
	}
	
	public void updateArticleReport(ArtReportVO artReportVO) {
		dao.update(artReportVO);
	}
	
	public void deleteArticleReport(Integer articleReportId) {
		dao.delete(articleReportId);
	}
	
	public ArtReportVO getOneArticleReport(Integer artReportId) {
		return dao.findByPrimaryKey(artReportId);
	}
	
	public List<ArtReportVO> getAll(){
		return dao.getAll();
	}

}
