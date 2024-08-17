package TestCases;

import Base.DriverManagement;
import Pages.LoginPage;
import Pages.ProductDetailPage;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductDetailTest extends DriverManagement {
    WebDriver driver;
    public ProductDetailPage productDetailPage;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        Log.info(driver);
    }
    @Test(priority = 1)
    public void login(){
        loginPage = new LoginPage(driver);
        productDetailPage = loginPage.login_product_detail_page();
        productDetailPage.end_case();
    }


    @Test(priority = 2)
    public void validateProduct(){
        productDetailPage.compareProductInformation();
        productDetailPage.end_case();
    }


    @Test(priority = 3)
    public void addProductToCart(){
        productDetailPage.clickProduct();
        productDetailPage.click_add_to_cart();
        productDetailPage.validateAddToCartMessage();
        productDetailPage.end_case();

    }


}
