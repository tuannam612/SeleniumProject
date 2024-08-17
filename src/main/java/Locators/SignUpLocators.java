package Locators;

import org.openqa.selenium.By;

public class SignUpLocators {
    public By signup_button = By.id("signin2");
    public By signup_username_field = By.id("sign-username");
    public By signup_password_field = By.id("sign-password");
    public By signup_confirm = By.xpath("//button[normalize-space()='Sign up']");
    public By signup_close_button = By.xpath("//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']");
    public By signup_modal = By.id("signInModal");
    public By signup_title = By.id("signInModalLabel");
}
