package TestCases;



import Base.DriverManagement;
import Base.Action;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class TestListener extends DriverManagement implements ITestListener{
    private WebDriver driver;
    public Action action;
    public WebDriverWait webDriverWait;


    @Override
    public void onStart(ITestContext context) {
        Log.info("Test Suite started: " + context.getName());
        driver = getDriver();
        action = new Action(driver);
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("Test failed: " + result.getMethod().getMethodName());
//        try {
//            CaptureFailedTest.captureScreenshot(driver, result.getName());
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot " + e.getMessage());
//        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Test Suite finished: " + context.getName());
        if (driver!= null) {
            driver.quit();
        }
    }

}
