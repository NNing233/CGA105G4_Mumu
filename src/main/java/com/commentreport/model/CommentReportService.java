package com.commentreport.model;


import java.util.List;

public class CommentReportService {
	
	private CommentReportDAO_interface dao;
	
	public CommentReportService() {
		dao = new CommentReportJDBCDAO();				
	}
	
	public CommentReportVO addCommentReport(Integer commentId,Integer memberId,Integer adminId,Integer reportStatus,String reportCause) {
		
		CommentReportVO commentReportVO = new CommentReportVO();
		commentReportVO.setCommentId(commentId);
		commentReportVO.setMemberId(memberId);
		commentReportVO.setAdminId(adminId);
		commentReportVO.setReportStatus(reportStatus);
		commentReportVO.setReportCause(reportCause);
		dao.insert(commentReportVO);
		
		return commentReportVO;	
	}
	
	public void addCommentReport(CommentReportVO commentReportVO) {
		dao.insert(commentReportVO);
	}
	
	public CommentReportVO updateCommentReport(Integer adminId,Integer reportStatus, String reportResult) {
		
		CommentReportVO commentReportVO = new CommentReportVO();
		commentReportVO.setAdminId(adminId);
		commentReportVO.setReportStatus(reportStatus);
		commentReportVO.setReportResult(reportResult);
		dao.update(commentReportVO);
		
		return commentReportVO;	
	}
	
	public void updateCommentReport(CommentReportVO commentReportVO) {
		dao.update(commentReportVO);
	}
	
	public void deleteCOmmentReport(Integer commentReportId) {
		dao.delete(commentReportId);
	}
	
	public CommentReportVO getOneCommentReport(Integer commentReportId) {
		return dao.findByPrimaryKey(commentReportId);
	}
	
	public List<CommentReportVO> getAll(){
		return dao.getAll();
	}

}
