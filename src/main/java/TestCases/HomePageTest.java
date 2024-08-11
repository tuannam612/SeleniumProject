package TestCases;

import Base.BaseSetup;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseSetup {
    private WebDriver driver;
    public HomePage homePage;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test(priority = 1)
    public void logInWrongPassword() throws Exception {
        System.out.println(driver);
        homePage = new HomePage(driver);
        homePage.failed_logIn("Tuan Trinh","GMOzzzzzz", "Wrong password." );
    }

    @Test(priority = 2)
    public void logInUserNotExist() throws Exception {
        System.out.println(driver);
        homePage = new HomePage(driver);
        homePage.failed_logIn("Tuan Viet","GMOzzzzzz", "User does not exist." );
    }

    @Test(priority = 3)
    public void logInMissingField() throws Exception {
        System.out.println(driver);
        homePage = new HomePage(driver);
        homePage.failed_logIn("", "NoPassword", "Please fill out Username and Password." );
    }

    @Test(priority = 3)
    public void logInSuccess() throws Exception {
        System.out.println(driver);
        homePage = new HomePage(driver);
        homePage.login("Tuan Trinh","GMOzzzz");
    }
}
