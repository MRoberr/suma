package edu.msg.suma.backend.service;

import edu.msg.suma.backend.service.basic.BasicUserService;

public class ServiceFactory {

	public static UserService getUserService() {
		
		return new BasicUserService();
	}
}
