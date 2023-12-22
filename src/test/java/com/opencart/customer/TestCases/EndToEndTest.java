package com.opencart.customer.TestCases;

import com.opencart.customer.PageObjects.*;
import com.opencart.customer.TestBase.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EndToEndTest extends BaseClass {

    @Test
    public void test_end_to_end() {
        logger.info("Starting end to end testing");

        SoftAssert softAssert = new SoftAssert();

        HomePage home = new HomePage(driver);
        AccountRegistrationPage register = new AccountRegistrationPage(driver);
        MyAccountPage account = new MyAccountPage(driver);
        LoginPage login = new LoginPage(driver);
        SearchPage search = new SearchPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutPage checkout = new CheckoutPage(driver);

        home.clickMyAccount();
        logger.info("User clicked on my account link");
        home.clickRegister();
        logger.info("User clicked on register link");

        // Register
        String email = randomAlphanumerics() + "@testing.test";

        register.setFirstName(randomAlphanumerics());
        logger.info("User provided first name");
        register.setLastName(randomAlphanumerics());
        logger.info("User provided last name");
        register.setEmail(email);
        logger.info("User provided email address");
        register.setTelephone(randomNumerics());
        logger.info("User provided telephone number");
        register.setPassword(resBundle.getString("password"));
        logger.info("User provided password");
        register.setConfirmPassword(resBundle.getString("password"));
        logger.info("User provided matched password");
        register.checkPrivacyPolicy();
        logger.info("User checked privacy policy");
        register.clickContinueBtn();
        logger.info("User clicked continue button");

        logger.info("Validating if account has been successfully created");
        softAssert.assertEquals(register.msgAccountSuccessCreated(), "Your Account Has Been Created!",
                "The message is not matched");

        account.clickLogout();
        logger.info("User click logout button");

        home.clickMyAccount();
        logger.info("User clicked on my account link");
        home.clickLogin();
        logger.info("User clicked on register link");

        // Login
        login.setEmail(email);
        logger.info("User input email address");
        login.setPassword(resBundle.getString("password"));
        logger.info("User input password");
        login.clickLoginBtn();
        logger.info("User clicked on login button");

        logger.info("Validating if logged in successfully");
        softAssert.assertTrue(account.isMyAccountDisplayed());

        // Search product then add to cart
        home.searchItems("iPhone");
        logger.info("Input item name on search box");
        home.clickSearchBtn();
        logger.info("Clicked search button");

        if (search.isProductExist("iPhone")) {
            search.selectItem("iPhone");
            logger.info("User select the product");
            search.setQuantity("5");
            logger.info("User set quantity for the product");
            search.clickAddToCartBtn();
            logger.info("User click add to cart button");
        }

        logger.info("Validating if the item added successfully");
        softAssert.assertTrue(search.isMsgSuccessAddToCartIsDisplayed(),
                "The message success did not displayed");

        // Cart Page
        cart.clickCartBtn();
        logger.info("User clicked cart button");
        cart.clickViewCartBtn();
        logger.info("User clicked view cart button");

        logger.info("Validating if the total price is equals to expected price");
        softAssert.assertEquals(cart.getTotalPrice(), "$616.00", "Price is not matched");
        cart.clickCheckoutBtn();
        logger.info("User clicked checkout button");

        // Checkout page
        checkout.setFirstName("First Name");
        logger.info("User provided first name");
        checkout.setLastName("Last Name");
        logger.info("User provided last name");
        checkout.setAddress1("Address 1");
        logger.info("User provided address");
        checkout.setCity("City");
        logger.info("User provided city");
        checkout.setPostCode("12334");
        logger.info("User provided postcode");
        checkout.selectCountry("Philippines");
        logger.info("User select country");
        checkout.selectRegion("Palawan");
        logger.info("User select region");
        checkout.clickContinuePaymentAddressBtn();
        logger.info("User clicked continue button");
        checkout.clickContinueDeliveryDetailsBtn();
        logger.info("User clicked continue button");
        checkout.clickContinueDeliveryMethodBtn();
        logger.info("User clicked continue button");
        checkout.checkTermsAndConditions();
        logger.info("User check the terms and conditions");
        checkout.setComment("Hello, World!");
        logger.info("User provide comment");
        checkout.clickContinuePaymentMethodBtn();
        logger.info("User clicked continue button");

        logger.info("Validating if the total price before confirming order is equals to expected price");
        softAssert.assertEquals(checkout.getTotalPriceBeforeConfirm(), "$510.00");

        logger.info("Validating if the order is successfully placed");
        softAssert.assertEquals(checkout.isOrderSuccessPlaced(), true);

        softAssert.assertAll();

        logger.info("Finished end to end testing");
    }
}
