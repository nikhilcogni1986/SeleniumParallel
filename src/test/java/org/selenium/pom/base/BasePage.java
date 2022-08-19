package org.selenium.pom.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String endpoint)
    {
        driver.get("https://askomdch.com"+endpoint);
    }

    public WebElement waitForElementToBeVisible(By by)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementToBeClickable(By by)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void handleStaleElementWithForLoop(By by)
    {
        for(int i=0 ; i<10; i++)
        {
            try
            {
                driver.findElement(by).click();
            }
            catch (StaleElementReferenceException e)
            {
                driver.findElement(by).click();
            }
        }
    }

    public void waitForOverlaysToDisappear(By overlay)
    {
        List<WebElement> overlay_element = driver.findElements(overlay);
        if(overlay_element.size()>0)
        {
            System.out.println("Overlay elements: "+overlay_element.size());
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlay_element));
            System.out.println("OVERLAYS ARE INVISIBLE");
        }
        else
            System.out.println("NO OVERLAYS FOUND");
    }

    public void clickForcefully(By by)
    {
        WebElement ele = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", ele);
    }
}
