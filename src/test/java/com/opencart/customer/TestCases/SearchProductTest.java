package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.HomePage;
import com.opencart.customer.PageObjects.SearchPage;
import com.opencart.customer.TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseClass {

    @Test
    public void test_search_item () {
        logger.info("Starting testing for search item");

        try {
            HomePage home = new HomePage(driver);
            SearchPage search = new SearchPage(driver);

            home.searchItems(resBundle.getString("item"));
            logger.info("Input item name on search box");
            home.clickSearchBtn();
            logger.info("Clicked search button");

            search.isProductExist(resBundle.getString("item"));

            logger.info("Validating if the item exists");
            Assert.assertTrue(search.isProductExist(resBundle.getString("item")));
        }
        catch (Exception e) {
            logger.error("Testing has an exception error");
            Assert.fail();
        }
        logger.info("Finished testing for search item");
    }
}
