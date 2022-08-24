package org.selenium.pom.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.selenium.pom.factory.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        if (browser == null)
            browser = "CHROME";
        System.getProperty("browser", browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER =" + getDriver());
    }

    @AfterMethod
    public void quitDriver(ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER =" + getDriver());
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("scr" + File.separator + "browser" + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + File.separator
            + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destFile);
        }
        getDriver().quit();
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }
}