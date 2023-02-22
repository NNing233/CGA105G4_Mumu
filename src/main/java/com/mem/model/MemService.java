package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	
	private MemDAO_interface dao;
		
	public MemService() {
		dao = new MemJDBCDAO();
	}
	
	public MemVO addMem(String memberAccount, String memberPassword, String memberNickname, Integer memberStatus,
			String idNumber, String memberName, String memberGender,  Date dateOfBirth, String memberEmail,
			String memberPhone, String memberAddress,byte[] profilePhoto, String bankAccount
			) {
				
		MemVO memVO = new MemVO();

		memVO.setMemberAccount(memberAccount);
		memVO.setMemberPassword(memberPassword);
		memVO.setMemberNickname(memberNickname);
		memVO.setMemberStatus(memberStatus);
		memVO.setIdNumber(idNumber);
		memVO.setMemberName(memberName);
		memVO.setMemberGender(memberGender);
		memVO.setDateOfBirth(dateOfBirth);
		memVO.setMemberEmail(memberEmail);
		memVO.setMemberPhone(memberPhone);
		memVO.setMemberAddress(memberAddress);
		memVO.setProfilePhoto(profilePhoto);
		memVO.setBankAccount(bankAccount);
		dao.insert(memVO);
		memVO = dao.findByAccount(memberAccount);
		
		
		return memVO;
		
	}
	
	public MemVO updateMem(Integer memeberID, String memberAccount, String memberNickname, 
		 String idNumber, String memberName, String memberGender,  Date dateOfBirth,
			String memberEmail, String memberPhone, String memberAddress,
			byte[] profilePhoto, String bankAccount) {
				
		MemVO memVO = new MemVO();
		
		memVO.setMemberId(memeberID);
		memVO.setMemberAccount(memberAccount);
		memVO.setMemberNickname(memberNickname);
		memVO.setIdNumber(idNumber);
		memVO.setMemberName(memberName);
		memVO.setMemberGender(memberGender);
		memVO.setDateOfBirth(dateOfBirth);
		memVO.setMemberEmail(memberEmail);
		memVO.setMemberPhone(memberPhone);
		memVO.setMemberAddress(memberAddress);
		memVO.setProfilePhoto(profilePhoto);
		memVO.setBankAccount(bankAccount);
		dao.update(memVO);
		memVO = dao.findByPrimaryKey(memeberID);
		return memVO;
		
	}
	
	public void deleteMem(Integer memberId) {
		dao.delete(memberId);
	}
	
	public MemVO getOneMem(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	
	public MemVO findByAccount(String memberAccount) {
		
		return dao.findByAccount(memberAccount);
	}
	
	public void updateMemberStatus(Integer memberId, Integer memberStatus) {
		MemVO memVO = new MemVO();
		memVO.setMemberId(memberId);
		memVO.setMemberStatus(memberStatus);
		dao.updateStatus(memVO);
	}
	
	public MemVO changePassword(Integer memberId,String newMemberPassword) {
		MemVO memVO = new MemVO();
		memVO.setMemberId(memberId);
		memVO.setMemberPassword(newMemberPassword);
		dao.changePassword(memVO);
		return dao.findByPrimaryKey(memberId);
	}

	
}
