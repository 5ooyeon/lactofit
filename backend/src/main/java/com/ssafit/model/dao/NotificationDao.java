package com.ssafit.model.dao;

import java.util.List;
import com.ssafit.model.dto.Notification;

public interface NotificationDao {
    void createNotification(Notification notification);

    Notification getNotificationById(int notificationId);

    List<Notification> getNotificationsByUserId(int userId);

    void deleteNotification(int notificationId);
}
