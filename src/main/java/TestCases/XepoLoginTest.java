package TestCases;

import Base.DriverManagement;
import Pages.LoginPage;
import Pages.ProductDetailPage;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XepoLoginTest extends DriverManagement {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        Log.info(driver);
    }

    @Test(priority = 1)
    public void login(){
        loginPage = new LoginPage(driver);
        loginPage.login_account();
        loginPage.end_case();
    }

}
