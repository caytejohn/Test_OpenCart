package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.AccountRegistrationPage;
import com.opencart.customer.PageObjects.HomePage;
import com.opencart.customer.TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountRegistrationTest extends BaseClass {

    @Test(groups = {"regression", "main"})
    public void test_success_account_registration() {
        logger.info("Starting testing for success account registration");

        try {
            HomePage home = new HomePage(driver);
            AccountRegistrationPage registration = new AccountRegistrationPage(driver);

            home.clickMyAccount();
            logger.info("User clicked on my account link");
            home.clickRegister();
            logger.info("User clicked on register link");

            registration.setFirstName(randomAlphanumerics());
            logger.info("User provided first name");
            registration.setLastName(randomAlphanumerics());
            logger.info("User provided last name");
            registration.setEmail(randomAlphanumerics() + "@testing.test");
            logger.info("User provided email address");
            registration.setTelephone(randomNumerics());
            logger.info("User provided telephone number");
            registration.setPassword("!@test@!");
            logger.info("User provided password");
            registration.setConfirmPassword("!@test@!");
            logger.info("User provided matched password");
            registration.checkPrivacyPolicy();
            logger.info("User checked privacy policy");
            registration.clickContinueBtn();
            logger.info("User clicked continue button");

            logger.info("Validating if account has been successfully created");
            Assert.assertEquals(registration.msgAccountSuccessCreated(), "Your Account Has Been Created!",
                    "The message is not matched");
        }
        catch (Exception e) {
            logger.error("Testing has an exception error");
            Assert.fail();
        }
        logger.info("Finished testing for success account registration");
    }
}
