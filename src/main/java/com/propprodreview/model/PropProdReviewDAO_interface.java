package com.propprodreview.model;

import java.util.List;
import java.util.Set;

import com.propopt.model.*;


public interface PropProdReviewDAO_interface {
	public void insert(PropProdReviewVO propRrodReviewVO);
    public void update(PropProdReviewVO propRrodReviewVO);
    public void delete(Integer reviewProductId);
    public PropProdReviewVO findByPrimaryKey(Integer reviewProductId);
    public List<PropProdReviewVO> getAll();

    public Set<PropOptVO> getPropOptionIdByReviewProductId(Integer reviewProductId);
    

    public void insertWithPropOptionId(PropProdReviewVO propRrodReviewVO , List<PropOptVO> list);
    
    public int insertNew(PropProdReviewVO propRrodReviewVO);
    public void insertByPropsalId(Integer propsalId);
}
