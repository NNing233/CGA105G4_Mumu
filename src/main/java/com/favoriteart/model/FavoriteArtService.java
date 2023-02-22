package com.favoriteart.model;

import java.util.List;

public class FavoriteArtService {
	
	private FavoriteArtDAO_interface dao ;
	
	public FavoriteArtService(){
		dao = new FavoriteArtJDBCDAO();	
	}
	public FavoriteArtVO addFavoriteArt(Integer articleId,Integer memberId) {
		FavoriteArtVO favoriteArtVO = new FavoriteArtVO();
		favoriteArtVO.setArticleId(articleId);
		favoriteArtVO.setMemberId(memberId);
		dao.insert(favoriteArtVO);
		return favoriteArtVO;
	}
	public FavoriteArtVO updateFavoriteArt(Integer articleId,Integer memberId) {
		FavoriteArtVO favoriteArtVO = new FavoriteArtVO();
		favoriteArtVO.setArticleId(articleId);
		favoriteArtVO.setMemberId(memberId);
		dao.update(favoriteArtVO);
		return favoriteArtVO;
	}
	public void deleteFavoriteArt(Integer articleId,Integer memberId) {
		dao.delete(articleId, memberId);
	}
	public FavoriteArtVO getOneFavoriteArt(Integer articleId) {
		return dao.findByPrimaryKey(articleId);
	}
	public List<FavoriteArtVO>getAll(){
		return dao.getAll();
	}
	
	public List<FavoriteArtVO> findByMemberID(Integer memberId){
		return dao.getTheMemberAllFavorArt(memberId);
	}
	
	public FavoriteArtVO getTheFavoriteArt(Integer articleId, Integer memberId) {
		return dao.getTheFavoriteArt(articleId, memberId);
	}
}
