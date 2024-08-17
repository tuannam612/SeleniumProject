package TestCases;

import Base.DriverManagement;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends DriverManagement {

    WebDriver driver;

    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
    }


    @Test(priority = 1)
    public void login_wrong_password() {
        loginPage.click_login();
        loginPage.wait_login_modal();
        loginPage.verify_login_title("Log in");
        loginPage.fill_username(4);
        loginPage.fill_password(4);
        loginPage.click_login_confirm();
        loginPage.wait_for_alert();
        loginPage.validate_and_confirm_login_alert("Wrong password.");
        loginPage.click_login_close();
        loginPage.wait_login_modal_disappear();
        loginPage.end_case();
    }


    @Test(priority = 2)
    public void login_user_not_exist(){
        loginPage.click_login();
        loginPage.wait_login_modal();
        loginPage.verify_login_title("Log in");
        loginPage.fill_username(5);
        loginPage.fill_password(5);
        loginPage.click_login_confirm();
        loginPage.wait_for_alert();
        loginPage.validate_and_confirm_login_alert("User does not exist.");
        loginPage.click_login_close();
        loginPage.wait_login_modal_disappear();
        loginPage.end_case();
    }

    @Test(priority = 3)
    public void login_missing_username_and_password_field(){
        loginPage.click_login();
        loginPage.wait_login_modal();
        loginPage.verify_login_title("Log in");
        loginPage.fill_username(7);
        loginPage.fill_password(7);
        loginPage.click_login_confirm();
        loginPage.wait_for_alert();
        loginPage.validate_and_confirm_login_alert("Please fill out Username and Password.");
        loginPage.click_login_close();
        loginPage.wait_login_modal_disappear();
        loginPage.end_case();
    }

    @Test(priority = 4)
    public void login_success(){
        loginPage.click_login();
        loginPage.wait_login_modal();
        loginPage.verify_login_title("Log in");
        loginPage.fill_username(2);
        loginPage.fill_password(2);
        loginPage.click_login_confirm();
        loginPage.wait_login_modal_disappear();
        loginPage.validate_login(2);
        loginPage.end_case();
    }

}
