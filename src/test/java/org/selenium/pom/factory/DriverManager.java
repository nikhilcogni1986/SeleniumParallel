package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManager
{
    protected WebDriver driver;

    public WebDriver initializeDriver(String browser)
    {
        String localBrowser;
        localBrowser = System.getProperty("browser",browser);
        switch (DriverType.valueOf(localBrowser))
        {
            case CHROME:
                WebDriverManager.chromedriver().cachePath("drivers").setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("drivers").setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name is specified.");
        }
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}