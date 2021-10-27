package noob.practising.helpers;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesReaderTest {

    @Test
    public void testNullGetPropertiesDict() {
        assertNull(PropertiesReader.getPropertiesDict(null));
    }

    // TODO: Test when file does not exists

    @Test
    public void testGetWrongPropertiesDict() {
        Map<String, String> expectedProperties = new HashMap<String, String>();
        expectedProperties.put("db.user", "user123");
        expectedProperties.put("db.password", "example123");
        expectedProperties.put("db.url", "https://tests123.com");

        Map<String, String> properties = PropertiesReader.getPropertiesDict("test.properties");

        assertNotEquals(expectedProperties.get("db.user"), properties.get("db.user"));
        assertNotEquals(expectedProperties.get("db.password"), properties.get("db.user"));
        assertNotEquals(expectedProperties.get("db.url"), properties.get("db.user"));

    }

    @Test
    public void testGetPropertiesDict() {
        Map<String, String> expectedProperties = new HashMap<>();
        expectedProperties.put("db.user", "test");
        expectedProperties.put("db.password", "example");
        expectedProperties.put("db.url", "https://tests.com");
        expectedProperties.put("db.host", "localhost");
        expectedProperties.put("db.port", "8788");
        expectedProperties.put("db.name", "metflix");
        expectedProperties.put("db.protocol", "jdbc:mysql");

        Map<String, String> properties = PropertiesReader.getPropertiesDict("test.properties");

        assertEquals(properties, expectedProperties);
    }

}
