package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    @FindBy(xpath = "//a[text()='Login']")
    WebElement lnkLogin;

    //Actions
    public void clickMyAccount() {
        wait.until(ExpectedConditions.visibilityOf(lnkMyAccount)).click();
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.visibilityOf(lnkRegister)).click();
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.visibilityOf(lnkLogin)).click();
    }
}
