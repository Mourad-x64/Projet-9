package com.openclassrooms.projet9microserviceassessment.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class PropertyLoader {
    private Properties properties;

    public PropertyLoader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        // Try-with-resources to ensure the InputStream is closed after use
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            // Load properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        PropertyLoader loader = new PropertyLoader();
        //System.out.println("Database URL: " + loader.getProperty("database.url"));
        //System.out.println("Database User: " + loader.getProperty("database.username"));
        //System.out.println("Database Password: " + loader.getProperty("database.password"));
    }
}
