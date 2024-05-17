package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.Follow;
import com.ssafit.model.dto.Notification;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao followDao;

	@Autowired
	private NotificationService notificationService;

	@Override
	public void toggleFollow(Follow follow) {
		Follow alreadyFollow = followDao.getFollowByUsers(follow.getUserId(), follow.getFollowingUserId());
		if (alreadyFollow != null) {
			followDao.deleteFollow(alreadyFollow.getFollowId(), follow.getUserId());
		} else {
			followDao.createFollow(follow);
			Notification notification = new Notification();
			notification.setUserId(follow.getFollowingUserId());
			notification.setNotificationContent("User " + follow.getUserId() + " has followed you.");
			notification.setNotificationRead(false);
			notificationService.createNotification(notification);
		}
	}

	@Override
	public void deleteFollow(int followId, int userId) {
		followDao.deleteFollow(followId, userId);
	}

	@Override
	public List<Follow> getFollowers(int userId) {
		return followDao.getFollowersByUserId(userId);
	}

	@Override
	public List<Follow> getFollowing(int followingUserId) {
		return followDao.getFollowingByUserId(followingUserId);
	}
}
