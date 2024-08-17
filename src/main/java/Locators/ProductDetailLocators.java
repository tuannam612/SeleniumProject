package Locators;

import org.openqa.selenium.By;

public class ProductDetailLocators {
    public By product_detail = By.xpath("//div[@id='tbodyid']");

    public By product_name = By.cssSelector(".name");
    public By product_price = By.cssSelector(".price-container");
    public By product_description = By.cssSelector("div[id='more-information'] p");
    public By product_add_to_cart_button = By.cssSelector(".btn.btn-success.btn-lg");
    public By cart_button = By.id("cartur");
}
