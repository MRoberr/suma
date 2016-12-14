package edu.msg.suma.backend.service.basic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.suma.backend.model.User;
import edu.msg.suma.backend.repository.DAOFactory;
import edu.msg.suma.backend.repository.RepositoryException;
import edu.msg.suma.backend.repository.UserDAO;
import edu.msg.suma.backend.service.ServiceException;
import edu.msg.suma.backend.service.UserService;
import edu.msg.suma.backend.util.PasswordEncrypter;

public class BasicUserService implements UserService{

	private UserDAO userDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicUserService.class);
	
	
	public BasicUserService() {

		try {
			userDAO = DAOFactory.getDAOFactory().getUserDAO();
//			throw new ServiceException("Failed to initialize");
		} catch (RepositoryException e) {
			
			LOGGER.error("Failed to initialize user service");
			throw new ServiceException("Failed to initialize user service", e);
		}
	}
	
	@Override
	public User insertUser(User user) {

		user.setPassword(PasswordEncrypter.encrypt(user.getPassword(), ""));
		LOGGER.info("Password hashed succesfully");
		try {
			return userDAO.insertUser(user);
		} catch (RepositoryException e) {
			
			LOGGER.error("Failed to insert user");
			throw new ServiceException("Failed to insert user", e);
		}
	}

	@Override
	public List<User> getUsers() {

		try {
//			throw new ServiceException("fa");
			return userDAO.getAllUsers();
		} catch (RepositoryException e) {
			
			LOGGER.error("Failed to get users");
			throw new ServiceException("Failed to get users", e);
		}
	}

	@Override
	public void updateUser(User user) {

		try {
			
			userDAO.updateUser(user);
			LOGGER.info("User updated succesfully.");
		} catch (RepositoryException e) {
			
			LOGGER.error("Can't update user");
			throw new ServiceException("Can't update user", e);
		}
		
	}

	@Override
	public void deleteUser(User user) {
		
		try {
			
			userDAO.deleteUser(user);
			LOGGER.info("User deleted succesfully");
		} catch (RepositoryException e) {
			
			LOGGER.error("Failed to delete user");
			throw new ServiceException("Failed to delete user", e);
		}
	}

	
}
