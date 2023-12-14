package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(id = "input-firstname")
    WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    WebElement txtLastName;

    @FindBy(id = "input-email")
    WebElement txtEmail;

    @FindBy(id = "input-telephone")
    WebElement txtTelephone;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(name = "confirm")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(css = "input[value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[contains(text(),'Created!')]")
    WebElement msgAccountCreated;

    // Action Methods
    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName)).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txtTelephone.sendKeys(telephone);
    }

    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPass) {
        txtConfirmPassword.sendKeys(confirmPass);
    }

    public void checkPrivacyPolicy() {
        chkPolicy.click();
    }

    public void clickContinueBtn() {
        btnContinue.click();
    }

    public String msgAccountSuccessCreated () {
        try {
            return msgAccountCreated.getText();
        }
        catch (Exception e1) {
            return e1.getMessage();
        }
    }
}
