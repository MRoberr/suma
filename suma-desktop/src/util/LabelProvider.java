package util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum LabelProvider {
	INSTANCE;

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("edu.msg.suma.desktop.res.SumaBundle", new Locale(""));
	

	public String getProperty(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
			
		} catch(MissingResourceException e) {
			
			return "!" + key + "!";
		}
	}
	
	public void setLocale(Locale locale) {
		
		RESOURCE_BUNDLE = ResourceBundle.getBundle("edu.msg.suma.desktop.res.SumaBundle", locale);
	}
}
