package com.ssafit.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao followDao;

	@Override
	public void toggleFollow(Follow follow) {
		Follow existingFollow = followDao.getFollowByUsers(follow.getUserId(), follow.getFollowingUserId());
		if (existingFollow != null) {
			followDao.deleteFollow(existingFollow.getFollowId(), follow.getUserId());
		} else {
			followDao.createFollow(follow);
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
