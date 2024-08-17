package TestCases;

import Base.DriverManagement;
import Pages.AboutUsPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class AboutUsTest extends DriverManagement {
    WebDriver driver;
    public AboutUsPage aboutUsPage;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        aboutUsPage = new AboutUsPage(driver);
    }

    @Test(priority = 1)
    public void login() {
        loginPage = new LoginPage(driver);
        aboutUsPage = loginPage.login_aboutUs_page();
        aboutUsPage.end_case();
    }

    @Test(priority = 2)
    public void aboutUs_video() {
        aboutUsPage.click_about();
        aboutUsPage.wait_aboutUs_modal();
        aboutUsPage.verify_aboutUs_title("About us");
        aboutUsPage.click_poster();
        aboutUsPage.check_video();
        aboutUsPage.click_close();
        aboutUsPage.end_case();
    }
}