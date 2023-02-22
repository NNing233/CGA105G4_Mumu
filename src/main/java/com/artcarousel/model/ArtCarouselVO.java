package com.artcarousel.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


import javax.print.attribute.PrintJobAttribute;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class ArtCarouselVO implements java.io.Serializable{
	private Integer articleCarouselId;
	private Integer articleCarouselPosition;
	private Integer articleTypeId;
	private byte[] articleCarouselPicture;
	private String articleLink;
	
	public Integer getArticleCarouselId() {
		return articleCarouselId;
	}
	public void setArticleCarouselId(Integer articleCarouselId) {
		this.articleCarouselId = articleCarouselId;
	}
	public Integer getArticleCarouselPosition() {
		return articleCarouselPosition;
	}
	public void setArticleCarouselPosition(Integer articleCarouselPosition) {
		this.articleCarouselPosition = articleCarouselPosition;
	}
	public Integer getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId= articleTypeId;
	}
	public byte[] getArticleCarouselPicture() {
		return articleCarouselPicture;
	}
	public void setArticleCarouselPicture(byte[] articleCarouselPicture) {
		this.articleCarouselPicture = articleCarouselPicture;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	
	public com.arttype.model.ArtTypeVO getArtTypeVO(){
		com.arttype.model.ArtTypeService artTypeSvc = new com.arttype.model.ArtTypeService();
		com.arttype.model.ArtTypeVO artTypeVO = artTypeSvc.getOneArtType(articleTypeId);
		return artTypeVO;
	}
	
}