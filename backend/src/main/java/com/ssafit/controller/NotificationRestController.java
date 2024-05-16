package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Notification;
import com.ssafit.model.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationRestController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/")
	public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
		notificationService.createNotification(notification);
		return new ResponseEntity<>(notification, HttpStatus.CREATED);
	}

	@GetMapping("/{notification_id}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable("notification_id") Integer notificationId) {
		Notification notification = notificationService.getNotificationById(notificationId);
		if (notification != null) {
			return new ResponseEntity<>(notification, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable("user_id") Integer userId) {
		List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}

	@PutMapping("/{notification_id}")
	public ResponseEntity<Notification> updateNotification(@PathVariable("notification_id") Integer notificationId,
			@RequestBody Notification notification) {
		notification.setNotificationId(notificationId);
		notificationService.updateNotification(notification);
		Notification updatedNotification = notificationService.getNotificationById(notificationId);
		if (updatedNotification != null) {
			return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{notification_id}")
	public ResponseEntity<String> deleteNotification(@PathVariable("notification_id") Integer notificationId) {
		notificationService.deleteNotification(notificationId);
		return new ResponseEntity<>("{\"msg\": \"알림이 삭제되었습니다.\"}", HttpStatus.OK);
	}
}
