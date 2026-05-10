package com.demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger log = LogManager.getLogger(ConfigReader.class);
    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";

    private static Properties properties;

    static {
        loadProperties();
    }

    private ConfigReader() {}

    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            log.info("Configuration loaded successfully from: {}", CONFIG_PATH);
        } catch (IOException e) {
            log.error("Failed to load config.properties: {}", e.getMessage());
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            log.warn("Property '{}' not found in config.properties", key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl", "https://demoblaze.com/");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    public static String getExcelPath() {
        return getProperty("excel.path");
    }

    public static String getValidSheet() {
        return getProperty("valid.sheet", "ValidCredentials");
    }

    public static String getInvalidSheet() {
        return getProperty("invalid.sheet", "InvalidCredentials");
    }

    public static String getScreenshotPath() {
        return getProperty("screenshot.path", "screenshots/");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }
}
