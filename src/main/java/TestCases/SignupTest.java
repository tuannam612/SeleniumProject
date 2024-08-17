package TestCases;


import Base.DriverManagement;
import Pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class SignupTest extends DriverManagement {
    WebDriver driver;
    public SignupPage signupPage;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
        signupPage = new SignupPage(driver);
    }

    @Test(priority = 1)
    public void signup_missing_username_password_field(){
        signupPage.click_signup();
        signupPage.wait_signup_modal();
        signupPage.verify_signup_title("Sign up");
        signupPage.fill_username(1);
        signupPage.fill_password(1);
        signupPage.click_signup_confirm();
        signupPage.wait_for_alert();
        signupPage.validate_and_confirm_signup_alert("Please fill out Username and Password.");
        signupPage.click_signup_close();
        signupPage.wait_signup_modal_disappear();
        signupPage.end_case();
    }

    @Test(priority = 2)
    public void signup_user_exist(){
        signupPage.click_signup();
        signupPage.wait_signup_modal();
        signupPage.verify_signup_title("Sign up");
        signupPage.fill_username(2);
        signupPage.fill_password(2);
        signupPage.click_signup_confirm();
        signupPage.wait_for_alert();
        signupPage.validate_and_confirm_signup_alert("This user already exist.");
        signupPage.click_signup_close();
        signupPage.wait_signup_modal_disappear();
        signupPage.end_case();
    }

    @Test(priority = 3)
    public void signup_success(){
        signupPage.click_signup();
        signupPage.wait_signup_modal();
        signupPage.verify_signup_title("Sign up");
        signupPage.fill_random_username();
        signupPage.fill_random_password();
        signupPage.click_signup_confirm();
        signupPage.wait_for_alert();
        signupPage.validate_and_confirm_signup_alert("Sign up successful.");
        signupPage.wait_signup_modal_disappear();
        signupPage.end_case();
    }
}
