package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement hdrMyAccount;

    @FindBy(xpath = "//a[@class='list-group-item' and normalize-space()='Logout']")
    WebElement lnkLogout;

    // Methods
    public boolean isMyAccountDisplayed() {
        try {
            return (hdrMyAccount.isDisplayed());
        }
        catch (Exception e) {
            return (false);
        }
    }

    public void clickLogout() {
        lnkLogout.click();
    }
}
