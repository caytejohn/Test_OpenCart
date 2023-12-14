package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.HomePage;
import com.opencart.customer.PageObjects.LoginPage;
import com.opencart.customer.PageObjects.MyAccountPage;
import com.opencart.customer.TestBase.BaseClass;
import com.opencart.customer.Utilities.DataProvidersUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProvidersUtility.class)
    public void test_data_driven_login(String email, String password, String result) {
        logger.info("Starting testing for data driven login");

        try {
            HomePage home = new HomePage(driver);
            LoginPage login = new LoginPage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            home.clickMyAccount();
            logger.info("User clicked on my account link");
            home.clickLogin();
            logger.info("User clicked on my login link");

            login.setEmail(email);
            logger.info("User input email address");
            login.setPassword(password);
            logger.info("User input password");
            login.clickLoginBtn();
            logger.info("User clicked on login button");

            logger.info("Validating if logged in successfully");
            boolean myAccountContent = myAccount.isMyAccountDisplayed();

            if (result.equalsIgnoreCase("valid")) {
                if (myAccountContent) {
                    myAccount.clickLogout();
                    Assert.assertTrue(true);
                }
                else {
                    Assert.fail();
                }
            }
            else if (result.equalsIgnoreCase("invalid")) {
                if (myAccountContent) {
                    myAccount.clickLogout();
                    Assert.fail();
                }
                else {
                    Assert.assertTrue(true);
                }
            }
        }
        catch (Exception e) {
            Assert.fail();
        }
        logger.info("Finished testing for data driven login");
    }
}
