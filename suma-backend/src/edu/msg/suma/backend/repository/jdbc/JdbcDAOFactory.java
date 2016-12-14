package edu.msg.suma.backend.repository.jdbc;

import edu.msg.suma.backend.repository.DAOFactory;
import edu.msg.suma.backend.repository.UserDAO;

public class JdbcDAOFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {

		return new JdbcUserDAO();
	}

}
