package com.ssafit.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Follow;

public interface FollowService {

	void toggleFollow(Follow follow);

	void deleteFollow(@Param("followId") int followId, @Param("userId") int userId);

	List<Follow> getFollowers(int userId);

	List<Follow> getFollowing(int followingUserId);
}
