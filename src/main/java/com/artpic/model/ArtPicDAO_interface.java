package com.artpic.model;

import java.util.List;

public interface ArtPicDAO_interface {
	
	public void insert(ArtPicVO artPictureVO);
	public void  update(ArtPicVO artPictureVO);
	public void delete(Integer articlePictureId);
	public ArtPicVO findByPrimaryKey(Integer articlePictureId);
    public List<ArtPicVO> getAll();

}
