package org.selenium.pom.DataProviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider
{
    @DataProvider(name="getFeaturedProducts", parallel = false)
    public Object[] getFeaturedProducts() throws IOException
    {
        return JacksonUtils.deserialization("products.json", Product[].class);
    }
}
