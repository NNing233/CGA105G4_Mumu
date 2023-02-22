package com.artliked.model;

import java.util.List;

public class ArtLikedService {
	
	private ArtLikedDAO_interface dao;
	
	public ArtLikedService() {
		dao = new ArtLikedJDBCDAO();
	}
	
	public ArtLikedVO addArtLiked(Integer articleId, Integer memberId) {
		
		ArtLikedVO artLikedVO = new  ArtLikedVO();
		
		artLikedVO.setArticleId(articleId);
		artLikedVO.setMemberId(memberId);
		dao.insert(artLikedVO);
		
		return artLikedVO;
	}
	public ArtLikedVO updateArtLiked(Integer articleId,Integer memberId) {
		
		ArtLikedVO artLikedVO = new  ArtLikedVO();
		
		artLikedVO.setArticleId(articleId); 
		artLikedVO.setMemberId(memberId);
		dao.update(artLikedVO);
		
		return artLikedVO;
	}
	public void deleteArtLiked(Integer artialeId) {
		dao.delete(artialeId);
	}
	public ArtLikedVO getOneArtLiked(Integer artialeId) {
		return dao.findByPrimaryKey(artialeId);
	}
	public List<ArtLikedVO>getAll(){
		return dao.getAll();
	}
	
}
