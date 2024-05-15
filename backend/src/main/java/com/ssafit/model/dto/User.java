package com.ssafit.model.dto;

import java.sql.Timestamp;

public class User {

	private int userId;

	private String userTag;

	private String userNickname;

	private String userBirth;

	private String userProfileImage;

	private Timestamp userRegDate;

	private String userStreakColor;

	private Integer userStreakPrice;

	public User() {
	};

	public User(int userId, String userTag, String userNickname, String userBirth, String userProfileImage,
			Timestamp userRegDate, String userStreakColor, Integer userStreakPrice) {
		this.userId = userId;
		this.userTag = userTag;
		this.userNickname = userNickname;
		this.userBirth = userBirth;
		this.userProfileImage = userProfileImage;
		this.userRegDate = userRegDate;
		this.userStreakColor = userStreakColor;
		this.userStreakPrice = userStreakPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public Timestamp getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(Timestamp userRegDate) {
		this.userRegDate = userRegDate;
	}

	public String getUserStreakColor() {
		return userStreakColor;
	}

	public void setUserStreakColor(String userStreakColor) {
		this.userStreakColor = userStreakColor;
	}

	public Integer getUserStreakPrice() {
		return userStreakPrice;
	}

	public void setUserStreakPrice(Integer userStreakPrice) {
		this.userStreakPrice = userStreakPrice;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userTag=" + userTag + ", userNickname=" + userNickname + ", userBirth="
				+ userBirth + ", userProfileImage=" + userProfileImage + ", userRegDate=" + userRegDate
				+ ", userStreakColor=" + userStreakColor + ", userStreakPrice=" + userStreakPrice + "]";
	}

}
