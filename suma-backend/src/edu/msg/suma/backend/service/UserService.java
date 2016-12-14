package edu.msg.suma.backend.service;

import java.util.List;

import edu.msg.suma.backend.model.User;

public interface UserService {

	List<User> getUsers();
	User insertUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
}
