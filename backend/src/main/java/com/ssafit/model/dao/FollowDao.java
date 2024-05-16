package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Follow;

public interface FollowDao {
	
	void createFollow(Follow follow);

	void deleteFollow(int followId);

	List<Follow> getFollowersByUserId(int userId);

	List<Follow> getFollowingByUserId(int followingUserId);
}
