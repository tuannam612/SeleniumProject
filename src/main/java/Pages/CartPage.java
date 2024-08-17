package Pages;


import Base.Action;
import Locators.CartLocators;
import Ultilities.Excel;
import Ultilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Action action;
    private final Excel excelSheet = new Excel();
    private final CartLocators cartLocators;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Action(driver);
        this.cartLocators = new CartLocators();
    }

    public void waitTotalPriceLoaded() {
        Log.info("Wait for total price loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLocators.total_Price));
    }

    public int getActualTotal() {
        WebElement totalPriceElement = driver.findElement(cartLocators.total_Price);
        String totalPriceText = totalPriceElement.getText().trim();
        int actual_total = Integer.parseInt(totalPriceText);
        Log.info("Total Price Actual: " + actual_total);
        return actual_total;
    }

    public int getExpectedTotal() {
        Log.info("Get expected total price on the table");
        List<WebElement> rows = driver.findElements(cartLocators.product_table_row);
        int priceIndex = 3;
        int expected_total = 0;
        action.setWait();
        for (WebElement row : rows) {
            try {
                WebElement product_price = row.findElement(By.xpath("td[" + priceIndex + "]"));
                String priceText = product_price.getText().trim();
                int price = Integer.parseInt(priceText);
                expected_total = expected_total + price;
            } catch (Exception e) {
                Log.warn("Error: " + e.getMessage());
            }
        }
        return expected_total;
    }

    public void validateTotal() {
        Log.info("Check expected and total price");
        int expectedTotal = getExpectedTotal();
        Log.info("Expected: " + expectedTotal);
        int actualTotal = getActualTotal();
        Log.info("Expected: " + actualTotal);
        Assert.assertEquals(actualTotal, expectedTotal, "Total price doesn't match");
    }

    public void end_case() {
        Log.info("===================================================================");
    }
}
