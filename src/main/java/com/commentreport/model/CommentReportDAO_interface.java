package com.commentreport.model;

import java.util.*;

public interface CommentReportDAO_interface {	
	
	public void insert(CommentReportVO commentReportVO);
    public void update(CommentReportVO commentReportVO);
    public void delete(Integer commentReportId);
    public CommentReportVO findByPrimaryKey(Integer commentReportId);
    public List<CommentReportVO> getAll();

}
