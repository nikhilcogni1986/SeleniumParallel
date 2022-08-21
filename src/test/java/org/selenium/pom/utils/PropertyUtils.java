package org.selenium.pom.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils
{
    public static Properties readProperties(String filePath) throws FileNotFoundException {

        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
             fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Unable to find the properties file");
        }
        try {
            properties.load(fis);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new IllegalArgumentException("Unable to load the properties file");
        }
        return properties;
    }
}
