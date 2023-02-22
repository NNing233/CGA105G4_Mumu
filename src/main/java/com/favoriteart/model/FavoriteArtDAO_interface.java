package com.favoriteart.model;

import java.util.*;

public interface FavoriteArtDAO_interface {
          public void insert(FavoriteArtVO favoriteArtVO);
          public void update(FavoriteArtVO favoriteArtVO);
          public void delete(Integer articleId,Integer memberId);
          public FavoriteArtVO findByPrimaryKey(Integer articleId);
          public List<FavoriteArtVO> getAll();
          public List<FavoriteArtVO> getTheMemberAllFavorArt(Integer memberId);
          public FavoriteArtVO getTheFavoriteArt(Integer articleId, Integer memberId);

}
