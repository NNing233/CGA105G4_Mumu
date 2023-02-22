package com.notifyrecord.model;

import java.sql.Date;
import java.util.List;

public class NotifyRecordService {

	private NotifyRecordDAO_interface dao;
	
	public NotifyRecordService() {
		dao = new NotifyRecordJDBCDAO();
	}
	
	public NotifyRecordVO addNotifyRecordService( Integer memberId, String notifyContent, 
			byte isRead, Date notifyDatetime 
			) {
		
		NotifyRecordVO notifyRecordVO = new NotifyRecordVO();
		
		
		notifyRecordVO.setMemberId(memberId);
		notifyRecordVO.setNotifyContent(notifyContent);
		notifyRecordVO.setIsRead((byte) isRead);
		notifyRecordVO.setNotifyDatetime(notifyDatetime);
		return notifyRecordVO;
		
	}
	
	public NotifyRecordVO updateNotifyRecord(Integer notifyRecordId, Integer memberId, String notifyContent, 
			byte isRead, Date notifyDatetime ) {
		
		NotifyRecordVO notifyRecordVO = new NotifyRecordVO();
		
		notifyRecordVO.setNotifyRecordId(notifyRecordId);
		notifyRecordVO.setMemberId(memberId);
		notifyRecordVO.setNotifyContent(notifyContent);
		notifyRecordVO.setIsRead((byte) isRead);
		notifyRecordVO.setNotifyDatetime(notifyDatetime);
		
				return notifyRecordVO;
		
	}
	
	public void deleteNotifyRecord(Integer notifyRecordId) {
		dao.delete(notifyRecordId);
	}
	
	public NotifyRecordVO getOneNotifyRecord(Integer notifyRecordId) {
		return dao.findByPrimaryKey(notifyRecordId);
	}
	
	public List<NotifyRecordVO> getAll(){
		return dao.getAll();
	}
	
}
