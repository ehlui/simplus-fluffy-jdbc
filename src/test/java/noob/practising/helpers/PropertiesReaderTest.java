package noob.practising.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PropertiesReaderTest {

	@Test
	public void testNullGetPropertiesDict() {
		Object properties = PropertiesReader.getPropertiesDict(null);
		assertEquals(properties, null);
	}

	// TODO: Test when file does not exists

	@Test
	public void testGetWrongPropertiesDict() {
		Map<String, String> expectedProperties = new HashMap<String, String>();
		expectedProperties.put("db.user", "user123");
		expectedProperties.put("db.password", "example123");
		expectedProperties.put("db.url", "https://tests123.com");

		Map<String, String> properties = PropertiesReader.getPropertiesDict("tests.properties");

		assertNotEquals(expectedProperties.get("db.user"), properties.get("db.user"));
		assertNotEquals(expectedProperties.get("db.password"), properties.get("db.user"));
		assertNotEquals(expectedProperties.get("db.url"), properties.get("db.user"));

	}

	@Test
	public void testGetPropertiesDict() {
		Map<String, String> expectedProperties = new HashMap<String, String>();
		expectedProperties.put("db.user", "user");
		expectedProperties.put("db.password", "example");
		expectedProperties.put("db.url", "https://tests.com");

		Map<String, String> properties = PropertiesReader.getPropertiesDict("tests.properties");

		assertEquals(properties, expectedProperties);
	}

}
