package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.Composition.MyHeader;
import org.selenium.pom.pages.Composition.ProductThumbNail;

import java.io.FileNotFoundException;

public class HomePage extends BasePage
{
    private ProductThumbNail productThumbNail;
    private MyHeader myHeader;

    public ProductThumbNail getProductThumbNail() {
        return productThumbNail;
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public HomePage(WebDriver driver) {
        super(driver);
        productThumbNail = new ProductThumbNail(driver);
        myHeader = new MyHeader(driver);
    }

    public HomePage load() throws FileNotFoundException {
        load("/");
        return this;
    }
}