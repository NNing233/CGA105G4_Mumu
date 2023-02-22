package com.myfavorite.model;

import java.util.List;

public interface MyFavoriteDAO_interface {
	public void insert(MyFavoriteVO myFavoriteVO);

	public void update(MyFavoriteVO myFavoriteVO, MyFavoriteVO myFavoriteVO2);
	
//********************刪除特定會員收藏的特定商品*************************//	
	public void delete(Integer memberId, Integer productId); 

	public MyFavoriteVO findByPrimaryKey(Integer memberId, Integer productId); // ��PK���n��

	public List<MyFavoriteVO> getAll();
//  public List<EmpVO> getAll(Map<String, String[]> map); 

//********************列出會員收藏的商品*************************//	
	public List<MyFavoriteVO> getMmeFavorite(Integer memberId);

}
