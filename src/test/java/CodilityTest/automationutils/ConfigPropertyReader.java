package CodilityTest.automationutils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;


public class ConfigPropertyReader {

    /**
	 *This varaible contains the name of default config property file.
	 */
	private static String defaultConfigFile = "Config.properties";
	/**
	 * construtor of this class.
	 */
	private ConfigPropertyReader() {
	}

	/**
	 * This method will get the properties file config.
	 *
	 * @param propFile the prop file
	 * @param property the property
	 * @return property value
	 */
	public static String getProperty(final String propFile, final String property) {
		try {
			Properties propERTY = ResourceLoader.loadProperties(propFile);
			return propERTY.getProperty(property);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Get property form config file.
	 *
	 * @param property the property
	 * @return particular property
	 */
	public static String getProperty(final String property) {
		return getProperty(defaultConfigFile, property);
	}

	/**
	 * Get all property form config file.
	 * @return map of all key value
	 */
	public static HashMap<String, String> readAllPropertyVlauesFromConfigFile() {
		HashMap<String, String> mymap = new HashMap<String, String>();
		Properties prop;
		try {
			prop = ResourceLoader.loadProperties(defaultConfigFile);
			for (final Entry<Object, Object> entry : prop.entrySet()) {
				mymap.put((String) entry.getKey(), (String) entry.getValue());
			}
			return mymap;
		} catch (IOException e) {
			return null;
		}
	}
}
