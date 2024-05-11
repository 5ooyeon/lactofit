package com.ssafit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.UserDto;
import com.ssafit.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getLogin(@RequestBody Map<String, String> requestMap, HttpSession session) {
	    String id = requestMap.get("id");
	    String pw = requestMap.get("pw");
	    UserDto userDto = userService.getLogin(id, pw);
	    if(userDto == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	    session.setAttribute("loginUser", userDto);
	    return ResponseEntity.ok().build(); // 성공적인 로그인 응답
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
		boolean isSignedUp = userService.signUp(userDto);
		if(!isSignedUp) {
			return ResponseEntity.badRequest().body("회원가입에 실패하였습니다. 사용자 정보를 확인해주세요.");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
