package CodilityTest.automationutils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;

import CodilityTest.webdriverfactory.TestSessionInitiator;

public class YamlReader {

	private static Map<String, Object> object;
	/** The yaml file path. */
	public static String yamlFilePath = "";

	/** The new file path. */
	public static String newFilePath = "";

	/** The common file path. */

	/** The config settings. */
	public static Map<String, String> configSettings = TestSessionInitiator._getSessionConfig();

	/**
	 * Instantiates a new yaml reader.
	 */
	public YamlReader() {
		setYamlFilePath();
	}

	/**
	 * Sets the yaml file path.
	 *
	 * @return the string
	 */
	public static String setYamlFilePath() {

		// String tier = TestSessionInitiator.configSettings.get("tier");
		String tier = configSettings.get("tier");

		if (tier.equalsIgnoreCase("QA")) {
			yamlFilePath = "src/test/resource/CodilityTest/yamldata/QA_Testdata.yml";
		} else if (tier.equalsIgnoreCase("prod") || tier.equalsIgnoreCase("production")) {
			yamlFilePath = "src/test/resource/CodilityTest/yamldata/PROD_Testdata.yml";
		} else {
			Reporter.log("YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data", true);
		}
		return yamlFilePath;
	}

	/**
	 * Gets the yaml value.
	 *
	 * @param token
	 *            the token
	 * @return the yaml value
	 */
	public static String getYamlValue(String token) {
		try {
			return getValue(token);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * Gets the data.
	 *
	 * @param token
	 *            the token
	 * @return the data
	 */
	public String getData(String token) {
		return getYamlValue(token);
	}

	/**
	 * Gets the yaml values.
	 *
	 * @param token
	 *            the token
	 * @return the yaml values
	 */
	public Map<String, Object> getYamlValues(String token) {

		Reader doc = null;
		Yaml yaml;
		Map<String, Object> object;
		try {
			doc = new FileReader(yamlFilePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		yaml = new Yaml();
		object = (Map<String, Object>) yaml.load(doc);
		return parseMap(object, token + ".");
	}
	
	/**
	 * Gets the value.
	 *
	 * @param token
	 *            the token
	 * @return the value
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	private static String getValue(String token) throws FileNotFoundException {
		Reader doc;
		Yaml yaml;
		Map<String, Object> object;
		doc = new FileReader(yamlFilePath);
		yaml = new Yaml();
		object = (Map<String, Object>) yaml.load(doc);
		return getMapValue(object, token);
	}

	/**
	 * Gets the map value.
	 *
	 * @param object
	 *            the object
	 * @param token
	 *            the token
	 * @return the map value
	 */
	public static String getMapValue(Map<String, Object> object, String token) {
		// TODO: check for proper yaml token string based on presence of '.'
		String[] st = token.split("\\.");
		return parseMap(object, token).get(st[st.length - 1]).toString();
	}

	/**
	 * Parses the map.
	 *
	 * @param object
	 *            the object
	 * @param token
	 *            the token
	 * @return the map
	 */
	private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			object = parseMap((Map<String, Object>) object.get(st[0]), token.replace(st[0] + ".", ""));
		}
		return object;
	}

	public static Map<String, Object> getAllYamlValues(String token) {
		return parseMap(object, token + ".");
	}

}
