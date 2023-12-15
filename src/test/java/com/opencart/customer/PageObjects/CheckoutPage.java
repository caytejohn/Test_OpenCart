package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(id = "input-payment-firstname")
    WebElement txtFirstName;

    @FindBy(id = "input-payment-lastname")
    WebElement txtLastName;

    @FindBy(id = "input-payment-address-1")
    WebElement txtAddress1;

    @FindBy(name = "city")
    WebElement txtCity;

    @FindBy(name = "postcode")
    WebElement txtPostCode;

    @FindBy(name = "country_id")
    WebElement drpCountry;

    @FindBy(id = "input-payment-zone")
    WebElement drpRegion;

    @FindBy(xpath = "//input[@id='button-payment-address']")
    WebElement btnContinueBilling;

    @FindBy(xpath = "//input[@id='button-shipping-address']")
    WebElement btnContinueDeliveryDetails;

    @FindBy(xpath = "//input[@id='button-shipping-method']")
    WebElement btnContinueDeliveryMethod;

    @FindBy(xpath = "//div[@id='collapse-payment-method']//textarea[@name='comment']")
    WebElement txtComment;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkTermsAndConditions;

    @FindBy(how = How.ID, using = "button-payment-method")
    WebElement btnContinuePaymentMethod;

    @FindBy(xpath = "//strong[text()='Total:']//following::td")
    WebElement totalPrice;

    @FindBy(xpath = "//h1[contains(text(), 'placed!')]")
    WebElement msgSuccessOrderPlaced;

    @FindBy(id = "button-confirm")
    WebElement btnConfirmOrder;

    // Action Methods
    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName)).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setAddress1(String address1) {
        txtAddress1.sendKeys(address1);
    }

    public void setCity(String city) {
        txtCity.sendKeys(city);
    }

    public void setPostCode(String postCode) {
        txtPostCode.sendKeys(postCode);
    }

    public void selectCountry(String country) {
        new Select(drpCountry).selectByVisibleText(country);
    }

    public void selectRegion(String region) {
        new Select(wait.until(ExpectedConditions.visibilityOf(drpRegion))).selectByVisibleText(region);
    }

    public void clickContinuePaymentAddressBtn() {
        btnContinueBilling.click();
    }

    public void clickContinueDeliveryDetailsBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnContinueDeliveryDetails)).click();
    }

    public void clickContinueDeliveryMethodBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnContinueDeliveryMethod)).click();
    }

    public void setComment(String comments) {
        txtComment.sendKeys(comments);
    }

    public void checkTermsAndConditions() {
        wait.until(ExpectedConditions.visibilityOf(chkTermsAndConditions)).click();
    }

    public void clickContinuePaymentMethodBtn() {
       wait.until(ExpectedConditions.elementToBeClickable(btnContinuePaymentMethod)).click();
    }

    public String getTotalPriceBeforeConfirm() {
        return wait.until(ExpectedConditions.visibilityOf(totalPrice)).getText();
    }

    public boolean isOrderSuccessPlaced() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnConfirmOrder)).click();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            btnConfirmOrder.click();
            return wait.until(ExpectedConditions.visibilityOf(msgSuccessOrderPlaced))
                    .getText()
                    .equalsIgnoreCase("Your order has been placed!");
        }
        catch (Exception e) {
            return false;
        }
    }
}
