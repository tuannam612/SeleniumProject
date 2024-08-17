package Locators;

import org.openqa.selenium.By;

public class ContactLocators {
    public By contact_title = By.id("exampleModalLabel");
    public By contact_modal = By.xpath("//div[@id='exampleModal']//div[@class='modal-content']");
    public By contact_email_field = By.id("recipient-email");
    public By contact_name_field = By.id("recipient-name");
    public By contact_message_field = By.id("message-text");
    public By contact_button = By.xpath("//a[normalize-space()='Contact']");
    public By contact_send_button = By.xpath("//button[normalize-space()='Send message']");
    public By contact_close_button = By.xpath("//div[@id='exampleModal']//button[@type='button'][normalize-space()='Close']");
}
