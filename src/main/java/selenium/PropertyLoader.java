package selenium;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROP_FILE = "/config.properties";

    public static String loadProperty(String name) {
        Properties property = new Properties();
        try {
            property.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            System.err.println("Error: Property file is absent!");
        }

        String value = "";

        if (name != null) {
            value = property.getProperty(name);
        }
        return value;
    }
}
