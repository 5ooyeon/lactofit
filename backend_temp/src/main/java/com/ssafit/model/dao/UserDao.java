package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.UserDto;

@Repository
public interface UserDao {
	public UserDto getUserDtoByUserIdAndPassword(@Param(value = "userId") String userId, @Param(value="userPw") String userPassword);
	public UserDto getUerDtoByUserId(String userId);
	public void registUserDto(UserDto userDto);
}
