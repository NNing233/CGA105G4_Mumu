package com.artpic.model;

import java.io.InputStream;
import java.util.List;

public class ArtPicService {
	
	public ArtPicDAO_interface dao;
	
	public ArtPicService() {
		dao = new ArtPicJDBCDAO();
	}
	
	public ArtPicVO addArticlePicture(Integer articleId,byte[] articlePicture) {
			
		ArtPicVO artPicVO = new ArtPicVO();
		artPicVO.setArticleId(articleId);
		artPicVO.setArticlePicture(articlePicture);
		dao.insert(artPicVO);
		
		return artPicVO;
	}
		

	public ArtPicVO updateArticlePicture(byte[] articlePicture) {
		
		ArtPicVO artPictureVO= new ArtPicVO();
		artPictureVO.setArticlePicture(articlePicture);
		
		return artPictureVO;
	}
	

	public void deleteArticlePicyure(Integer artPictureId) {
		dao.delete(artPictureId);
	}
	
	public ArtPicVO getOneArtPic(Integer artPictureId) {
		return dao.findByPrimaryKey(artPictureId);
	}
	
	public List<ArtPicVO> getAll(){
		return dao.getAll();
	}

}
