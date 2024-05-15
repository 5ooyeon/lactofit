package com.ssafit.model.dto;

import java.sql.Timestamp;

public class Notification {
	private int notificationId;
	private int userId;
	private String notificationContent;
	private boolean notificationRead;
	private Timestamp notificationTimestamp;

	public Notification() {
	}

	public Notification(int notificationId, int userId, String notificationContent, boolean notificationRead,
			Timestamp notificationTimestamp) {
		this.notificationId = notificationId;
		this.userId = userId;
		this.notificationContent = notificationContent;
		this.notificationRead = notificationRead;
		this.notificationTimestamp = notificationTimestamp;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	public boolean isNotificationRead() {
		return notificationRead;
	}

	public void setNotificationRead(boolean notificationRead) {
		this.notificationRead = notificationRead;
	}

	public Timestamp getNotificationTimestamp() {
		return notificationTimestamp;
	}

	public void setNotificationTimestamp(Timestamp notificationTimestamp) {
		this.notificationTimestamp = notificationTimestamp;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", userId=" + userId + ", notificationContent="
				+ notificationContent + ", notificationRead=" + notificationRead + ", notificationTimestamp="
				+ notificationTimestamp + "]";
	}

}
