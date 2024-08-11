package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    public WebDriver driver;
    private WebDriverWait webDriverWait;
    private By home_Button = By.xpath("//a[normalize-space()='Contact']");
    private By aboutUs_Button = By.xpath("//a[normalize-space()='About us']");
    private By cart_Button = By.id("cartur");
    /**
     * Log In Section Here!
     */
    private By logIn_Button = By.id("login2");
    private By logInUsername_Field = By.id("loginusername");
    private By logInPassword_Field = By.xpath("//input[@id='loginpassword']");
    private By logIn_Confirm = By.xpath("//button[normalize-space()='Log in']");
    private By ligInClose_Button = By.xpath("//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']");
    private By logIn_WellComeMessage = By.xpath("//a[@id='nameofuser']");


    /**
     * Sign Up Section Here
     */

    private By signUp_Button = By.id("signin2");
    private By signUpUsername_Field = By.id("sign-username");
    private By signUpPassword_Field = By.id("sign-password");
    private By signUp_Confirm = By.xpath("//button[normalize-space()='Sign up']");

    /**
     * Categories Section Here
     */
    private By phones_Categories = By.xpath("//a[@onClick='byCat('phone')']");
    private By laptop_Categories = By.xpath("//a[@onClick='byCat('notebook')']");
    private By monitors_Categories = By.xpath("//a[@onClick='byCat('monitor')']");
    private By logInTitle_Modal = By.xpath("//h5[@id='logInModalLabel']");
    private By signUpTitle_Modal = By.id("signInModalLabel");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }


    public String getLogInTitle() {
        return driver.findElement(logInTitle_Modal).getText();
    }

    public String getSignUpTitle() {
        return driver.findElement(signUpTitle_Modal).getText();
    }

    public void waitForLogInModal() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(logInTitle_Modal));
    }

    public void waitForSignUpModal() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(signUpTitle_Modal));
    }

    public boolean verifyLogInTitleModal() {
        String expectedSignInTitle = "Log in";
        return getLogInTitle().equals(expectedSignInTitle);
    }

    public boolean verifySignUpTitleModal() {
        String expectedSignUpTitle = "Sign up";
        return getSignUpTitle().equals(expectedSignUpTitle);
    }

    public void clicklogInButton() {
        driver.findElement(logIn_Button).click();

    }

    public void clickSignUpButton() {
        driver.findElement(signUp_Button).click();
    }

    public void clickCloseButton() {
        driver.findElement(ligInClose_Button).click();
    }


    public void logInConfirmation() {
        driver.findElement(logIn_Confirm).click();
    }

    public void signUpConfirmation() {
        driver.findElement(signUp_Confirm).click();
    }


    public void signUp(String username, String password) throws InterruptedException {
        clickSignUpButton();
        waitForSignUpModal();
        verifySignUpTitleModal();
        driver.findElement(signUpUsername_Field).sendKeys(username);
        Thread.sleep(2000);
        driver.findElement(signUpPassword_Field).sendKeys(password);
        Thread.sleep(2000);
        signUpConfirmation();
    }

    public void failed_logIn(String username, String password, String expectedAlert) throws InterruptedException {
        clicklogInButton();
        waitForLogInModal();
        verifyLogInTitleModal();
        enterText(logInUsername_Field, username);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInPassword_Field));
        enterText(logInPassword_Field, password);
        logInConfirmation();
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        validateAlertMessage(expectedAlert);
        clickCloseButton();
    }


    public void login(String username, String password) {
        clicklogInButton();
        waitForLogInModal();
        verifyLogInTitleModal();
        enterText(logInUsername_Field, username);
        enterText(logInPassword_Field, password);
        logInConfirmation();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(logIn_WellComeMessage));
        validateLogIn(username);
    }


    public void enterText(By fieldLocator, String text) {
        WebElement field = driver.findElement(fieldLocator);
        field.clear();
        field.sendKeys(text);
    }


    public void validateLogIn(String username) {
        String expectedWelcome_Message = "Welcome " + username;
        String actualWelcome_Message = driver.findElement(logIn_WellComeMessage).getText();
        Assert.assertEquals(expectedWelcome_Message, actualWelcome_Message, "Failed to log in");
    }


    public List<String> getAllAlertMessages() {
        List<String> alertMessages = new ArrayList<>();
        boolean alertPresent = true;

        while (alertPresent) {
            try {
                Alert alert = driver.switchTo().alert();
                String alertMessage = alert.getText();
                alertMessages.add(alertMessage);
                alert.accept();
            } catch (NoAlertPresentException e) {
                alertPresent = false;
            }
        }

        return alertMessages;
    }
    public void validateAlertMessage(String expectedAlert) {
        List<String> actualAlertMessages = getAllAlertMessages();
        boolean alertMatched = actualAlertMessages.contains(expectedAlert);
        if (!alertMatched) {
            String actualAlerts = String.join(", ", actualAlertMessages);
            Assert.fail("Expected alert message: \"" + expectedAlert + "\", but got: [" + actualAlerts + "]");
        } else {
            Assert.assertTrue(true);

        }
    }
}





