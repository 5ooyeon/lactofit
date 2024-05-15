package com.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<User> getUserProfile(@PathVariable("user_id") int userId) {
		User user = userService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<User> updateUserProfile(@PathVariable("user_id") int userId, @RequestBody User user) {
		user.setUserId(userId);
		userService.updateUser(user);
		User updatedUser = userService.getUserById(userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable("user_id") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("{\"msg\": \"탈퇴되었습니다. 그 동안 저희 사이트를 이용해주셔서 감사합니다.\"}", HttpStatus.OK);
	}

	@PatchMapping("/{user_id}/streakColor")
	public ResponseEntity<User> updateStreakColor(@PathVariable("user_id") int userId,
			@RequestBody String userStreakColor) {
		userService.updateStreakColor(userId, userStreakColor);
		User updatedUser = userService.getUserById(userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PatchMapping("/{user_id}/streakPrice")
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
