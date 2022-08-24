package org.selenium.pom.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

@Epic("Navigation Tests")
@Feature("Navigation Controls")
public class NavigationTest extends BaseTest
{
    @Story("Navigation to Store Page")
    @Test(description = "Navigate to Store Page")
    public void navigateFromHomeToStoreUsingMainMenu() throws FileNotFoundException {
        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle("Store"),"Store");
    }
}