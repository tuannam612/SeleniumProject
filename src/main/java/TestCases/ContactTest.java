package TestCases;

import Base.DriverManagement;
import Pages.ContactPage;
import Pages.LoginPage;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ContactTest extends DriverManagement {

    WebDriver driver;
    public ContactPage contactPage;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        Log.info(driver);
    }
    @Test
    public void login(){
        loginPage = new LoginPage(driver);
        contactPage = loginPage.login_contact_page();
        contactPage.end_case();
    }
    @Test
    public void send_contact_message(){
        contactPage.click_contact();
        contactPage.wait_contact_modal();
        contactPage.verify_contact_title();
        contactPage.fill_email_field(24);
        contactPage.fill_name_field(24);
        contactPage.fill_message_field(24);
        contactPage.click_send();
        contactPage.wait_for_alert();
        contactPage.validate_and_confirm_alert("Thanks for the message!!");
        contactPage.wait_contact_modal_disappear();
        contactPage.end_case();
    }
}
