package com.articlereport.model;

import java.util.*;

public interface ArtReportDAO_interface {
	
	public void insert(ArtReportVO artReportVO);
    public void update(ArtReportVO artReportVO);
    public void delete(Integer articleReportId);
    public ArtReportVO findByPrimaryKey(Integer articleReportId);
    public List<ArtReportVO> getAll();

}
