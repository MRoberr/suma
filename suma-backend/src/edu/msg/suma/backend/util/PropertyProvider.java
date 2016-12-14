package edu.msg.suma.backend.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public enum PropertyProvider {
	INSTANCE;

	private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("edu.msg.suma.backend.res.suma");
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyProvider.class);

	public String getProperty(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
			
		} catch(MissingResourceException e) {
			
			LOGGER.error("Resource is missing", e);
			return "!" + key + "!";
		}
	}
}
