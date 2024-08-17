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
    private final Excel excel = new Excel();
    private final LoginLocators loginLocators;
    private final Action action;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.loginLocators = new LoginLocators();
        this.action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Contain steps
    public void click_login() {
        Log.info("Click login button");
        action.click(loginLocators.login_button);
    }

    public void wait_login_modal() {
        Log.info("Waiting login modal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLocators.login_modal));
    }

    public void verify_login_title(String login_title) {
        Log.info("Verify login title");
        Assert.assertEquals(action.getText(loginLocators.login_title), login_title, "This is not Log in modal!");
    }

    public void fill_username(int row) {
        Log.info("Fill in username");
        try {
            String username = get_username_from_data(row);
            action.enterText(loginLocators.login_username_field, username);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void fill_password(int row) {
        Log.info("Fill in password");
        try {
            String password = get_password_from_data(row);
            action.enterText(loginLocators.login_password_field, password);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void validate_and_confirm_login_alert(String alert_message) {
        Log.info("Validate and confirm alert");
        action.validateAlertMessage(alert_message);
    }

    public void click_login_confirm() {
        Log.info("Click log in");
        action.click(loginLocators.login_confirm);
    }

    public void click_login_close() {
        Log.info("Click close");
        action.click(loginLocators.login_close_button);
    }

    public void wait_login_modal_disappear(){
        Log.info("Waiting login modal close");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loginLocators.login_modal));
            sleep(2000);
        } catch (InterruptedException ie){
            Log.info(ie.getMessage());
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

    public void validate_login(int row) {
        String username = get_username_from_data(row);
        Log.info("Validate log in");
        String expected_message = "Welcome " + username;
        Log.info("Wait for welcome message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLocators.login_welcome_message));
        String actual_message = action.getText(loginLocators.login_welcome_message);
        Assert.assertEquals(expected_message, actual_message, "Failed Log In");
        Log.info("Login Successfully");
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
            excel.setExcelFile("src/Excel/Data.xlsx", "Login Data");
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void login_account(int row){
        click_login();
        wait_login_modal();
        verify_login_title("Log in");
        fill_username(row);
        fill_password(row);
        click_login_confirm();
        wait_login_modal_disappear();
        validate_login(row);
    }
    public ContactPage login_contact_page(){
        login_account(2);
        return new ContactPage(driver);
    }

    public AboutUsPage login_aboutUs_page(){
        login_account(3);
        return new AboutUsPage(driver);
    }
    public ProductDetailPage login_product_detail_page(){
        login_account(2);
        return new ProductDetailPage(driver);
    }

    public CartPage login_cart_page(){
        login_account(3);
        return new CartPage(driver);
    }
}
