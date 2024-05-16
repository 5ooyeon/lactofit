package com.ssafit.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/oauth2")
public class AuthRestController {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public ResponseEntity<Map<String, String>> googleCallback(@RequestParam("code") String code,
            @RequestParam(value = "scope", required = false) String scope, HttpSession session) {
    	System.out.println(code);
    	System.out.println(redirectUri);
        RestTemplate restTemplate = new RestTemplate();

        // Exchange the authorization code for an access token
        String tokenUrl = "https://oauth2.googleapis.com/token";
        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("code", code);
        tokenRequest.put("client_id", clientId);
        tokenRequest.put("client_secret", clientSecret);
        tokenRequest.put("redirect_uri", redirectUri);
        tokenRequest.put("grant_type", "authorization_code");

        Map<String, String> tokenResponse = restTemplate.postForObject(tokenUrl, tokenRequest, Map.class);
        String accessToken = tokenResponse.get("access_token");

        // Use the access token to get user info
        String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
        Map<String, String> userInfoResponse = restTemplate.getForObject(userInfoUrl, Map.class);

        // Get user info from response
        String openId = userInfoResponse.get("id");
        String nickname = userInfoResponse.get("name");
        String profileImage = userInfoResponse.get("picture");
        String birth = userInfoResponse.get("birthday");
        
        // 생년월일 파싱
        Date birthDate = null;
        if (birth != null) {
            birthDate = Date.valueOf(birth);
        }

        // Create or update the user in the database
        User user = userService.getUserByTag(openId);
        if (user == null) {
            user = new User();
            user.setUserTag(openId);
            user.setUserNickname(nickname);
            user.setUserBirth(birthDate);
            user.setUserProfileImage(profileImage);
            userService.createUser(user);
        }

        // Save user info in session
        session.setAttribute("user", user);

        // Return user info to frontend
        Map<String, String> response = new HashMap<>();
        response.put("userTag", user.getUserTag());
        response.put("userNickname", user.getUserNickname());
        response.put("userProfileImage", user.getUserProfileImage());
        response.put("userBirth", user.getUserBirth() != null ? user.getUserBirth().toString() : null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
