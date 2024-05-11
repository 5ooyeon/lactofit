package com.ssafit.model.dto;

public class UserDto {
	int userSeq;
	String userId;
	String userPassword;
	String userNickname;
	
	public UserDto(){}
	
	public UserDto(String userId, String userPassword, String userNickname) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
	}
	
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	@Override
	public String toString() {
		return "UserDto [userSeq=" + userSeq + ", userId=" + userId + ", userPassword=" + userPassword
				+ ", userNickname=" + userNickname + "]";
	}
	
}
