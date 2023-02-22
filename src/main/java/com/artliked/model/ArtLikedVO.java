package com.artliked.model;
import java.sql.Date;

public class ArtLikedVO implements java.io.Serializable{
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
}
	
	