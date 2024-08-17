package Pages;

import Base.Action;

import Locators.SignUpLocators;
import Ultilities.Excel;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;


public class SignupPage {
    public WebDriver driver;
    private WebDriverWait wait;
    private final Excel excel = new Excel();
    private final SignUpLocators signUpLocators;
    private final Action action;
    
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.signUpLocators = new SignUpLocators();
        this.action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void click_signup() {
        Log.info("Click Signup");
        action.click(signUpLocators.signup_button);
    }

    public void wait_signup_modal() {
        Log.info("Waiting signup modal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpLocators.signup_modal));
    }

    public void verify_signup_title(String signup_title) {
        Log.info("Verify signup title");
        Assert.assertEquals(action.getText(signUpLocators.signup_title), signup_title, "This is not sign up modal!");
    }

    public void fill_username(int row) {
        Log.info("Fill in username");
        try {
            String username = get_username_from_data(row);
            action.enterText(signUpLocators.signup_username_field, username);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void fill_password(int row) {
        Log.info("Fill in password");
        try {
            String password = get_password_from_data(row);
            action.enterText(signUpLocators.signup_password_field, password);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void validate_and_confirm_signup_alert(String alert_message) {
        Log.info("Validate and confirm alert");
        action.validateAlertMessage(alert_message);
    }

    public void click_signup_confirm() {
        Log.info("Click log in");
        action.click(signUpLocators.signup_confirm);
    }

    public void click_signup_close() {
        Log.info("Click Close");
        action.click(signUpLocators.signup_close_button);
    }

    public void wait_signup_modal_disappear(){
        Log.info("Waiting signup modal close");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(signUpLocators.signup_modal));
            sleep(2000);
        } catch (InterruptedException ie){
            Log.info(ie.getMessage());
        }
    }

    public String generateUniqueUsername() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "user_" + timestamp;
    }

    public String generateUniquePassword() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "pass_" + timestamp;
    }
    public void fill_random_username(){

        Log.info("Fill in username");
        try {
            String username = generateUniqueUsername();
            action.enterText(signUpLocators.signup_username_field, username);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }
    public void fill_random_password(){
        Log.info("Fill in password");
        try {
            String password = generateUniquePassword();
            action.enterText(signUpLocators.signup_password_field, password);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }




    public String get_username_from_data(int row) {
        String username = "";
        try {
            setExcelFile();
            return username = excel.getCellData("Username", row);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        return username;
    }

    public String get_password_from_data(int row) {

        String password = "";
        try {
            setExcelFile();
            return password = excel.getCellData("Password", row);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        return password;
    }

    public void end_case() {
        Log.info("===================================================================");
    }

    public void wait_for_alert() {
        Log.info("Wait for alert to present");
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public void setExcelFile() {
        try {
            excel.setExcelFile("src/Excel/Data.xlsx", "Signup Data");
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

}
