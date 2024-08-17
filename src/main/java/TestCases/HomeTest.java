package TestCases;

import Base.DriverManagement;
import Pages.HomePage;
import Pages.LoginPage;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
public class HomeTest extends DriverManagement {
    WebDriver driver;
    public HomePage homePage;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        Log.info(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void login() {

        loginPage.login_account(2);
        homePage.end_case();
    }


    @Test(priority = 2)
    public void home_categories_filter_phones() {
        homePage.click_category("phones");
        homePage.wait_for_product();
        homePage.compare_product("""
                Samsung galaxy s6
                $360
                The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.""");
        homePage.end_case();
    }

    @Test(priority = 3)
    public void home_categories_filter_laptops() {
        homePage.click_category("laptops");
        homePage.wait_for_product();
        homePage.compare_product("""
                Sony vaio i5
                $790
                Sony is so confident that the VAIO S is a superior ultraportable laptop that the company proudly compares the notebook to Apple's 13-inch MacBook Pro. And in a lot of ways this notebook is better, thanks to a lighter weight.""");
        homePage.end_case();
    }

    @Test(priority = 4)
    public void home_categories_filter_monitors() {
        homePage.click_category("monitors");
        homePage.wait_for_product();
        homePage.compare_product("""
                Apple monitor 24
                $400
                LED Cinema Display features a 27-inch glossy LED-backlit TFT active-matrix LCD display with IPS technology and an optimum resolution of 2560x1440. It has a 178 degree horizontal and vertical viewing angle, a "typical" brightness of 375 cd/m2, contrast ratio of 1000:1, and a 12 ms response time.""");
        homePage.end_case();
    }
    @Test(priority = 5)
    public void home_pagination(){
        homePage.click_home();
        homePage.check_if_pagination();
        homePage.end_case();
    }
}
