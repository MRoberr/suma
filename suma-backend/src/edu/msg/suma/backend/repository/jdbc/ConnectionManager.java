package edu.msg.suma.backend.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.suma.backend.repository.RepositoryException;
import edu.msg.suma.backend.util.PropertyProvider;

public final class ConnectionManager {

	private static final String dbUrl = PropertyProvider.INSTANCE.getProperty("mysql.url");
	private static final String user = PropertyProvider.INSTANCE.getProperty("mysql.user");
	private static final String password = PropertyProvider.INSTANCE.getProperty("mysql.password");
	private static final short SIZE = Short.parseShort(PropertyProvider.INSTANCE.getProperty("connection.size"));
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

	private static ConnectionManager instance;
	// private Connection con;
	private List<Connection> pool;

	private ConnectionManager() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// con = DriverManager.getConnection(dbUrl, user, password);

			pool = new ArrayList<>();
			for (int i = 0; i < SIZE; ++i) {

				pool.add(DriverManager.getConnection(dbUrl, user, password));
			}
		LOGGER.info("Connection pool has been initialized");
		} catch (ClassNotFoundException e) {

			LOGGER.error("Could not find class.", e);
			throw new RepositoryException("Could not find class.", e);
			
		} catch (SQLException e) {

			throw new RepositoryException("Could not create connection.", e);
		}
	}

	public static ConnectionManager getInstance() {

		if (instance == null) {

			instance = new ConnectionManager();
		}

		return instance;
	}

	public synchronized Connection getConnection() {

		Connection con = null;

		if (pool.size() > 0) {
			con = pool.get(0);
			pool.remove(0);
		} else {
			
			throw new RepositoryException("No more connection in the pool");
		}
		return con;
	}

	public synchronized void returnConnection(Connection connection) {

		if (pool.size() < SIZE) {
			
			pool.add(connection);
		}
	}
}
