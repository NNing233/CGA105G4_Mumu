package com.art.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ArtVO implements java.io.Serializable {
	private Integer articleId;
	private Integer memberId;
	private Integer articleTypeId;
	private Integer articleStatus;
	private String articleTitle;
	private String articleContent;
	private Integer articleLikesAmount;
	private Timestamp articlePostTime;
	private Timestamp articleUpdateTime;

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
	
    public com.mem.model.MemVO getMemVO() {
	    com.mem.model.MemService memSvc = new com.mem.model.MemService();
	    com.mem.model.MemVO memVO = memSvc.getOneMem(memberId);
	    return memVO;
    }

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public Integer getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(Integer articleStatus) {
		this.articleStatus = articleStatus;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getArticleLikesAmount() {
		return articleLikesAmount;
	}

	public void setArticleLikesAmount(Integer articleLikesAmount) {
		this.articleLikesAmount = articleLikesAmount;
	}

	public Timestamp getArticlePostTime() {
		return articlePostTime;
	}

	public void setArticlePostTime(Timestamp articlePostTime) {
		this.articlePostTime = articlePostTime;
	}

	public Timestamp getArticleUpdateTime() {
		return articleUpdateTime;
	}

	public void setArticleUpdateTime(Timestamp articleUpdateTime) {
		this.articleUpdateTime = articleUpdateTime;
	}
	
	
	public com.arttype.model.ArtTypeVO getArtTypeVO(){
		com.arttype.model.ArtTypeService artTypeSvc = new com.arttype.model.ArtTypeService();
		com.arttype.model.ArtTypeVO artTypeVO = artTypeSvc.getOneArtType(articleTypeId);
		return artTypeVO;
	}

	// for join articlePicture from articleId
    public com.artpic.model.ArtPicVO getArtPicVO() {
    	com.artpic.model.ArtPicService artPicSvc = new com.artpic.model.ArtPicService();
    	com.artpic.model.ArtPicVO artPicVO = artPicSvc.getOneArtPic(articleId);
	    return artPicVO;
    }

}
