package com.artcarousel.model;

import java.io.InputStream;
import java.util.List;

public class ArtCarouselService {
	
	private ArtCarouselDAO_interface dao;
	
	public ArtCarouselService() {
		dao = new ArtCarouselJDBCDAO();
	}
	public ArtCarouselVO addArtCarousel(Integer articleCarouselPosition,Integer articleTypeId,byte[] articleCarouselPicture,String articleLink) 
	{
		ArtCarouselVO artCarouselVO = new ArtCarouselVO();
		
		artCarouselVO.setArticleCarouselPosition(articleCarouselPosition);
		artCarouselVO.setArticleTypeId(articleTypeId);
		artCarouselVO.setArticleCarouselPicture(articleCarouselPicture);
		artCarouselVO.setArticleLink(articleLink);
		dao.insert(artCarouselVO);
		
		return artCarouselVO;
	}
	public ArtCarouselVO updateArtCarousel(Integer articleCarouselId,Integer articleCarouselPosition,
			Integer articleTypeId, byte[] articleCarouselPicture,String articleLink)
	{
		ArtCarouselVO artCarouselVO = new ArtCarouselVO();
		artCarouselVO.setArticleCarouselId(articleCarouselId);
		artCarouselVO.setArticleCarouselPosition(articleCarouselPosition);
		artCarouselVO.setArticleTypeId(articleTypeId);
		artCarouselVO.setArticleCarouselPicture(articleCarouselPicture);
		artCarouselVO.setArticleLink(articleLink);
		dao.update(artCarouselVO);
		
		return artCarouselVO;
	}
	public void deleteArtCarousel(Integer articleCarouselId) {
		dao.delete(articleCarouselId);
	}
	public ArtCarouselVO getOneArtCarousel(Integer articleCarouselId) {
		return dao.findByPrimaryKey(articleCarouselId);
	}
	public List<ArtCarouselVO>getAll(){
		return dao.getAll();
	}
	
}