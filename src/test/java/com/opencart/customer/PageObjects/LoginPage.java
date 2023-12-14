package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(css = "input[name='email']")
    WebElement txtEmail;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    // Actions
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail)).sendKeys(email);
    }

    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        btnLogin.click();
    }
}
