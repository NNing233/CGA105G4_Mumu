package com.arttype.model;

import java.util.*;

public interface ArtTypeDAO_interface {
	      public void insert(ArtTypeVO artTypeVO);
          public void update(ArtTypeVO artTypeVO);
          public void delete(Integer articleTypeId);
          public ArtTypeVO findByPrimaryKey(Integer articleTypeId);
	      public List<ArtTypeVO> getAll();
	      
//	      public Set<ArtTypeVO> getArtTypeByArtType(Integer articleTypeId);
}
