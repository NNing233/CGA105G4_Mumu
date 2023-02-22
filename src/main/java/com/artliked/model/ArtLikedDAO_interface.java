package com.artliked.model;

import java.util.*;

public interface ArtLikedDAO_interface {
          public void insert(ArtLikedVO articleLikedVO);
          public void update(ArtLikedVO articleLikedVO);
          public void delete(Integer articleId);
          public ArtLikedVO findByPrimaryKey(Integer articleId);
          public List<ArtLikedVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
