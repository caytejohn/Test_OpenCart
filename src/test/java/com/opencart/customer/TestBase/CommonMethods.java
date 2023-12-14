package com.opencart.customer.TestBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class CommonMethods {

    public String randomAlphanumerics() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public String randomNumerics() {
        return RandomStringUtils.randomNumeric(5);
    }

    public String screenCapture(String testName, WebDriver driver) throws IOException {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hhmmss").format(new Date());

        byte[] imageBytes = new byte[0];

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotDest = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
            FileUtils.copyFile(screenshot, new File(screenshotDest));

            imageBytes = IOUtils.toByteArray(new FileInputStream(screenshotDest));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
