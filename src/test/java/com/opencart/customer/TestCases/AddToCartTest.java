package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.HomePage;
import com.opencart.customer.PageObjects.SearchPage;
import com.opencart.customer.TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseClass {

    @Test
    public void test_add_to_cart() {
        logger.info("Starting testing for add to cart");

        try {
            HomePage home = new HomePage(driver);
            SearchPage search = new SearchPage(driver);

            home.searchItems("Samsung Galaxy Tab 10.1");
            home.clickSearchBtn();

            if (search.isProductExist("Samsung Galaxy Tab 10.1")) {
                search.selectItem("Samsung Galaxy Tab 10.1");
                search.setQuantity("2");
                search.clickAddToCartBtn();
            }

            Assert.assertTrue(search.isMsgSuccessAddToCartIsDisplayed(), "The message success did not displayed");
        }
        catch (Exception e) {
            logger.error("Testing has an exception error");
            Assert.fail();
        }
        logger.info("Finished testing for add to cart");
    }
}
