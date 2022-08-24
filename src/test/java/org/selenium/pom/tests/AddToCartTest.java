package org.selenium.pom.tests;

import org.selenium.pom.DataProviders.MyDataProvider;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddToCartTest extends BaseTest
{
    @Test(description = "Add a product to the cart")
    public void addToCartFromProductsPage() throws IOException
    {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbNail().
                clickAddCartBtn(product.getName()).
                clickVewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }

    @Test(dataProvider="getFeaturedProducts", dataProviderClass = MyDataProvider.class,
    description = "Add multiple products to the cart")
    public void addToCartFeaturedProducts(Product product) throws FileNotFoundException {
        CartPage cartPage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                getProductThumbNail().
                clickAddCartBtn(product.getName()).
                clickVewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }
}