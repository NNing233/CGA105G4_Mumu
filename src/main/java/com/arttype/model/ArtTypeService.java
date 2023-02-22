package com.arttype.model;

import java.util.List;

public class ArtTypeService {
	
	private ArtTypeDAO_interface dao;
	
	public ArtTypeService() {
		dao = new ArtTypeJDBCDAO();
	}

	public ArtTypeVO addArtType( String articleTypeName) {
		
		ArtTypeVO artTypeVO = new ArtTypeVO();
		
		artTypeVO.setArticleTypeName(articleTypeName);
		dao.insert(artTypeVO);
		
		return artTypeVO;
	}
	public ArtTypeVO updateArtType(Integer articleTypeId, String articleTypeName) {
		
		ArtTypeVO artTypeVO = new ArtTypeVO();
		
		artTypeVO.setArticleTypeId(articleTypeId);
		artTypeVO.setArticleTypeName(articleTypeName);
		dao.update(artTypeVO);
		
		return artTypeVO;
		
	}
	public void deleteArtType(Integer articleTypeId) {
		dao.delete(articleTypeId);
	}
	public ArtTypeVO getOneArtType(Integer articleTypeId) {
		return dao.findByPrimaryKey(articleTypeId);
	}
	public List<ArtTypeVO>getAll(){
		return dao.getAll();
	}
}