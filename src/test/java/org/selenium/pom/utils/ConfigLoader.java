package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigLoader
{
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() throws FileNotFoundException {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env))
        {
            case STAGE:
                properties = PropertyUtils.readProperties("src/test/resources/stg_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.readProperties("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalArgumentException("Invalid ENV Type!!");
        }

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