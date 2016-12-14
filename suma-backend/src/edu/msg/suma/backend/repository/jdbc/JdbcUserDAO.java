package edu.msg.suma.backend.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.suma.backend.model.User;
import edu.msg.suma.backend.repository.RepositoryException;
import edu.msg.suma.backend.repository.UserDAO;

public class JdbcUserDAO implements UserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDAO.class);
	
	private ConnectionManager connectionManager;

	public JdbcUserDAO() {

		connectionManager = ConnectionManager.getInstance();
	}

	@Override
	public List<User> getAllUsers() {

		List<User> list = new ArrayList<User>();
		Connection connection = null;

		try {

			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet users = statement.executeQuery("Select * from user");

			while (users.next()) {

				User user = new User();
				user.setFirstName(users.getString("firstName"));
				user.setLastName(users.getString("lastName"));
				user.setEmail(users.getString("email"));
				user.setPassword(users.getString("password"));
				user.setId(users.getLong("id"));
				user.setUUID(users.getString("uuid"));
				list.add(user);
			}

			LOGGER.info("Users query was successful");
		} catch (SQLException e) {

			LOGGER.error("Couldn't query Users", e);
			throw new RepositoryException("Couldn't query User", e);
		} finally {

			if (connection != null) {

				connectionManager.returnConnection(connection);
			}
		}

		return list;
	}

	@Override
	public User insertUser(User user) {

		Connection connection = null;

		try {

			connection = connectionManager.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement("insert into user (uuid, firstName, lastName, email, password) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getUUID());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			user.setId(resultSet.getLong(1));
			LOGGER.info("User succesfully inserted");
			
		} catch (SQLException e) {
			
			LOGGER.error("Couldn't insert User", e);
			throw new RepositoryException("Couldn't inser User", e);

		} finally {

			if (connection != null) {

				connectionManager.returnConnection(connection);
			}
		}

		return user;
	}

	@Override
	public void updateUser(User user) {

		Connection connection = null;

		try {

			connection = connectionManager.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement("update user set uuid = ?, firstName = ?, lastName = ?, email = ?, password = ? where id = ?");
			preparedStatement.setString(1, user.getUUID());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setLong(6, user.getId());
			preparedStatement.execute();

			LOGGER.info("User updated succesfully");
			
		} catch (SQLException e) {
			
			LOGGER.error("Couldn't update User", e);
			throw new RepositoryException("Couldn't update User", e);

		} finally {

			if (connection != null) {

				connectionManager.returnConnection(connection);
			}
		}

	}

	@Override
	public void deleteUser(User user) {
		
		Connection connection = null;

		try {

			connection = connectionManager.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
			preparedStatement.setLong(1, user.getId());
			preparedStatement.execute();

			LOGGER.info("User deleted succesfully");
			
		} catch (SQLException e) {
			
			LOGGER.error("Couldn't delete User", e);
			throw new RepositoryException("Couldn't delete User", e);

		} finally {

			if (connection != null) {

				connectionManager.returnConnection(connection);
			}
		}
		
	}

}
