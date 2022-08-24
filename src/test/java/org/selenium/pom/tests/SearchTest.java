package org.selenium.pom.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

@Epic("Search Tests")
@Feature("Search")
public class SearchTest extends BaseTest
{
    @Story("Search a product")
    @Test(description = "Search a product with partial search criteria")
    public void searchWithPartialMatch() throws FileNotFoundException {
        StorePage storePage = new StorePage(getDriver()).
                load().
                search("Blue");
        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");
    }
}