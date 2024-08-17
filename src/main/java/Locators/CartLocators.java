package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartLocators {
    public By total_Price = By.id("totalp");
    public By product_table_row = By.xpath("//table[@class='table table-bordered table-hover table-striped']/tbody/tr");
    public By place_Order = By.cssSelector(".btn.btn-success");
    public By place_Order_Modal = By.xpath("//div[@id='orderModal']//div[@class='modal-content']");
    public By place_Order_Modal_Name = By.id("name");
    public By place_Order_Modal_Country = By.id("country");
    public By place_Order_Modal_City = By.id("city");
    public By place_Order_Modal_CreditCard = By.id("card");
    public By place_Order_Modal_Month = By.id("month");
    public By place_Order_Modal_Year = By.id("year");
    public By place_Order_Modal_Purchase_Button = By.xpath("//button[normalize-space()='Purchase']");
    public By place_Order_Modal_Alert = By.xpath("//div[contains(@class,'showSweetAlert visible')]");
    public By expected_Alert = By.xpath("//h2[normalize-space()='Thank you for your purchase!']");
    public By confirm_Button = By.xpath("//button[normalize-space()='OK']");
    public By close_Button = By.xpath("//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']");
    public By delete_Button = By.xpath("//a[contains(text(),'Delete')]");

    public By home_Button = By.xpath("//li[@class='nav-item active']//a[@class='nav-link']");
    public By cart_Button = By.xpath("//a[normalize-space()='Cart']");


}
