package Pages;

import Base.Action;
import Locators.ProductDetailLocators;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetailPage {
    WebDriver driver;
    private final WebDriverWait wait;
    private final Action action;
    private final ProductDetailLocators productDetailLocators;
    private final HomePage homePage;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.homePage = new HomePage(driver);
        this.productDetailLocators = new ProductDetailLocators();
    }


    public void click_add_to_cart() {
        Log.info("Click add to cart");
        action.click(productDetailLocators.product_add_to_cart_button);
    }

    public void waitProductDetail() {
        Log.info("Wait product detail appear");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailLocators.product_detail));
    }

    public String getProductName() {
        return action.getText(productDetailLocators.product_name);
    }

    public String getProductPrice() {
        return getPrice(action.getText(productDetailLocators.product_price));
    }

    public String getProductDescription() {
        return action.getText(productDetailLocators.product_description);
    }

    public void compareProductInformation() {
        try {
            String expectedProduct = homePage.getProduct();
            waitProductDetail();
            Log.info("Getting product information");
            String name = getProductName();
            String price = getProductPrice();
            String description = getProductDescription();
            String actualProduct = name + "\n" + price + "\n" + description;
            Log.info("Product Info After Click:" + actualProduct);
            Log.info("Checking...");
            Assert.assertEquals(expectedProduct, actualProduct, "Product information doesn't match");
            Log.info("Matched!");
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void clickProduct(){
        try{
            homePage.getProduct();
        }catch (Exception e){
            Log.info(e.getMessage());
        }
    }


    public void validateAddToCartMessage() {
        Log.info("Validate Message");
        action.validateAlertMessage("Product added.");
        Log.info("Product added");
    }


    public String getPrice(String price) {
        Pattern pattern = Pattern.compile("\\$(\\d+)");
        Matcher matcher = pattern.matcher(price);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            Log.warn("Empty Price");
            return "No amount found.";
        }
    }

    public void end_case() {
        Log.info("===================================================================");
    }
}
