package com.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Follow;

public interface FollowDao {

	void createFollow(Follow follow);

	void deleteFollow(@Param("followId") int followId, @Param("userId") int userId);

	Follow getFollowByUsers(@Param("userId") int userId, @Param("followingUserId") int followingUserId);

	List<Follow> getFollowersByUserId(int userId);

	List<Follow> getFollowingByUserId(int followingUserId);
}
