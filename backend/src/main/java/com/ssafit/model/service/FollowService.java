package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Follow;

public interface FollowService {
	
	void followUser(Follow follow);

	void unfollowUser(int followId);

	List<Follow> getFollowers(int userId);

	List<Follow> getFollowing(int followingUserId);
}
