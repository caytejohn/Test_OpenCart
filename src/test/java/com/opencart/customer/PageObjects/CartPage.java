package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage (WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(xpath = "//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
    WebElement btnCart;

    @FindBy(xpath = "//strong[contains(text(), 'Cart')]")
    WebElement btnViewCart;

    @FindBy(xpath = "//*[@id='content']/div[2]/div/table//strong[text()='Total:']//following::td")
    WebElement totalPrice;

    @FindBy(css = "a[class='btn btn-primary']")
    WebElement btnCheckout;

    // Action Methods
    public void clickCartBtn() {
        btnCart.click();
    }

    public void clickViewCartBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnViewCart)).click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void clickCheckoutBtn() {
        btnCheckout.click();
    }
}
