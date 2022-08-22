package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.Composition.ProductThumbNail;

import java.io.FileNotFoundException;
import java.time.Duration;

public class StorePage extends BasePage
{

    private final By searchFld = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title =  By.cssSelector(".woocommerce-products-header h1");
    private ProductThumbNail productThumbNail;

    public ProductThumbNail getProductThumbNail() {
        return productThumbNail;
    }

    public StorePage(WebDriver driver)
    {
        super(driver);
        productThumbNail = new ProductThumbNail(driver);
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

    public StorePage load() throws FileNotFoundException {
        load("/store");
        return this;
    }
}