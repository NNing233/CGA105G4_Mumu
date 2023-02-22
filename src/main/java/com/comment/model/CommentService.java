package com.comment.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class CommentService {
	
	private CommentDAO_inerface dao;
	
	public CommentService() {
		dao = new CommentJDBCDAO();
	}
	
	public CommentVO addComment(Integer articleId, Integer memberId, String commentContent, Integer commentStatus, byte[] commentPicture) {
		
		CommentVO commentVO =new CommentVO();
		commentVO.setArticleId(articleId);
		commentVO.setMemberId(memberId);
		commentVO.setCommentContent(commentContent);
		commentVO.setCommentStatus(commentStatus);
		commentVO.setCommentPicture(commentPicture);
		dao.insert(commentVO);	
		
		return commentVO;
	}
	
	public void addComment(CommentVO commentVO) {
		dao.insert(commentVO);
	}
	
	public CommentVO updateComment(Integer commentId, String commentContent, Integer commentStatus, byte[]commentPicture) {
		
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentContent(commentContent);
		commentVO.setCommentStatus(commentStatus);
		commentVO.setCommentPicture(commentPicture);
		commentVO.setCommentId(commentId);
		dao.update(commentVO);
		
		return commentVO;
		
	}
	
	public void updateComment(CommentVO commentVO) {
		dao.update(commentVO);
	}
	
	public void deleteComment(Integer commentId) {
		dao.delete(commentId);
	}
	
	public CommentVO getOneComment(Integer commentId) {
		return dao.findByPrimaryKey(commentId);
	}
	
	public List<CommentVO> getAll(){
		return dao.getAll();
	}
	
	public List<CommentVO> getCommentByArtId(Integer articleId){
		
		return dao.getCommentByArtId(articleId);
	}
	
}
