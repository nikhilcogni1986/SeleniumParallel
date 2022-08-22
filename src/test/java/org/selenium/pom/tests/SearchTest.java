package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class SearchTest extends BaseTest
{
    @Test
    public void searchWithPartialMatch() throws FileNotFoundException {
        StorePage storePage = new StorePage(getDriver()).
                load().
                search("Blue");
        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");
    }
}
