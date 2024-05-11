package com.ssafit.model.dto;

public class VideoDto {
	int videoSeq;
	String videoURL;
	String title;
	String part;
	int userSeq;
	
	public VideoDto(){}
	
	public VideoDto(String videoURL, String title, String part){
		this.videoURL = videoURL;
		this.title = title;
		this.part = part;
	}
	
	public int getVideoSeq() {
		return videoSeq;
	}
	public void setVideoSeq(int videoSeq) {
		this.videoSeq = videoSeq;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	@Override
	public String toString() {
		return "ReviewDto [videoSeq=" + videoSeq + ", videoURL=" + videoURL + ", title=" + title + ", part=" + part
				+ ", userSeq=" + userSeq + "]";
	}
	
}
