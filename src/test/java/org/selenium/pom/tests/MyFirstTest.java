package org.selenium.pom.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

@Epic("Add a product")
@Feature("Checkout a product using guest/registered user")
public class MyFirstTest extends BaseTest
{
    @Story("Guest Checkout for a product")
    @Test(description = "Add a product to the cart and checkout as a Guest")
    public void guestCheckOutUsingDirectAccount() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserialization("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                search("Blue");

        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");

        storePage.getProductThumbNail().
                clickAddCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbNail().clickVewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Story("Checkout a product as a registered user")
    @Test(description = "Add a product to the cart and checkout as a registered user")
    public void loginAsUserCheckOutUsingDirectAccount() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserialization("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        User user = JacksonUtils.deserialization("users.json",User.class);

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                search("Blue");

        Assert.assertEquals(storePage.getTitle("Search results: “Blue”"),"Search results: “Blue”");

        storePage.getProductThumbNail().
                clickAddCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbNail().clickVewCartBtn();
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