package com.opencart.customer.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencart.customer.TestBase.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtility implements ITestListener {

    public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest test;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hhmmss").format(new Date());

        spark = new ExtentSparkReporter(System.getProperty("user.dir")
                + "\\reports\\" + "test_report_" + timeStamp + ".html");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Functional Testing");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().thumbnailForBase64(true);
        spark.config().setTimelineEnabled(true);

        extent.setSystemInfo("Application Name", "OpenCart");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Username", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName().concat("- PASSED"), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());

        WebDriver driver;

        try {
            driver = (WebDriver) result
                    .getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());

            String imgPath = new BaseClass().screenCapture(result.getName(), driver);

            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName().concat(" - FAILED"), ExtentColor.RED));
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(imgPath).build());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
        test.log(Status.SKIP, result.getThrowable());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
