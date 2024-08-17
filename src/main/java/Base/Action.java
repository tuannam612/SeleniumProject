package Base;

import Ultilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Action {
    private WebDriver driver;
    private WebDriverWait wait;
//    private final Excel excel = new Excel();

    public Action(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));}


    public void click(By element) {
        driver.findElement(element).click();
        setWait();
    }


    public void enterText(By fieldLocator, String text) {
        WebElement field = driver.findElement(fieldLocator);
        field.clear();
        field.sendKeys(text);
        setWait();
    }

    public String getText(By fieldLocator) {
        return driver.findElement(fieldLocator).getText();
    }


    public boolean validateAlertMessage(String expectedAlert) {
        List<String> actualAlertMessages = getAllAlertMessages();
        boolean alertMatched = actualAlertMessages.contains(expectedAlert);
        String actualAlerts = String.join(", ", actualAlertMessages);
        Assert.assertTrue(alertMatched, "Expected alert message: \"" + expectedAlert + "\", but got: [" + actualAlerts + "]");
        Log.info("Valid Alert Message");
        return true;
    }

    public List<String> getAllAlertMessages() {
        List<String> alertMessages = new ArrayList<>();
        boolean alertPresent = true;

        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            alertMessages.add(alertMessage);
            alert.accept();
        } catch (NoAlertPresentException e) {
            alertPresent = false;
        }
        return alertMessages;
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        sleep(2000);
    }

    public void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    public int getRowCount(By element) {
        List<WebElement> rows = driver.findElements(element);
        return rows.size();
    }
    public void setWait(){
        try {
            sleep(1000);
        }catch (Exception e){
            Log.info(e.getMessage());
        }
    }
}
