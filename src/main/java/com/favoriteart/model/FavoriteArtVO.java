package com.favoriteart.model;

import com.art.model.ArtService;
import com.arttype.model.ArtTypeService;

public class FavoriteArtVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer articleId;
	private Integer memberId;
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public com.arttype.model.ArtTypeVO getTheArticleType() {
		com.arttype.model.ArtTypeService  artTypeSvc = new ArtTypeService(); 
		com.arttype.model.ArtTypeVO artTypeVO = artTypeSvc.getOneArtType(articleId);
		return artTypeVO;
	}
	public com.art.model.ArtVO getTheArticle() {
		com.art.model.ArtService  artSvc = new ArtService(); 
		com.art.model.ArtVO artVO = artSvc.getOneArticle(articleId);
		return artVO;
	}
}
	
	