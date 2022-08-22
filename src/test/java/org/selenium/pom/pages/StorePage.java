package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;

import java.io.FileNotFoundException;
import java.time.Duration;

public class StorePage extends BasePage
{

    private final By searchFld = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title =  By.cssSelector(".woocommerce-products-header h1");
    private final By viewCartBtn = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchFld(String searchTxt)
    {
        WebElement txtSearch = waitForElementToBeClickable(searchFld);
        txtSearch.clear();
        txtSearch.sendKeys(searchTxt);
        return this;
    }

    public void clickSearchBtn()
    {
        driver.findElement(searchBtn).click();
    }

    public StorePage search(String txt)
    {
        enterTextInSearchFld(txt).
        clickSearchBtn();
        return this;
    }

    public String getTitle(String expectedTitle)
    {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.textToBe(title, expectedTitle));
        return driver.findElement(title).getText();
    }

    private By getAddCartToBtn(String productName)
    {
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");
    }

    public StorePage clickAddCartBtn(String productName)
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

    public StorePage load() throws FileNotFoundException {
        load("/store");
        return this;
    }
}