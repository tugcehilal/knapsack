package com.mobiquity.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is responsible for loading and providing configuration properties.
 */
public class ConfigProperties {
    private static Properties prop;

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ConfigProperties() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Static initializer block to load the configuration properties when the class is loaded.
     */
    static {
        prop = new Properties();
        // Get the configuration file path from the system properties, or use "config.properties" as a default
        String configFilePath = System.getProperty("config.file.path", "config.properties");
        // Get the configuration file as a stream
        InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream(configFilePath);
        try {
            // Load the properties from the configuration file
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    /**
     * Gets a property as an integer.
     *
     * @param key The key of the property.
     * @return The value of the property as an integer.
     */
    public static int getPropertyAsInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}
