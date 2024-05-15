package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.UserDao;
import com.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public void createUser(User user) {
		userDao.createUser(user);
	}

	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}

	public void updateStreakColor(int userId, String userStreakColor) {
		userDao.updateStreakColor(userId, userStreakColor);
	}

	public void updateStreakPrice(int userId, int userStreakPrice) {
		userDao.updateStreakPrice(userId, userStreakPrice);
	}

}
