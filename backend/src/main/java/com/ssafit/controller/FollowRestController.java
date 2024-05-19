package com.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Follow;
import com.ssafit.model.service.FollowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/follows")
@Tag(name = "followRestController", description = "팔로우/언팔로우 컨트롤러")
public class FollowRestController {

	@Autowired
	private FollowService followService;

	@PostMapping("/")
	@Operation(summary = "사용자를 팔로우 또는 언팔로우합니다.")
	public ResponseEntity<?> toggleFollow(@RequestBody Follow follow) {
		boolean isfollowed = followService.toggleFollow(follow);
		Map<String, Boolean> response = new HashMap<>();
		response.put("followed", isfollowed);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/followers/{user_Id}")
	@Operation(summary = "특정 사용자의 팔로워 목록을 조회합니다.")
	public ResponseEntity<List<Follow>> getFollowers(@PathVariable("user_Id") int userId) {
		List<Follow> followers = followService.getFollowers(userId);
		return new ResponseEntity<>(followers, HttpStatus.OK);
	}

	@GetMapping("/following/{user_Id}")
	@Operation(summary = "특정 사용자가 팔로우하는 사용자 목록을 조회합니다.")
	public ResponseEntity<List<Follow>> getFollowing(@PathVariable("user_Id") int userId) {
		List<Follow> following = followService.getFollowing(userId);
		return new ResponseEntity<>(following, HttpStatus.OK);
	}

	@GetMapping("/count/followers/{user_Id}")
	@Operation(summary = "특정 사용자의 팔로워 수를 조회합니다.")
	public ResponseEntity<Integer> countFollowers(@PathVariable("user_Id") int userId) {
		int count = followService.countFollowers(userId);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/count/following/{user_Id}")
	@Operation(summary = "특정 사용자가 팔로우하는 수를 조회합니다.")
	public ResponseEntity<Integer> countFollowing(@PathVariable("user_Id") int userId) {
		int count = followService.countFollowing(userId);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
}
