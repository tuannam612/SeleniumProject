package Locators;

import org.openqa.selenium.By;

public class HomeLocators {
    public By phones_Categories = By.xpath("(//a[normalize-space()='Phones'])[1]");
    public By laptop_Categories = By.xpath("(//a[normalize-space()='Laptops'])[1]");
    public By monitors_Categories = By.xpath("(//a[normalize-space()='Monitors'])[1]");
    public By categories_productToCompare = By.xpath("//body/div[@id='contcont']/div[@class='row']/div[@class='col-lg-9']/div[@id='tbodyid']/div[1]");
    public By home_button = By.xpath("//li[@class='nav-item active']//a[@class='nav-link']");
    public By next_button = By.id("next2");
    public By cate_firstProduct = By.xpath("//body/div[@id='contcont']/div[@class='row']/div[@class='col-lg-9']/div[@id='tbodyid']/div[1]");
    public By product_list = By.xpath("//div[@class='card h-100']");
}
