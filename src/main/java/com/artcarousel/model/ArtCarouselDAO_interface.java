package com.artcarousel.model;

import java.util.*;

public interface ArtCarouselDAO_interface {
	public void insert(ArtCarouselVO artCarouselVO);
	public void update(ArtCarouselVO artCarouselVO);
	public void delete(Integer articleCarouselId);
	public ArtCarouselVO findByPrimaryKey(Integer articleCarouselId);
	public List<ArtCarouselVO>getAll();
		
	
}
