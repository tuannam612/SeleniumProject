package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginLocators {
    public final By login_button = By.id("login2");
    public final By login_username_field = By.id("loginusername");
    public final By login_password_field = By.xpath("//input[@id='loginpassword']");
    public final By login_confirm = By.xpath("//button[normalize-space()='Log in']");
    public final By login_close_button = By.xpath("//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']");
    public final By login_welcome_message = By.xpath("//a[@id='nameofuser']");
    public final By login_title = By.xpath("//h5[@id='logInModalLabel']");
    public final By login_modal = By.id("logInModal");

}
