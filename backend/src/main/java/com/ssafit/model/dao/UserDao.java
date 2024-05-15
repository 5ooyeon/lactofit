package com.ssafit.model.dao;

import com.ssafit.model.dto.User;

public interface UserDao {
	void createUser(User user);

	User getUserById(int userId);

	void updateUser(User user);

	void deleteUser(int userId);

	void updateStreakColor(int userId, String userStreakColor);

	void updateStreakPrice(int userId, int userStreakPrice);
}
