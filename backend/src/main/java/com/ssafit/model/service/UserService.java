package com.ssafit.model.service;

import com.ssafit.model.dto.User;

public interface UserService {

	public void createUser(User user);

	public User getUserById(int userId);

	public void updateUser(User user);

	public void deleteUser(int userId);

	public void updateStreakColor(int userId, String userStreakColor);

	public void updateStreakPrice(int userId, int userStreakPrice);
}
