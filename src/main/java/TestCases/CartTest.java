package TestCases;

import Base.DriverManagement;
import Pages.CartPage;
import Pages.ContactPage;
import Pages.LoginPage;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTest extends DriverManagement {
    WebDriver driver;
    public ContactPage contactPage;
    public CartPage cartPage;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        Log.info(driver);
    }
    @Test
    public void login(){
        loginPage = new LoginPage(driver);
        cartPage = loginPage.login_cart_page();
        cartPage.end_case();
    }

    @Test
    public void add_to_cart(){
        cartPage.waitTotalPriceLoaded();
        cartPage.validateTotal();
    }
}
