package com.artpic.model;

import java.io.InputStream;

public class ArtPicVO implements java.io.Serializable {
	
	private Integer articlePictureId;
	private Integer articleId;
	private byte[] articlePicture;
	
	
	public Integer getArticlePictureId() {
		return articlePictureId;
	}
	public void setArticlePictureId(Integer articlePictureId) {
		this.articlePictureId = articlePictureId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public byte[] getArticlePicture() {
		return articlePicture;
	}
	public void setArticlePicture(byte[] articlePicture) {
		this.articlePicture = articlePicture;
	}
	
	
}
