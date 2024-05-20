package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Notification;

public interface NotificationDao {
	void createNotification(Notification notification);

	Notification getNotificationById(Integer notificationId);

	List<Notification> getNotificationsByUserId(Integer userId);

	void updateNotification(Notification notification);

	void deleteNotification(Integer notificationId);
}
