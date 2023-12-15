package com.opencart.customer.TestBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.ResourceBundle;

public class BaseClass extends CommonMethods {

    public WebDriver driver;
    public Logger logger;
    public ResourceBundle resBundle;

    @Parameters({"browser"})
    @BeforeClass(groups = {"main", "sanity", "regression"})
    public void setup(String browser) {
        logger = LogManager.getLogger(this.getClass());
        resBundle = ResourceBundle.getBundle("conf");

        switch (browser) {
            case "chrome" -> {
                /* ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito ");
                options.addArguments("--headless=new");
                options.addArguments("--allow-insecure-localhost");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1280,800");
                options.setCapability("acceptInsecureCerts", true);
                 */
                driver = new ChromeDriver();
                logger.info("Opening the Chrome browser");
            }
            case "edge" -> {
                driver = new EdgeDriver();
                logger.info("Opening the MS Edge browser");
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
                logger.info("Opening the Firefox browser");
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(resBundle.getString("url"));
        logger.info("Load " + driver.getCurrentUrl());
    }

    @AfterClass(groups = {"main", "sanity", "regression"})
    public void tearDown() {
        driver.quit();
        logger.info("Closed browsers");
    }
}
