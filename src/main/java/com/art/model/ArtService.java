package com.art.model;

import java.sql.Timestamp;
import java.util.List;

public class ArtService {	
	private ArtDAO_interface dao;
	
	public ArtService() {
		dao = new ArtJDBCDAO();
	}
		
	
//	public ArtVO addArticle(Integer memberId,Integer articleTypeId,Integer articleStatus,
//				String articleTitle,String articleContent,Integer articleLikesAmount) {
//		
//		ArtVO artVO = new ArtVO();
//		artVO.setMemberId(memberId);
//		artVO.setArticleTypeId(articleTypeId);
//		artVO.setArticleStatus(articleStatus);
//		artVO.setArticleTitle(articleTitle);
//		artVO.setArticleContent(articleContent);
//		artVO.setArticleLikesAmount(articleLikesAmount);
//
//		dao.insert(artVO);
//		
//		return artVO; 
//	}
	
	public ArtVO addArticle(String articleTitle, String articleContent, Integer articleStatus, Integer articleTypeId,Integer memberId) {
		ArtVO artVO = new ArtVO();
		
		artVO.setArticleTitle(articleTitle);
		artVO.setArticleContent(articleContent);
		artVO.setArticleStatus(articleStatus);
		artVO.setArticleTypeId(articleTypeId);
		artVO.setMemberId(memberId);

		
		dao.insert(artVO);
		
		
		return artVO;
		
	}
	

	public void addArt(ArtVO artVO) {
		dao.insert(artVO);
	}
	
	
	public ArtVO updateArticle(Integer articleTypeId,Integer articleStatus,
			String articleTitle,String articleContent,Integer articleLikesAmount,Timestamp articlePostTime) {
		
		ArtVO artVO = new ArtVO();		
		artVO.setArticleTypeId(articleTypeId);
		artVO.setArticleStatus(articleStatus);
		artVO.setArticleTitle(articleTitle);
		artVO.setArticleContent(articleContent);
		artVO.setArticleLikesAmount(articleLikesAmount);
		artVO.setArticlePostTime(articlePostTime);

		dao.update(artVO);
		
		return artVO;
	}
	
	public ArtVO updateArticle(Integer articleId, String articleTitle, String articleContent, Integer articleStatus,
			Integer articleTypeId) {
		
		ArtVO artVO = new ArtVO();	
		artVO.setArticleId(articleId);		
		artVO.setArticleTitle(articleTitle);
		artVO.setArticleContent(articleContent);
		artVO.setArticleStatus(articleStatus);
		artVO.setArticleTypeId(articleTypeId);
		
		dao.update(artVO);
		
		return artVO;
	}

	
	public void updateArticle(ArtVO artVO) {
		dao.update(artVO);		
	}
	
	public void deleteArticle(Integer articleId) {
		dao.delete(articleId);		
	}
	
	public ArtVO getOneArticle(Integer articleId) {
		return dao.findByPrimaryKey(articleId);
	}
	
	public List<ArtVO> select(Integer articleTypeId) {
		return dao.select(articleTypeId);		
	}
	
	public List<ArtVO> getAll(){
		return dao.getAll();
	}

	public List<ArtVO> selectArticle(String keyword) {
		
		return dao.selectKeyword(keyword);
	}
	
	





	

	

	

}
