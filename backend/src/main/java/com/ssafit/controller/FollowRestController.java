package com.ssafit.controller;

import com.ssafit.model.dto.Follow;
import com.ssafit.model.service.FollowService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowRestController {

	@Autowired
	private FollowService followService;

	@PostMapping("/")
	@Operation(summary = "사용자를 팔로우합니다.")
	public ResponseEntity<Void> followUser(@RequestBody Follow follow) {
        followService.followUser(follow);
        return ResponseEntity.ok().build();
    }

	@DeleteMapping("/{followId}")
	@Operation(summary = "사용자를 언팔로우합니다.")
	public ResponseEntity<Void> unfollowUser(@PathVariable int followId) {
        followService.unfollowUser(followId);
        return ResponseEntity.noContent().build();
    }

	@GetMapping("/followers/{userId}")
	@Operation(summary = "모든 팔로워를 조회합니다.")
	public ResponseEntity<List<Follow>> getFollowers(@PathVariable int userId) {
		List<Follow> followers = followService.getFollowers(userId);
		return ResponseEntity.ok(followers);
	}

	@GetMapping("/following/{followingUserId}")
	@Operation(summary = "팔로우 하고 있는 모든 사용자를 조회합니다.")
	public ResponseEntity<List<Follow>> getFollowing(@PathVariable int followingUserId) {
		List<Follow> following = followService.getFollowing(followingUserId);
		return ResponseEntity.ok(following);
	}
}
