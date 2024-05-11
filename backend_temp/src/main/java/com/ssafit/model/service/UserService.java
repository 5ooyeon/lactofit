package com.ssafit.model.service;

import com.ssafit.model.dto.UserDto;

public interface UserService {
	UserDto getLogin(String userId, String userPassword);
	boolean signUp(UserDto userDto);
	UserDto getUserDtoByUserId(String userId);

}
