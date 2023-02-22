package com.art.model;

import java.util.*;



public interface ArtDAO_interface {
	
	  public void insert(ArtVO artVO);
      public void update(ArtVO artVO);
      public void delete(Integer articleId);
      public ArtVO findByPrimaryKey(Integer articleId);
      public List<ArtVO> getAll();
	  public List<ArtVO> selectKeyword(String keyword);
	  
	  public List<ArtVO> select(Integer articleTypeId);





}
