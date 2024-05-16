package com.ssafit.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/oauth2")
@Tag(name = "authRestController", description = "구글 로그인 관련 컨트롤러")
public class AuthRestController {

	@Value("${google.client.id}")
	private String clientId;

	@Value("${google.client.secret}")
	private String clientSecret;

	@Value("${google.redirect.uri}")
	private String redirectUri;

	@Autowired
	private UserService userService;

	@PostMapping("/callback")
	@Operation(summary = "사용자 정보를 받아서 프론트엔드에 다시 전달.")
	public ResponseEntity<Map<String, String>> googleCallback(@RequestParam String code, HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();

		String tokenUrl = "https://oauth2.googleapis.com/token";
		Map<String, String> tokenRequest = new HashMap<>();
		tokenRequest.put("code", code);
		tokenRequest.put("client_id", clientId);
		tokenRequest.put("client_secret", clientSecret);
		tokenRequest.put("redirect_uri", redirectUri);
		tokenRequest.put("grant_type", "authorization_code");

		Map<String, String> tokenResponse = restTemplate.postForObject(tokenUrl, tokenRequest, Map.class);
		String accessToken = tokenResponse.get("access_token");

		String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
		Map<String, String> userInfoResponse = restTemplate.getForObject(userInfoUrl, Map.class);

		String openId = userInfoResponse.get("id");
		String nickname = userInfoResponse.get("name");
		String profileImage = userInfoResponse.get("picture");
		Date birth = Date.valueOf(userInfoResponse.get("birthday"));

		User user = userService.getUserByTag(openId);
		if (user == null) {
			user = new User();
			user.setUserTag(openId);
			user.setUserNickname(nickname);
			user.setUserBirth(birth);
			user.setUserProfileImage(profileImage);
			userService.createUser(user);
		}

		session.setAttribute("user", user);

		Map<String, String> response = new HashMap<>();
		response.put("userTag", user.getUserTag());
		response.put("userNickname", user.getUserNickname());
		response.put("userProfileImage", user.getUserProfileImage());
		response.put("userBirth", user.getUserBirth().toString());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
