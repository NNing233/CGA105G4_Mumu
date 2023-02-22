package com.accessfunc.model;

import java.util.List;

public interface AccessFuncDAO_interface { //定規則給子類別
    public void insert(AccessFuncVO adminFuncVO);
    public void update(AccessFuncVO adminFuncVO);
    public void delete(Integer accessFunctionId); //基本上沒有刪除
    public AccessFuncVO findByPrimaryKey(Integer accessFunctionId);
    public List<AccessFuncVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}