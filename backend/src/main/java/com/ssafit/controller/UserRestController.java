package com.ssafit.controller;

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

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "userRestController", description = "사용자CRUD, 스트릭 컬러 및 가격 UPDATE")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	@Operation(summary = "사용자를 등록합니다.")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{user_id}")
	@Operation(summary = "유저 정보를 불러옵니다.")
	public ResponseEntity<?> getUserProfile(@PathVariable("user_id") int userId) {
		User user = userService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{user_id}")
	@Operation(summary = "유저 정보를 수정합니다.")
	public ResponseEntity<User> updateUserProfile(@PathVariable("user_id") int userId, @RequestBody User user) {
		user.setUserId(userId);
		userService.updateUser(user);
		User updatedUser = userService.getUserById(userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{user_id}")
	@Operation(summary = "유저 정보를 삭제합니다.")
	public ResponseEntity<?> deleteUser(@PathVariable("user_id") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{user_id}/streakColor")
	@Operation(summary = "유저의 스트릭 색상을 변경합니다.")
	public ResponseEntity<User> updateStreakColor(@PathVariable("user_id") int userId,
			@RequestBody String userStreakColor) {
		userService.updateStreakColor(userId, userStreakColor);
		User updatedUser = userService.getUserById(userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{user_id}/streakPrice")
	@Operation(summary = "유저의 스트릭 뽑기 가격을 변경합니다.")
	public ResponseEntity<User> updateStreakPrice(@PathVariable("user_id") int userId,
			@RequestBody int userStreakPrice) {
		userService.updateStreakPrice(userId, userStreakPrice);
		User updatedUser = userService.getUserById(userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
