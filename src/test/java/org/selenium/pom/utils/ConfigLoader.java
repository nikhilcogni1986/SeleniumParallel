package org.selenium.pom.utils;

import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigLoader
{
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() throws FileNotFoundException {
        properties = PropertyUtils.readProperties("src/test/resources/Config.properties");
    }

    public static ConfigLoader getConfigLoaderInstance() throws FileNotFoundException {
        if(configLoader == null)
            configLoader = new ConfigLoader();
        return configLoader;
    }

    public String getBaseUrl()
    {
        String prop = properties.getProperty("baseUrl");
        if(prop!=null)
            return prop;
        else
            throw new IllegalArgumentException("Property baseUrl is not defined in the properties file!!");
    }

    public String getUsername()
    {
        String prop = properties.getProperty("username");
        if(prop!=null)
            return prop;
        else
            throw new IllegalArgumentException("Property username is not defined in the properties file!!");
    }

    public String getPassword()
    {
        String prop = properties.getProperty("password");
        if(prop!=null)
            return prop;
        else
            throw new IllegalArgumentException("Property password is not defined in the properties file!!");
    }
}