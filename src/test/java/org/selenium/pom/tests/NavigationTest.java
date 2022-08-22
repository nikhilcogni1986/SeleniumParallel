package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class NavigationTest extends BaseTest
{
    @Test
    public void navigateFromHomeToStoreUsingMainMenu() throws FileNotFoundException {
        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle("Store"),"Store");
    }
}