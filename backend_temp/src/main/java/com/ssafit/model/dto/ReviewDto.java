package com.ssafit.model.dto;

public class ReviewDto {
	int reviewSeq;
	int videoSeq;
	int userSeq;
	String title;
	String regDate;
	String descr;
	
	public ReviewDto() {}
	
	public ReviewDto(String title, String regDate, String descr) {
		this.title = title;
		this.regDate = regDate;
		this.descr = descr;
	}
	
	@Override
	public String toString() {
		return "ReviewDto [reviewSeq=" + reviewSeq + ", videoSeq=" + videoSeq + ", userSeq=" + userSeq + ", title="
				+ title + ", regDate=" + regDate + ", descr=" + descr + "]";
	}
	
	public int getReviewSeq() {
		return reviewSeq;
	}
	public void setReviewSeq(int reviewSeq) {
		this.reviewSeq = reviewSeq;
	}
	public int getVideoSeq() {
		return videoSeq;
	}
	public void setVideoSeq(int videoSeq) {
		this.videoSeq = videoSeq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
