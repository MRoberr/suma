package edu.msg.suma.backend.repository;

import java.util.List;

import edu.msg.suma.backend.model.User;

public interface UserDAO {

	List<User> getAllUsers();	
	User insertUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
}
