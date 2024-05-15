package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Notification;

public interface NotificationService {

	public void createNotification(Notification notification);

	public Notification getNotificationById(int notificationId);

	public List<Notification> getNotificationsByUserId(int userId);

	public void updateNotification(Notification notification);

	public void deleteNotification(int notificationId);
}
