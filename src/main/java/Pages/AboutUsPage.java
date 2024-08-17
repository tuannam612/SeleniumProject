package Pages;

import Base.Action;
import Locators.AboutUsLocators;
import Ultilities.Excel;
import Ultilities.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class AboutUsPage {
    private AboutUsLocators aboutUsLocators;
    public WebDriver driver;
    private WebDriverWait wait;
    private final Excel excel = new Excel();
    private final Action action;

    public AboutUsPage(WebDriver driver) {
        this.driver = driver;
        this.aboutUsLocators = new AboutUsLocators();
        this.action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click_about() {
        Log.info("Click About Us");
        action.click(aboutUsLocators.aboutUs_button);
    }

    public void wait_aboutUs_modal() {
        Log.info("Wait for aboutUs modal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutUsLocators.aboutUs_modal));
    }
    public void verify_aboutUs_title(String aboutUs_title){
        Log.info("Verify aboutUs title");
        Assert.assertEquals(action.getText(aboutUsLocators.aboutUs_title), aboutUs_title, "This is not aboutUs modal!");
    }

    public void click_poster() {
        Log.info("Click poster to start video");
        action.click(aboutUsLocators.aboutUs_poster);
    }

    public void check_video() {
        Log.info("Check video if it's playing");
        try {
            WebElement videoElement = driver.findElement(aboutUsLocators.aboutUs_video);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean isPlaying = (Boolean) js.executeScript("return !arguments[0].paused;", videoElement);
            if (isPlaying) {
                sleep(5000);
                Log.info("Video is playing.");
            } else {
                Log.warn("Video is not playing.");
            }
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void click_close(){
        Log.info("Click close");
        action.click(aboutUsLocators.aboutUs_close_button);
    }


    public void end_case() {
        Log.info("===================================================================");
    }

}
