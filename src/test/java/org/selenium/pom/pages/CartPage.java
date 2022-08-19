package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage
{
    private final By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed to checkout')]");
    private final By productName = By.cssSelector("td[class='product-name']>a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName()
    {
        return waitForElementToBeVisible(productName).getText();
    }

    public CheckoutPage checkout()
    {
        waitForElementToBeClickable(proceedToCheckoutBtn).click();
        return new CheckoutPage(driver);
    }
}
