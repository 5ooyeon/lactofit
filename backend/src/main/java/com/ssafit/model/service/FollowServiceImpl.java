package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao followDao;

	@Override
	public void followUser(Follow follow) {
		followDao.createFollow(follow);
	}

	@Override
	public void unfollowUser(int followId) {
		followDao.deleteFollow(followId);
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
