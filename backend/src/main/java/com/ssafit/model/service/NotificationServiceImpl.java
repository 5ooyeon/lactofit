package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.NotificationDao;
import com.ssafit.model.dto.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	public void createNotification(Notification notification) {
		notificationDao.createNotification(notification);
	}

	public Notification getNotificationById(int notificationId) {
		return notificationDao.getNotificationById(notificationId);
	}

	public List<Notification> getNotificationsByUserId(int userId) {
		return notificationDao.getNotificationsByUserId(userId);
	}

	public void deleteNotification(int notificationId) {
		notificationDao.deleteNotification(notificationId);
	}

	public void readNotification(int notificationId) {
		notificationDao.readNotification(notificationId);
	}
}
