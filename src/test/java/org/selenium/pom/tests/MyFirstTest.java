package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTest extends BaseTest
{
    @Test
    public void guestCheckOutUsingDirectAccount() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserialization("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search("Blue");

        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");

        storePage.clickAddCartBtn(product.getName());
        CartPage cartPage = storePage.clickVewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void loginAsUserCheckOutUsingDirectAccount() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserialization("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        User user = JacksonUtils.deserialization("users.json",User.class);

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search("Blue");

        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");

        storePage.clickAddCartBtn(product.getName());
        CartPage cartPage = storePage.clickVewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());


        CheckoutPage checkoutPage = cartPage.checkout().
                clickHereToLogin().
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}
