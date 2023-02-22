package com.notifyrecord.model;

import java.util.List;

public interface NotifyRecordDAO_interface { 
    public void insert(NotifyRecordVO notifyRecordVO);
    public void update(NotifyRecordVO notifyRecordVO);
    public void delete(Integer notifyRecordVO); 
    public NotifyRecordVO findByPrimaryKey(Integer notifyRecordVO);
    public List<NotifyRecordVO> getAll();
   
}