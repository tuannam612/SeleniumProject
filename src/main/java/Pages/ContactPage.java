package Pages;

import Base.Action;
import Locators.ContactLocators;
import Ultilities.Excel;
import Ultilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class ContactPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final Excel excel = new Excel();
    private final ContactLocators contactLocators;
    private final Action action;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.contactLocators = new ContactLocators();
        this.action = new Action(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click_contact() {
        Log.info("Click contact");
        action.click(contactLocators.contact_button);
    }

    public void wait_contact_modal() {
        Log.info("Wait for contact modal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactLocators.contact_modal));
    }

    public void verify_contact_title() {
        Log.info("Verify contact title");
        Assert.assertEquals(action.getText(contactLocators.contact_title), "New message", "This is not contact modal");
    }

    public void fill_email_field(int row) {
        Log.info("Fill in email");
        try {
            String email = get_email_from_data(row);
            action.enterText(contactLocators.contact_email_field, email);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void fill_name_field(int row) {
        Log.info("Fill in name");
        try {
            String name = get_name_from_data(row);
            action.enterText(contactLocators.contact_name_field, name);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void fill_message_field(int row) {
        Log.info("Fill in message");
        try {
            String message = get_message_from_data(row);
            action.enterText(contactLocators.contact_message_field, message);
        }catch (Exception e){
            Log.info(e.getMessage());
        }
    }

    public void click_send() {
        Log.info("Click send");
        action.click(contactLocators.contact_send_button);
    }

    public void wait_for_alert() {
        Log.info("Wait for alert to present");
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void validate_and_confirm_alert(String alert_message) {
        Log.info("Validate and confirm alert");
        action.validateAlertMessage(alert_message);
    }

    public void wait_contact_modal_disappear(){
        Log.info("Waiting contact modal close");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(contactLocators.contact_modal));
            sleep(2000);
        } catch (InterruptedException ie){
            Log.info(ie.getMessage());
        }
    }

    public void end_case() {
        Log.info("===================================================================");
    }

    public String get_email_from_data(int row) {
        String email = "";
        try {
            setExcelFile();
            return email = excel.getCellData("Contact Email", row);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        return email;
    }


    public String get_name_from_data(int row) {
        String name = "";
        try {
            setExcelFile();
            return name = excel.getCellData("Contact Name", row);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        return name;
    }

    public String get_message_from_data(int row) {
        String message = "";
        try {
            setExcelFile();
            return message = excel.getCellData("Message", row);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        return message;

    }

    public void setExcelFile() {
        try {
            excel.setExcelFile("src/Excel/Data.xlsx", "Message Contact Data");
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }
}
