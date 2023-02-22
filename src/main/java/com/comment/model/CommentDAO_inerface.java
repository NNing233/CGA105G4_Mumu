package com.comment.model;

import java.util.*;

public interface CommentDAO_inerface {
	
	public void insert(CommentVO commentVO);
    public void update(CommentVO commentVO);
    public void delete(Integer commentId);
    public CommentVO findByPrimaryKey(Integer commentId);
    public List<CommentVO> getAll();
    public List<CommentVO> getCommentByArtId(Integer articleId);
	
    
    
}
