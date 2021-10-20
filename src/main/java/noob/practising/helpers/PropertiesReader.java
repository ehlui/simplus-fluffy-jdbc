package noob.practising.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import noob.practising.App;

public class PropertiesReader {

	private static Map<String, String> propertiesDict;

	private PropertiesReader() {

	}

	private static void loadProperties(String propFile) {
		try (InputStream input = App.class.getClassLoader().getResourceAsStream(propFile)) {

			Properties prop = new Properties();

			if (input == null) {
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			propertiesDict = new HashMap<String, String>();

			prop.stringPropertyNames().forEach(name -> {
				propertiesDict.put(name, prop.getProperty(name));
			});

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, String> getPropertiesDict(String propFile) {
		if (propFile == null)
			return null;
		if (propertiesDict == null)
			loadProperties(propFile);

		return propertiesDict;
	}

}
