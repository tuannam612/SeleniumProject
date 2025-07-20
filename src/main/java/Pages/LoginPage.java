package Pages;

import Base.Action;
import Locators.LoginLocators;
import Ultilities.Excel;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class LoginPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final LoginLocators loginLocators;
    private final Action action;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.loginLocators = new LoginLocators();
        this.action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Contain steps
    public void click_start() {
        Log.info("Click start button");
        action.click(loginLocators.startBtn);
    }

    public void fill_username() {
        Log.info("Fill in username");
        action.enterText(LoginLocators.usrNameField, "buituannam06122003@gmail.com");
    }

   public void fill_password() {
        Log.info("Fill in password");
        action.enterText(LoginLocators.pwdField, "Tuannam06122003@");
    }

    public void click_login() {
        Log.info("Click log in");
        action.click(LoginLocators.logginBtn);
    }



    public void login_account() {
        click_start();
        fill_username();
        fill_password();
        click_login();
        Log.info("Login successfully");
    }

    public void end_case() {
        Log.info("===================================================================");
    }
}
