package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.UserDao;
import com.ssafit.model.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto getLogin(String id, String password) {
		return userDao.getUserDtoByUserIdAndPassword(id, password);
	}

	@Override
	public boolean signUp(UserDto userDto) {
		if(userDao.getUerDtoByUserId(userDto.getUserId()) != null) {
			return false;
		}
		userDao.registUserDto(userDto);
		return true;
	}

	@Override
	public UserDto getUserDtoByUserId(String userId) {
		return userDao.getUerDtoByUserId(userId);
	}

}
