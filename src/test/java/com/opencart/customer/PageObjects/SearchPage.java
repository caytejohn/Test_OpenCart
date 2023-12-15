package com.opencart.customer.PageObjects;

import com.opencart.customer.PageObjects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // WebElements
    @FindBy(xpath = "//*[@id='content']//img")
    List<WebElement> resultProducts;

    @FindBy(id = "input-quantity")
    WebElement txtQuantity;

    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement btnAddToCart;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement msgSuccessAddToCart;

    // Action Methods
    public boolean isProductExist(String productName) {
        boolean flag = false;
        for(WebElement product:resultProducts) {
            if(product.getAttribute("title").equalsIgnoreCase(productName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void selectItem(String itemName) {
        for (WebElement product : resultProducts) {
            if (product.getAttribute("title").equals(itemName)) {
                wait.until(ExpectedConditions.visibilityOf(product)).click();
            }
        }
    }

    public void setQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOf(txtQuantity)).clear();
        txtQuantity.sendKeys(quantity);
    }

    public void clickAddToCartBtn() {
        btnAddToCart.click();
    }

    public boolean isMsgSuccessAddToCartIsDisplayed () {
        try {
            return msgSuccessAddToCart.isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }
}
