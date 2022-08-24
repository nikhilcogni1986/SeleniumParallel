package org.selenium.pom.pages.Composition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;

public class ProductThumbNail extends BasePage
{
    private final By viewCartBtn = By.cssSelector("a[title='View cart']");

    public ProductThumbNail(WebDriver driver) {
        super(driver);
    }

    private By getAddCartToBtn(String productName)
    {
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");
    }

    public ProductThumbNail clickAddCartBtn(String productName)
    {
        System.out.println(productName);
        waitForElementToBeClickable(getAddCartToBtn(productName));
        driver.findElement(getAddCartToBtn(productName)).click();
        return this;
    }

    public CartPage clickVewCartBtn()
    {
        waitForElementToBeClickable(viewCartBtn).click();
        return new CartPage(driver);
    }
}