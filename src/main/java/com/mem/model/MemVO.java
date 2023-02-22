package com.mem.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;


public class MemVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String memberAccount;
	private String memberPassword;
	private String memberNickname;
	private Timestamp signUpDatetime ;
	private Integer memberStatus;
	private String idNumber;
	private String memberName;
	private String memberGender;
	private Date dateOfBirth;
	private String memberEmail;
	private String memberPhone;
	private String memberAddress;
	private byte[] profilePhoto;
	private String bankAccount;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public Timestamp getSignUpDatetime() {
		return signUpDatetime;
	}
	public void setSignUpDatetime(Timestamp signUpDatetime) {
		this.signUpDatetime = signUpDatetime;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public byte[] getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Override
	public String toString() {
		return "MemVO [memberId=" + memberId + ", memberAccount=" + memberAccount + ", memberPassword=" + memberPassword
				+ ", memberNickname=" + memberNickname + ", signUpDatetime=" + signUpDatetime + ", memberStatus="
				+ memberStatus + ", idNumber=" + idNumber + ", memberName=" + memberName + ", memberGender="
				+ memberGender + ", dateOfBirth=" + dateOfBirth + ", memberEmail=" + memberEmail + ", memberPhone="
				+ memberPhone + ", memberAddress=" + memberAddress + ", profilePhoto=" + Arrays.toString(profilePhoto)
				+ ", bankAccount=" + bankAccount + "]";
	}
	
	
}
