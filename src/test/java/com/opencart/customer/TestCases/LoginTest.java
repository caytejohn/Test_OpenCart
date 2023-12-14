package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.HomePage;
import com.opencart.customer.PageObjects.LoginPage;
import com.opencart.customer.PageObjects.MyAccountPage;
import com.opencart.customer.TestBase.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    @Test(groups = {"sanity", "main"})
    public void test_success_login() {
        logger.info("Starting testing for success login");

        try {
            HomePage home = new HomePage(driver);
            LoginPage login = new LoginPage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            home.clickMyAccount();
            logger.info("User clicked on my account link");
            home.clickLogin();
            logger.info("User clicked on my login link");

            login.setEmail(resBundle.getString("email"));
            logger.info("User input email address");
            login.setPassword(resBundle.getString("password"));
            logger.info("User input password");
            login.clickLoginBtn();
            logger.info("User clicked on login button");

            logger.info("Validating if logged in successfully");
            Assert.assertTrue(myAccount.isMyAccountDisplayed());
        } catch (Exception e){
            logger.error("Testing has an exception error");
            Assert.fail();
        }
        logger.info("Finished testing for success login");
    }
}
