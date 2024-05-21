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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;
import com.ssafit.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "userRestController", description = "사용자CRUD, 스트릭 컬러 및 가격 UPDATE")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	private ResponseEntity<?> validateTokenAndGetUser(String token) {
		token = token.replace("Bearer ", ""); // 표준 접두사 "Bearer"가 오기때문에
		if (jwtUtil.validateToken(token)) {
			String userId = jwtUtil.getUserIdFromToken(token);
			User user = userService.getUserById(Integer.parseInt(userId));
			if (user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/")
	@Operation(summary = "사용자를 등록합니다.")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{user_id}")
	@Operation(summary = "유저 정보를 불러옵니다.")
	public ResponseEntity<?> getUserProfile(@PathVariable("user_id") int userId,
			@RequestHeader("Authorization") String token) {
		return validateTokenAndGetUser(token);
	}

	@PutMapping("/{user_id}")
	@Operation(summary = "유저 정보를 수정합니다.")
	public ResponseEntity<?> updateUserProfile(@PathVariable("user_id") int userId, @RequestBody User user,
			@RequestHeader("Authorization") String token) {
		ResponseEntity<?> validationResponse = validateTokenAndGetUser(token);
		if (validationResponse.getStatusCode() == HttpStatus.OK) {
			user.setUserId(userId);
			userService.updateUser(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return validationResponse;
	}

	@DeleteMapping("/{user_id}")
	@Operation(summary = "유저 정보를 삭제합니다.")
	public ResponseEntity<?> deleteUser(@PathVariable("user_id") int userId,
			@RequestHeader("Authorization") String token) {
		ResponseEntity<?> validationResponse = validateTokenAndGetUser(token);
		if (validationResponse.getStatusCode() == HttpStatus.OK) {
			userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return validationResponse;
	}

	@PutMapping("/streakColor/{user_id}")
	@Operation(summary = "유저의 스트릭 색상을 변경합니다.")
	public ResponseEntity<?> updateStreakColor(@PathVariable("user_id") int userId, @RequestBody String userStreakColor,
			@RequestHeader("Authorization") String token) {
		ResponseEntity<?> validationResponse = validateTokenAndGetUser(token);
		if (validationResponse.getStatusCode() == HttpStatus.OK) {
			userService.updateStreakColor(userId, userStreakColor);
			return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
		}
		return validationResponse;
	}

	@PutMapping("/streakPrice/{user_id}")
	@Operation(summary = "유저의 스트릭 뽑기 가격을 변경합니다.")
	public ResponseEntity<?> updateStreakPrice(@PathVariable("user_id") int userId, @RequestBody int userStreakPrice,
			@RequestHeader("Authorization") String token) {
		ResponseEntity<?> validationResponse = validateTokenAndGetUser(token);
		if (validationResponse.getStatusCode() == HttpStatus.OK) {
			userService.updateStreakPrice(userId, userStreakPrice);
			return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
		}
		return validationResponse;
	}
}
