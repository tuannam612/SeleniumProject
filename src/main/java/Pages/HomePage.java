package Pages;

import Base.Action;
import Locators.HomeLocators;
import Ultilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class HomePage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final Action action;
    private final HomeLocators homeLocators;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        action = new Action(driver);
        this.homeLocators = new HomeLocators();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void click_category(String category){
        Log.info("Click category: " + category);
        By categoryLocator = getCategoryLocator(category);
        action.click(categoryLocator);
    }

    public void wait_for_product(){
        Log.info("Wait for product");
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeLocators.categories_productToCompare));
        action.setWait();
    }

    public void compare_product(String expected_product){
        Log.info("Compare the product");
        WebElement product_to_be_compare = driver.findElement(homeLocators.categories_productToCompare);
        String actual_product = product_to_be_compare.getText();
        Log.info("Actual Product: " + actual_product);
        Assert.assertEquals(actual_product, expected_product,"The first product does not match the expected value." );
        Log.info("Actual product matches the expected product. Filter completed");
    }

    public By getCategoryLocator(String category) {
        return switch (category.toLowerCase()) {
            case "phones" -> homeLocators.phones_Categories;
            case "laptops" -> homeLocators.laptop_Categories;
            case "monitors" -> homeLocators.monitors_Categories;
            default -> throw new IllegalArgumentException("Invalid category: " + category);
        };    }

    public void click_home(){
        Log.info("Click home");
        action.click(homeLocators.home_button);
    }

    public String get_first_product_of_each(){
        WebElement product = driver.findElement(homeLocators.cate_firstProduct);
        return product.getText();
    }

    public void click_next() {
        Log.info("Click next");
        action.click(homeLocators.next_button);
    }

    public void check_if_pagination(){
        Log.info("Checking if pagination works");
        String product_before_click = get_first_product_of_each();
        Log.info("Product before click: " + product_before_click);
        click_next();
        String product_after_click = get_first_product_of_each();
        Log.info("Product after click: " + product_after_click);
        Assert.assertNotEquals(product_before_click, product_after_click, "Failed to pagination");
        Log.info("Pagination works normal!");
        action.setWait();
    }


    public String getProduct() throws InterruptedException {
        Actions a = new Actions(driver);
        List<WebElement> products = driver.findElements(homeLocators.product_list);
        if (products.isEmpty()) {
            Log.info("No product found.");
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        Log.info("Random Index: " + randomIndex);
        sleep(1000);
        String productXPath = "(//div[@class='card h-100'])[" + (randomIndex + 1) + "]";
        WebElement productLocation = driver.findElement(By.xpath(productXPath));
        wait.until(ExpectedConditions.elementToBeClickable(productLocation));
        action.scrollToElement(productLocation);
        Log.info("Getting product information" );
        sleep(1000);
        String productInfoBeforeClick = productLocation.getText();
        Log.info("Product info: " + productInfoBeforeClick);
        sleep(1000);
        String[] productInfoLine = productInfoBeforeClick.split("\n");
        String name = productInfoLine[0];
        By productName_xPath = By.xpath("//a[normalize-space()='" + name + "']");
        WebElement productName = driver.findElement(productName_xPath);
        a.moveToElement(productName).click().build().perform();
        return productInfoBeforeClick;
    }
    public void end_case() {
        Log.info("===================================================================");
    }

}
