package edu.msg.suma.backend.repository;

import edu.msg.suma.backend.repository.jdbc.JdbcDAOFactory;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();
	
	public static DAOFactory getDAOFactory() {
		
		return new JdbcDAOFactory();
	}
}
