package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

import java.io.FileNotFoundException;

public class HomePage extends BasePage
{
    private final By storeMenuLink = By.cssSelector("#menu-item-1227>a");
    private final By viewCartBtn = By.cssSelector("a[title='View cart']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() throws FileNotFoundException {
        load("/");
        return this;
    }

    public StorePage navigateToStoreUsingMenu()
    {
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }

    private By getAddCartToBtn(String productName)
    {
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");
    }

    public HomePage clickAddCartBtn(String productName)
    {
        waitForElementToBeClickable(getAddCartToBtn(productName)).click();
        //driver.findElement(getAddCartToBtn(productName)).click();
        return this;
    }

    public CartPage clickVewCartBtn()
    {
        waitForElementToBeClickable(viewCartBtn).click();
        return new CartPage(driver);
    }

}
